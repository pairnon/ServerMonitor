package io.papermc.sm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

public class TpsCalculator extends JavaPlugin {
   private static int lastSecond = -1;
   private static long tickCount = 0;

   public static double tps = 0;

   public static void startCalculatingTps(Plugin plugin) {

       Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
           @Override
           public void run() {
               // Increment the tick count
               tickCount++;

               // Check if a second has passed
               if (lastSecond != Calendar.getInstance().get(Calendar.SECOND)) {
                  // Calculate TPS
                  tps = tickCount;

                  // Reset the tick count and update the last second
                  tickCount = 0;
                  lastSecond = Calendar.getInstance().get(Calendar.SECOND);

               }
           }
       }, 0L, 1L);
    }

}