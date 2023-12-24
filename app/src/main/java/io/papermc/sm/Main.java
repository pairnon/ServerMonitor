package io.papermc.sm;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.papermc.sm.commands.*;

public class Main extends JavaPlugin implements Listener {

    // For status updates, in seconds
    public static final int interval = 3;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        
        MemoryBar.runRamBar(this);

        this.getCommand("toggleram").setExecutor(new CommandToggleRam());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        BossBar memBar = MemoryBar.memBar;

        Player player = event.getPlayer();
        player.sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));

        memBar.addPlayer(player);
    }

}