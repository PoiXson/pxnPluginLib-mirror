package com.poixson.tools.worldstore;

import static com.poixson.utils.BukkitUtils.GetServerPath;
import static com.poixson.utils.Utils.SafeClose;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.poixson.tools.CacheMap;
import com.poixson.tools.xTime;
import com.poixson.tools.abstractions.xStartStop;
import com.poixson.utils.FileUtils;


public abstract class WorldStore_Map<K, V> extends CacheMap<K, V> implements xStartStop {

	public static final long DEFAULT_CYCLES_TIMEOUT  = xTime.Parse( "5m").get(SECONDS_PER_CYCLE);
	public static final long DEFAULT_CYCLES_SAVE     = xTime.Parse("30s").get(SECONDS_PER_CYCLE);
	public static final long DEFAULT_CYCLES_SAVE_MAX = xTime.Parse( "1m").get(SECONDS_PER_CYCLE);

	public static final String DEFAULT_NAMED_FILE    = "<name>.json";
	public static final String DEFAULT_LOCATION_FILE = "<name>.<x>.<z>.json";

	public static final int DEFAULT_GROUP_SIZE = 512;

	protected final JavaPlugin plugin;

	public final String world;
	public final String type;
	public final int group_size;

	public final File path;



	public WorldStore_Map(final JavaPlugin plugin,
			final String world, final String type) {
		this(plugin, world, type, DEFAULT_GROUP_SIZE);
	}
	public WorldStore_Map(final JavaPlugin plugin,
			final String world, final String type, final int group_size) {
		this(
			plugin, world, type,
			group_size,
			DEFAULT_CYCLES_TIMEOUT,
			DEFAULT_CYCLES_SAVE,
			DEFAULT_CYCLES_SAVE_MAX
		);
	}
	public WorldStore_Map(final JavaPlugin plugin,
			final String world, final String type, final int group_size,
			final long cycles_timeout, final long cycles_save, final long cycles_save_max) {
		super(cycles_timeout, cycles_save, cycles_save_max);
		this.plugin = plugin;
		this.world  = world;
		this.type   = type;
		this.group_size = group_size;
		this.path = new File(GetServerPath(), this.world+"/pxn");
	}



	@Override
	public void start() {
		WorldStoreTicker.Get(this.plugin)
			.register(this);
	}

	@Override
	public void stop() {
		WorldStoreTicker.Get(this.plugin)
			.unregister(this);
		// save all
		this.saveAllInvalidate();
	}



	@Override
	public V create(final K key) {
		return null;
	}

	@Override
	public void lazy_load(final K key, final boolean create) {
		final BukkitRunnable run = new BukkitRunnable() {
			private AtomicBoolean create = new AtomicBoolean(false);
			public BukkitRunnable init(final boolean create) {
				this.create.set(create);
				return this;
			}
			@Override
			public void run() {
				WorldStore_Map.this
					.get(key, false, this.create.get());
			}
		}.init(create);
		run.runTaskAsynchronously(this.plugin);
	}
	@Override
	public V load(final K key) {
		final File file = this.getFile(key);
		if (file.isFile()) {
			InputStream in = null;
			try {
				in = Files.newInputStream(file.toPath());
				final String json = FileUtils.ReadInputStream(in);
				if (json == null) throw new IOException("Failed to load json file: "+file.toString());
				final V value = this.load_decode(json);
				if (value != null)
					this.map.put(key, value);
				return value;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				SafeClose(in);
			}
		}
		return null;
	}
	protected abstract V load_decode(final String json);

	@Override
	public void save(final K key) {
		super.save(key);
		final File file = this.getFile(key);
		final V value = this.map.get(key);
		if (value == null)
			return;
		final String json = this.save_encode(key, value);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			SafeClose(writer);
		}
	}
	protected abstract String save_encode(final K key, final V value);

	public abstract File getFile(final K key);



	public int loc_to_group(final int loc) {
		return Math.floorDiv(loc, this.group_size);
	}
	public int loc_to_local(final int loc) {
		return (loc % this.group_size) + (loc<0 ? this.group_size-1 : 0);
	}
	public int loc_to_index(final int x, final int z) {
		return (this.loc_to_local(z) * this.group_size) + this.loc_to_local(x);
	}



}
