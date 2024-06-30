package io.papermc.sm;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MemoryBar {
    
    public static BossBar memoryBar = null;

    public static void run(Plugin plugin) {
        initializeMemoryBar();
        new BukkitRunnable() {
            @Override
            public void run() {
                updateMemoryBar();
            }

            private void updateMemoryBar() {
                String memUsed = MemoryCalc.getMemUsed();
                String memTotal = MemoryCalc.getMemTotal();
                double percentMemUsed = MemoryCalc.getPercentMemUsed();
                int percentMemUsedFormatted = (int)(percentMemUsed * 100);
                memoryBar.setTitle("Server Memory: " + memUsed + " of " + memTotal + " used (" + percentMemUsedFormatted + "%)");
                memoryBar.setProgress(percentMemUsed);
                setMemoryBarColor(percentMemUsed);
            }

            private void setMemoryBarColor(double percentMemUsed) {
                if(percentMemUsed >= 0.8) {
                    memoryBar.setColor(BarColor.RED);
                }
                else if(percentMemUsed >= 0.5) {
                    memoryBar.setColor(BarColor.YELLOW);
                }
                else {
                    memoryBar.setColor(BarColor.GREEN);
                }
            }
        }.runTaskTimer(plugin, 0L, Time.getTicksFromSeconds(Main.intervalInSeconds));
    }

    private static void initializeMemoryBar() {
        memoryBar = Bukkit.createBossBar("", BarColor.GREEN, BarStyle.SOLID);
        memoryBar.setStyle(BarStyle.SEGMENTED_20);
    }
}