package io.papermc.sm;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TpsBar {
    
    public static BossBar tpsBar = null;

    public static void run(Plugin plugin) {
        initializeTpsBar();
        TpsCalculator.startCalculatingTps(plugin);
        new BukkitRunnable() {
            @Override
            public void run() {
                updateTpsBar();
            }

            private void updateTpsBar() {
                double tps = TpsCalculator.tps;

                tpsBar.setTitle("" + tps + " TPS");

                // Sometimes tps becomes > 20 after heavy load, 
                // causing an error when setting bar progress
                double progress = tps / 20;
                if (progress > 1.0) {
                    tpsBar.setProgress(1.0);
                } else tpsBar.setProgress(progress);

                setTpsBarColor(tps);
            }

            private void setTpsBarColor(double tps) {
                if( tps <= 15) {
                    tpsBar.setColor(BarColor.RED);
                }
                else if( tps <= 19) {
                    tpsBar.setColor(BarColor.YELLOW);
                }
                else {
                    tpsBar.setColor(BarColor.GREEN);
                }
            }
        }.runTaskTimer(plugin, 0L, Time.getTicksFromSeconds(Main.intervalInSeconds));
    }

    private static void initializeTpsBar() {
        tpsBar = Bukkit.createBossBar("", BarColor.GREEN, BarStyle.SOLID);
        tpsBar.setStyle(BarStyle.SEGMENTED_20);
    }

}
