package com.poixson.pluginlib.commands;

import static com.poixson.utils.BukkitUtils.OpenWorkbench;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.poixson.pluginlib.pxnPluginLib;
import com.poixson.tools.commands.pxnCommandRoot;


// /workbench
public class Command_Workbench extends pxnCommandRoot {



	public Command_Workbench(final pxnPluginLib plugin) {
		super(
			plugin,
			"pxn", // namespace
			null, // desc
			null, // usage
			"pxn.cmd.workbench", // perm
			new String[] { // labels
				"workbench",     "work-bench",
				"craftingtable", "crafting-table"
			}
		);
	}



	@Override
	public boolean onCommand(final CommandSender sender, final String[] args) {
		final Player player = (sender instanceof Player ? (Player)sender : null);
		final int num_args = args.length;
		// other players
		if (num_args > 0) {
			int count = 0;
			ARG_LOOP:
			for (final String arg : args) {
				final Player p = Bukkit.getPlayer(arg);
				if (p == null) {
					sender.sendMessage(String.format("%sPlayer not found: %s", ChatColor.GOLD, arg));
					continue ARG_LOOP;
				}
				OpenWorkbench(p);
				count++;
			}
			if (count > 0) {
				sender.sendMessage(String.format(
					"Opened Workbench for %d player%s",
					Integer.valueOf(count),
					(count == 1 ? "" : "s")
				));
				return true;
			}
		// single player
		} else {
			OpenWorkbench(player);
			return true;
		}
		return false;
	}



	@Override
	public List<String> onTabComplete(final CommandSender sender, final String[] args) {
		final String first = (args.length == 0 ? "" : args[0]);
		return this.onTabComplete_Players(first);
	}



}