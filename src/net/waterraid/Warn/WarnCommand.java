package net.waterraid.Warn;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class WarnCommand implements CommandExecutor {
    Main _plugin;
    public WarnCommand(Main instance){
        super();
        _plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!commandSender.isOp() && !commandSender.hasPermission("Warn.warn")){
            commandSender.sendMessage("No Permission.");
            return true;
        }
        if (args.length < 3){
            commandSender.sendMessage("Invalid Number of Args");
            return true;
        }
        OfflinePlayer ofp = Bukkit.getOfflinePlayer(args[0]);
        String type = args[1];
        if (!_plugin.getConfigYML().isValidType(type)){
            commandSender.sendMessage("Invalid Type!");
            return true;
        }
        String notes = "";
        for(int i = 2; i < args.length; i++) {
            notes += args[i] + " ";
        }
        notes = notes.substring(0, notes.length()-1);
        _plugin.getDataYML().addWarn(ofp.getUniqueId(), type, args[0], notes, commandSender.getName() == null?"null":commandSender.getName());
        commandSender.sendMessage("Warned Player " + args[0] + " for "+type);
        commandSender.sendMessage(notes);
        return true;
    }
}
