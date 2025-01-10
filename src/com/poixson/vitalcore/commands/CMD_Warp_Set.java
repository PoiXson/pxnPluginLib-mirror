package com.poixson.vitalcore.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.poixson.tools.commands.pxnCommandRoot;
import com.poixson.vitalcore.VitalCorePlugin;


// /setwarp
public class CMD_Warp_Set extends pxnCommandRoot {



	public CMD_Warp_Set(final VitalCorePlugin plugin) {
		super(
			plugin,
			"pxn", // namespace
			"Set a warp location", // desc
			null, // usage
			"pxn.cmd.setwarp", // perm
			// labels
			"setwarp",
			"set-warp"
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
