package com.poixson.pluginlib.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.poixson.pluginlib.pxnPluginLib;
import com.poixson.tools.commands.pxnCommandRoot;


// /sethome
public class Command_Home_Set extends pxnCommandRoot {



	public Command_Home_Set(final pxnPluginLib plugin) {
		super(
			plugin,
			"pxn", // namespace
			"Save a home location.", // desc
			null, // usage
			"pxn.cmd.home", // perm
			new String[] { // labels
				"sethome",  "set-home",
				"savehome", "save-home"
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
