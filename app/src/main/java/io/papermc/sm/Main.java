package io.papermc.sm;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener {

    // For status updates, in seconds
    final int interval = 3;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        player.sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
        runRamBar(player);
    }

    public void runRamBar(Player player) {
        BossBar memBossBar = Bukkit.createBossBar("", BarColor.GREEN, BarStyle.SOLID);
        memBossBar.addPlayer(player);
        new BukkitRunnable() {
            @Override
            public void run() {

                String memUsed = Memory.getMemUsed();
                String memTotal = Memory.getMemTotal();
                double percentMemUsed = Memory.getPercentMemUsed();
                memBossBar.setTitle(memUsed + " of " + memTotal + " used");
                memBossBar.setProgress(percentMemUsed);
                if(percentMemUsed >= 0.8) {
                    memBossBar.setColor(BarColor.RED);
                }
                else if(percentMemUsed >= 0.5) {
                    memBossBar.setColor(BarColor.YELLOW);
                }
                else {
                    memBossBar.setColor(BarColor.GREEN);
                }

                if (!checkPlayerExists(player)) {
                    this.cancel();
                }
            }
        }.runTaskTimer(this, 0L, getTicksFromSeconds(interval));
    }

    public long getTicksFromSeconds(int seconds) {
        return (long)seconds * 20;
    }

    public boolean checkPlayerExists(Player player) {
        return (player != null && player.isOnline());
    }

}