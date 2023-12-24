package io.papermc.sm;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MemoryBar {
    
    public static BossBar memBar = null;

    public static void runRamBar(Plugin plugin) {
        memBar = Bukkit.createBossBar("", BarColor.GREEN, BarStyle.SOLID);
        memBar.setStyle(BarStyle.SEGMENTED_20);
        new BukkitRunnable() {
            @Override
            public void run() {

                String memUsed = MemoryCalc.getMemUsed();
                String memTotal = MemoryCalc.getMemTotal();
                double percentMemUsed = MemoryCalc.getPercentMemUsed();
                int percentMemUsedFormatted = (int)(percentMemUsed * 100);
                memBar.setTitle("Server Memory: " + memUsed + " of " + memTotal + " used (" + percentMemUsedFormatted + "%)");
                memBar.setProgress(percentMemUsed);
                if(percentMemUsed >= 0.8) {
                    memBar.setColor(BarColor.RED);
                }
                else if(percentMemUsed >= 0.5) {
                    memBar.setColor(BarColor.YELLOW);
                }
                else {
                    memBar.setColor(BarColor.GREEN);
                }

            }
        }.runTaskTimer(plugin, 0L, Helper.getTicksFromSeconds(Main.interval));
    }

}
