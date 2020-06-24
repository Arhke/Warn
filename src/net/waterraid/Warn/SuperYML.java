package net.waterraid.Warn;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SuperYML {
    Main _plugin;
    YamlConfiguration _config;
    File _file;
    public SuperYML(Main instance, File file) {
        if (!file.exists()) {
            throw new RuntimeException();
        }
        _file = file;
        _plugin = instance;
        _config = YamlConfiguration.loadConfiguration(file);
    }

    public Main getPlugin() {
        return _plugin;
    }

    public YamlConfiguration getConfig() {
        return _config;
    }

    public void save() {
        try {
            getConfig().save(_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
