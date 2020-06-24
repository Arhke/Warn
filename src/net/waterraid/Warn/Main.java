package net.waterraid.Warn;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Paths;

public class Main extends JavaPlugin {
    File configFile = Paths.get(getDataFolder().toString(), "config.yml").toFile();
    File dataFile = Paths.get(getDataFolder().toString(), "data.yml").toFile();
    ConfigYML config;
    DataYML data;
    @Override
    public void onEnable() {
        saveResource("data.yml", false);
        saveResource("config.yml", false);
        config = new ConfigYML(this, configFile);
        data = new DataYML(this, dataFile);
        getCommand("warn").setExecutor(new WarnCommand(this));
        getCommand("warnings").setExecutor(new WarningsCommand(this));;
        getCommand("delwarning").setExecutor(new DelWarningCommand(this));;
        getCommand("warnreload").setExecutor(new WarnReload(this));;

    }
    @Override
    public void onDisable() {
        getDataYML().save();
    }
    public ConfigYML getConfigYML() {
        return config;
    }
    public DataYML getDataYML() {
        return data;
    }
    public void reloadConfig() {
        config = new ConfigYML(this, configFile);
    }
}
