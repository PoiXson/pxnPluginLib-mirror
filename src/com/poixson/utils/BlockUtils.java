package com.poixson.utils;

import static com.poixson.utils.Utils.IsEmpty;

import java.util.concurrent.atomic.AtomicReference;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.poixson.tools.Keeper;


public final class BlockUtils {
	private BlockUtils() {}
	static { Keeper.add(new BlockUtils()); }



	public static BlockData StringToBlockData(final AtomicReference<String> atomic, final String def,
			final String...tags) {
		final String blockStr = atomic.get();
		return (IsEmpty(blockStr) ? StringToBlockData(def, tags) : StringToBlockData(blockStr, tags));
	}
	public static BlockData StringToBlockData(final String blockStr,
			final String...tags) {
		String str = blockStr;
		if (!IsEmpty(tags)) {
			final int size = tags.length;
			if (size % 2 != 0) throw new IllegalArgumentException("Invalid tags");
			for (int i=0; i<size; i+=2)
				str = str.replace(tags[i], tags[i+1]);
		}
		return Bukkit.createBlockData(str);
	}
	public static Material StringToMaterial(final AtomicReference<String> atomic, final String def) {
		String type = atomic.get();
		if (IsEmpty(type))
			type = def;
		final int pos = type.indexOf('[');
		if (pos != -1)
			type = type.substring(0, pos-1);
		return Material.matchMaterial(type);
	}



	// custom item model
	public static int GetCustomModel(final ItemStack stack) {
		if (stack.hasItemMeta()) {
			final ItemMeta meta = stack.getItemMeta();
			if (meta.hasCustomModelData())
				return meta.getCustomModelData();
		}
		return 0;
	}



	public static boolean IsSign(final Material type) {
		if (IsWallSign(type))        return true;
		if (IsStandingSign(type))    return true;
		if (IsHangingSign(type))     return true;
		if (IsHangingWallSign(type)) return true;
		return false;
	}
	public static boolean IsWallSign(final Material type) {
		switch (type) {
		case ACACIA_WALL_SIGN:
		case BAMBOO_WALL_SIGN:
		case BIRCH_WALL_SIGN:
		case CRIMSON_WALL_SIGN:
		case DARK_OAK_WALL_SIGN:
		case JUNGLE_WALL_SIGN:
		case MANGROVE_WALL_SIGN:
		case OAK_WALL_SIGN:
		case SPRUCE_WALL_SIGN:
		case WARPED_WALL_SIGN:
			return true;
		default: break;
		}
		return false;
	}
	public static boolean IsStandingSign(final Material type) {
		switch (type) {
		case ACACIA_SIGN:
		case BAMBOO_SIGN:
		case BIRCH_SIGN:
		case CRIMSON_SIGN:
		case DARK_OAK_SIGN:
		case JUNGLE_SIGN:
		case MANGROVE_SIGN:
		case OAK_SIGN:
		case SPRUCE_SIGN:
		case WARPED_SIGN:
			return true;
		default: break;
		}
		return false;
	}
	public static boolean IsHangingSign(final Material type) {
		switch (type) {
		case ACACIA_HANGING_SIGN:
		case BAMBOO_HANGING_SIGN:
		case BIRCH_HANGING_SIGN:
		case CRIMSON_HANGING_SIGN:
		case DARK_OAK_HANGING_SIGN:
		case JUNGLE_HANGING_SIGN:
		case MANGROVE_HANGING_SIGN:
		case OAK_HANGING_SIGN:
		case SPRUCE_HANGING_SIGN:
		case WARPED_HANGING_SIGN:
			return true;
		default: break;
		}
		return false;
	}
	public static boolean IsHangingWallSign(final Material type) {
		switch (type) {
		case ACACIA_WALL_HANGING_SIGN:
		case BAMBOO_WALL_HANGING_SIGN:
		case BIRCH_WALL_HANGING_SIGN:
		case CRIMSON_WALL_HANGING_SIGN:
		case DARK_OAK_WALL_HANGING_SIGN:
		case JUNGLE_WALL_HANGING_SIGN:
		case MANGROVE_WALL_HANGING_SIGN:
		case OAK_WALL_HANGING_SIGN:
		case SPRUCE_WALL_HANGING_SIGN:
		case WARPED_WALL_HANGING_SIGN:
			return true;
		default: break;
		}
		return false;
	}
	public static boolean IsButton(final Material type) {
		switch (type) {
		case STONE_BUTTON:
		case OAK_BUTTON:
		case SPRUCE_BUTTON:
		case BIRCH_BUTTON:
		case JUNGLE_BUTTON:
		case ACACIA_BUTTON:
		case DARK_OAK_BUTTON:
		case MANGROVE_BUTTON:
		case CHERRY_BUTTON:
		case BAMBOO_BUTTON:
		case CRIMSON_BUTTON:
		case WARPED_BUTTON:
		case POLISHED_BLACKSTONE_BUTTON:
			return true;
		default: break;
		}
		return false;
	}



}
