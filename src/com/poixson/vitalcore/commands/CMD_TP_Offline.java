package com.poixson.vitalcore.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.poixson.tools.commands.pxnCommandRoot;
import com.poixson.vitalcore.VitalCorePlugin;


// /tp-offline
public class CMD_TP_Offline extends pxnCommandRoot {



	public CMD_TP_Offline(final VitalCorePlugin plugin) {
		super(
			plugin,
			"pxn", // namespace
			"Teleport to the last known location of a player.", // desc
			null, // usage
			"pxn.cmd.tp.offline", // perm
			// labels
			"tpoffline",
			"tp-offline"
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
