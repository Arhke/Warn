package net.waterraid.Warn;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelWarningCommand implements CommandExecutor {
    Main _plugin;
    public DelWarningCommand(Main instance){
        super();
        _plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length != 3){
            commandSender.sendMessage("Wrong Number of Arguments");
            return true;
        }
        if (!commandSender.isOp() && !commandSender.hasPermission("Warn.delwarning")){
            commandSender.sendMessage("No Permission.");
            return true;
        }
        try{
            int index = Integer.parseInt(args[2]);
            commandSender.sendMessage(_plugin.getDataYML().removeWarning(Bukkit.getOfflinePlayer(args[0]), args[1], index));
            return true;
        }catch(NumberFormatException e){
            commandSender.sendMessage("Not a Valid Index Number");
            return true;
        }

    }
}
