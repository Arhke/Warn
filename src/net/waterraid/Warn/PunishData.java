package net.waterraid.Warn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PunishData {
    String _type;
    String _notes;
    public PunishData (String type, String notes, String who){
        _type = type;
        if (who != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");

            LocalDateTime now = LocalDateTime.now();
            _notes = dtf.format(now)+" at "+ dtf2.format(now) + " by " + who + "(" + notes + ")";
        }else {
            _notes = notes;
        }
    }
    public String getType() {
        return _type;
    }
    public String getNotes() {
        return _notes;
    }
    @Override
    public String toString() {
        return _notes;
    }
}
