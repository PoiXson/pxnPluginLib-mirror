package com.poixson.pluginlib.commands;

import org.bukkit.command.CommandSender;

import com.poixson.pluginlib.pxnPluginLib;
import com.poixson.tools.commands.pxnCommandRoot;


public class Command_MOTD extends pxnCommandRoot {



	public Command_MOTD(final pxnPluginLib plugin) {
		super(
			plugin,
			null, // desc
			null, // usage
			"pxn.cmd.motd", // perm
			"motd"
		);
	}



	@Override
	public boolean onCommand(final CommandSender sender, final String[] args) {
System.out.println("COMMAND:"); for (final String arg : args) System.out.println("  "+arg);
return false;
	}



}
