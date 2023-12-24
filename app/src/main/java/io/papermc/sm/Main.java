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

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        player.sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
        player.sendMessage(Component.text(Memory.getMemUsed() + " MB used"));
        player.sendMessage(Component.text(Memory.getMemTotal() + " MB alloc"));

        BossBar memBossBar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID);
        memBossBar.addPlayer(player);
        
        new BukkitRunnable() {
            @Override
            public void run() {
                
                memBossBar.setTitle("" + Memory.getMemUsed() + " MB used");

                if (!checkPlayerExists(player)) {
                    this.cancel();
                }
            }
        }.runTaskTimer(this, 0L, 40L);


    }

    public boolean checkPlayerExists(Player player) {
        return (player != null && player.isOnline());
    }

}