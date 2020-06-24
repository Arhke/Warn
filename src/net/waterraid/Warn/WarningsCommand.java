package net.waterraid.Warn;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarningsCommand implements CommandExecutor {
    Main _plugin;

    public WarningsCommand(Main instance) {
        super();
        _plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) {
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage("You may not run this command");
                return true;
            }
            commandSender.sendMessage(_plugin.getDataYML().getWarnInfo((Player) commandSender));
        } else {
            if (!commandSender.isOp() && !commandSender.hasPermission("Warn.warnings")) {
                commandSender.sendMessage("No Permission.");
                return true;
            } else {
                commandSender.sendMessage(_plugin.getDataYML().getWarnInfo(Bukkit.getOfflinePlayer(args[0])));
                return true;
            }
        }
        return true;
    }
}
