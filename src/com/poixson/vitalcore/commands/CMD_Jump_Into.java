package com.poixson.vitalcore.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.poixson.tools.commands.pxnCommandRoot;
import com.poixson.vitalcore.VitalCorePlugin;


// /jump-into
public class CMD_Jump_Into extends pxnCommandRoot {



	public CMD_Jump_Into(final VitalCorePlugin plugin) {
		super(
			plugin,
			"pxn", // namespace
			"Teleport through the location you are looking at.", // desc
			null, // usage
			"pxn.cmd.jump", // perm
			// labels
			"ji",
			"jumpinto",
			"jump-into",
			"jumpthrough",
			"jump-through"
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
