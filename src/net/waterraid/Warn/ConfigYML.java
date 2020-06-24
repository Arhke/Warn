package net.waterraid.Warn;

import java.io.File;
import java.util.Set;

public class ConfigYML extends SuperYML {
    Set<String> _types;

    public ConfigYML(Main instance, File file) {
        super(instance, file);
        _types = getConfig().getKeys(false);
    }

    public void runCommands(String type, int times, String player, String notes, String issuer) {
        if (!getConfig().contains(type + "." + times) || !getConfig().isList(type + "." + times)) {
            System.err.println("Im going to scream at you for not putting in a command config for type" + type + " and times " + times);

            return;
        }
        for (String command : getConfig().getStringList(type + "." + times)) {
            _plugin.getServer().dispatchCommand(_plugin.getServer().getConsoleSender(), command.replaceAll("\\{player}", player)
                    .replaceAll("\\{notes}", notes).replaceAll("\\{issuer}", issuer).replaceAll("\\{type}", type));
        }
    }

    public boolean isValidType(String type) {
        return _types.contains(type);
    }


}
