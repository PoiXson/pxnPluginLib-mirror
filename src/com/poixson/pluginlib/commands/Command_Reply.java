package com.poixson.pluginlib.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.poixson.pluginlib.pxnPluginLib;
import com.poixson.tools.commands.pxnCommandRoot;


// /reply
public class Command_Reply extends pxnCommandRoot {



	public Command_Reply(final pxnPluginLib plugin) {
		super(
			plugin,
			"pxn", // namespace
			"Reply to the last player to send you a message.", // desc
			null, // usage
			"pxn.cmd.msg", // perm
			new String[] { // labels
				"r", "reply"
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
