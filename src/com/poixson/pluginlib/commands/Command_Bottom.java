package com.poixson.pluginlib.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.poixson.pluginlib.pxnPluginLib;
import com.poixson.tools.commands.pxnCommandRoot;


// /bottom
public class Command_Bottom extends pxnCommandRoot {



	public Command_Bottom(final pxnPluginLib plugin) {
		super(
			plugin,
			"pxn", // namespace
			"Teleport to the lowest open space.", // desc
			null, // usage
			"pxn.cmd.bottom", // perm
			new String[] { // labels
				"bottom"
			}
		);
	}



	@Override
	public boolean onCommand(final CommandSender sender, final String[] args) {
System.out.println("COMMAND:"); for (final String arg : args) System.out.println("  "+arg);
return false;
	}



	@Override
	public List<String> onTabComplete(final CommandSender sender, final String[] args) {
//TODO
System.out.println("TAB:"); for (final String arg : args) System.out.println("  "+arg);
return null;
	}



}
