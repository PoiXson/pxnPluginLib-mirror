package com.poixson.pluginlib.commands;

import static com.poixson.utils.BukkitUtils.RestPlayer;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.poixson.pluginlib.pxnPluginLib;
import com.poixson.tools.commands.pxnCommandRoot;


// /rest
public class Command_Rest extends pxnCommandRoot {



	public Command_Rest(final pxnPluginLib plugin) {
		super(
			plugin,
			"pxn", // namespace
			"Restore full rest.", // desc
			null, // usage
			"pxn.cmd.rest", // perm
			new String[] { // labels
				"rest"
			}
		);
	}



	@Override
	public boolean onCommand(final CommandSender sender, final String[] args) {
		final Player player = (sender instanceof Player ? (Player)sender : null);
		final int num_args = args.length;
		// other players
		if (num_args > 0) {
			if (!sender.hasPermission("pxn.cmd.rest.other"))
				return false;
			int count = 0;
			LOOP_ARG:
			for (final String arg : args) {
				final Player p = Bukkit.getPlayer(arg);
				if (p == null) {
					sender.sendMessage(String.format("%sPlayer not found: %s", ChatColor.GOLD, arg));
					continue LOOP_ARG;
				}
				RestPlayer(p);
				p.sendMessage(ChatColor.GOLD+"You are rested");
				count++;
			}
			if (count > 0) {
				sender.sendMessage(String.format(
					"%sRested %d player%s",
					ChatColor.AQUA,
					Integer.valueOf(count),
					(count == 1 ? "" : "s")
				));
				return true;
			}
		// single player
		} else {
			if (!sender.hasPermission("pxn.cmd.rest"))
				return false;
			if (player == null) {
				sender.sendMessage("Cannot rest console");
				return true;
			}
			RestPlayer(player);
			sender.sendMessage(ChatColor.GOLD+"You are rested");
			return true;
		}
		return false;
	}



	@Override
	public List<String> onTabComplete(final CommandSender sender, final String[] args) {
		return this.onTabComplete_Players(args);
	}



}
