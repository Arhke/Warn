package net.waterraid.Warn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class DataYML extends SuperYML {
    //UUID,
    private Map<String, Map<String, List<PunishData>>> warnMap;

    public DataYML(Main instance, File file) {
        super(instance, file);
        warnMap = new HashMap<>();
        for (String id : getConfig().getKeys(false)) {
            ConfigurationSection cs = getConfig().getConfigurationSection(id);

            Map<String, List<PunishData>> dataMap = new HashMap<>();
            warnMap.put(id, dataMap);
            for (String type : cs.getKeys(false)) {
                ConfigurationSection cs2 = cs.getConfigurationSection(type);
                List<PunishData> data = new ArrayList<>();
                dataMap.put(type, data);
                if (getPlugin().getConfigYML().isValidType(type)) {
                    cs2.getKeys(false).forEach((index) -> data.add(new PunishData(type, cs2.getString(index+""), null)));
                }

            }

        }

    }

    //uuid, type, notes
    public String getWarnInfo(OfflinePlayer player) {
        Map<String, List<PunishData>> data = warnMap.get(player.getUniqueId().toString());
        if (data == null || data.size() == 0) return ChatColor.GOLD + "No PunishData Available";
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.GOLD + "==========\nWarnings for " + player.getName() + "\n");
        for (Map.Entry<String, List<PunishData>> entry : data.entrySet()) {
            if (entry.getValue().size() == 0) {
                continue;
            }
            sb.append(entry.getKey() + ": " + entry.getValue().size() + "\n");
            for (int i = 0; i < entry.getValue().size(); i++) {
                sb.append((i + 1));
                sb.append(": " + entry.getValue().get(i).toString());
                sb.append("\n");
            }
        }
        sb.append("==========");
        return sb.toString();

    }

    public void addWarn(UUID uuid, String type, String who, String notes, String issuer) {
        Map<String, List<PunishData>> dataMap = warnMap.get(uuid.toString());
        if (dataMap == null) {
            dataMap = new HashMap<>();
            warnMap.put(uuid.toString(), dataMap);
        }
        List<PunishData> data = dataMap.get(type);
        if (data == null) {
            data = new ArrayList<>();
            dataMap.put(type, data);
        }
        data.add(new PunishData(type, notes, who));
        getPlugin().getConfigYML().runCommands(type, data.size(), who, notes, issuer);
    }

    public String removeWarning(OfflinePlayer player, String type, int index) {
        index--;
        if (index < 0) {
            return "Please enter a positive integer for the index";
        }
        Map<String, List<PunishData>> dataMap = warnMap.get(player.getUniqueId().toString());
        if (dataMap == null || dataMap.size() == 0) {
            return "Specified Player Don't got no warning.";
        }
        List<PunishData> data = dataMap.get(type);
        if (data == null) {
            return "Specified Type Does Not Apply to the Specified Player";
        }
        if (data.size() <= index) {
            return "Specified Index is not available.";
        }
        return "Removed Warning: " + data.remove(index);
    }

    public void save() {
        _config = new YamlConfiguration();
        for (Map.Entry<String, Map<String, List<PunishData>>> entry : warnMap.entrySet()) {
            for (Map.Entry<String, List<PunishData>> ent : entry.getValue().entrySet()) {
                for (int i = 0; i < ent.getValue().size(); i++) {
                    getConfig().set(entry.getKey() + "." + ent.getKey() + "." + i, ent.getValue().get(i).toString());
                }
            }
        }
        super.save();
    }

}
