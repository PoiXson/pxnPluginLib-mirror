package com.poixson.commonmc.tools.scripting.loader;

import static com.poixson.commonmc.pxnCommonPlugin.LOG_PREFIX;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;


public class ScriptLoader_File implements ScriptLoader {
	protected static final Logger LOG = Logger.getLogger("Minecraft");

	protected final JavaPlugin plugin;

	protected final String path_local;
	protected final String path_resource;
	protected final String filename;
	protected Integer fps = null;

	protected final AtomicReference<ScriptSourceDAO[]> sources = new AtomicReference<ScriptSourceDAO[]>(null);



	public ScriptLoader_File(final JavaPlugin plugin,
			final String path_local, final String path_resource,
			final String filename) {
		this.plugin        = plugin;
		this.path_local    = path_local;
		this.path_resource = path_resource;
		this.filename      = filename;
	}



	@Override
	public void reload() {
		this.sources.set(null);
	}



	@Override
	public String getName() {
		return this.filename;
	}



	@Override
	public int getFPS(final int def) {
		final Integer fps = this.fps;
		if (fps != null)
			return fps.intValue();
		return def;
	}



	@Override
	public boolean hasChanged() {
		final ScriptSourceDAO[] sources = this.sources.get();
		if (sources != null) {
			for (final ScriptSourceDAO src : sources) {
				if (src.hasFileChanged())
					return true;
			}
		}
		return false;
	}



	@Override
	public ScriptSourceDAO[] getSources()
			throws FileNotFoundException {
		// existing sources
		{
			final ScriptSourceDAO[] sources = this.sources.get();
			if (sources != null)
				return sources;
		}
		// load sources
		{
			final LinkedList<ScriptSourceDAO> list = new LinkedList<ScriptSourceDAO>();
			try {
				this.loadSourcesRecursive(list, "prepend.js");
			} catch (FileNotFoundException ignore) {}
			this.loadSourcesRecursive(list, this.filename);
			final ScriptSourceDAO[] sources = list.toArray(new ScriptSourceDAO[0]);
			if (this.sources.compareAndSet(null, sources))
				return sources;
		}
		return this.getSources();
	}

	// load sources recursively
	protected void loadSourcesRecursive(final LinkedList<ScriptSourceDAO> list, final String filename)
			throws FileNotFoundException {
		// find local or resource file
		final ScriptSourceDAO found =
			ScriptSourceDAO.Find(
				this.plugin,
				this.path_local,
				this.path_resource,
				filename
			);
		if (found == null) throw new FileNotFoundException(filename);
		list.add(found);
		// parse header
		if (found.code.startsWith("//#")) {
			String code = found.code;
			String stmt;
			int pos;
			LOOP_LINES:
			while (code.startsWith("//#")) {
				pos = code.indexOf('\n');
				if (pos == -1) {
					stmt = code;
					code = "";
				} else {
					stmt = code.substring(0, pos);
					code = code.substring(pos + 1);
				}
				stmt = stmt.substring(3).trim();
				if (stmt.length() == 0)
					continue LOOP_LINES;
				pos = stmt.indexOf('=');
				// statement
				if (pos == -1) {
					switch (stmt) {
//TODO
					default:
						LOG.warning(String.format("%sUnknown statement: %s  in file: %s", LOG_PREFIX, stmt, filename));
						break;
					}
				// key/value
				} else {
					final String[] parts = stmt.split("=", 2);
					final String key = parts[0].trim();
					switch (key) {
					case "include":
						this.loadSourcesRecursive(list, parts[1].trim());
						break;
					case "fps":
						this.fps = Integer.valueOf(parts[1].trim());
						break;
					default:
						LOG.warning(String.format("%sUnknown statement: %s  in file: %s", LOG_PREFIX, stmt, filename));
						break;
					}
				}
			} // end LOOP_LINES
		}
	}



}