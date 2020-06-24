package net.waterraid.Warn;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarnReload implements CommandExecutor {
    Main _plugin;
    public WarnReload(Main instance){
        super();
        _plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!commandSender.isOp() && !commandSender.hasPermission("Warn.reload")) {
            commandSender.sendMessage("No Permission.");
            return true;
        }
        _plugin.reloadConfig();
        return true;

    }
}
