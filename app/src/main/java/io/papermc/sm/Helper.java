package io.papermc.sm;

import org.bukkit.entity.Player;

// Class containing various helper methods
public class Helper {
    
    public static long getTicksFromSeconds(int seconds) {
        return (long)seconds * 20;
    }

    public static boolean checkPlayerExists(Player player) {
        return (player != null && player.isOnline());
    }

}
