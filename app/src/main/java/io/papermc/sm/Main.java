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

    public static final int intervalInSeconds = 3;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        
        MemoryBar.run(this);
        TpsBar.run(this);

        this.getCommand("toggleram").setExecutor(new CommandToggleRam());
        this.getCommand("toggletps").setExecutor(new CommandToggleTps());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        BossBar memoryBar = MemoryBar.memBar;
        BossBar tpsBar = TpsBar.tpsBar;

        Player player = event.getPlayer();
        player.sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));

        memoryBar.addPlayer(player);
        tpsBar.addPlayer(player);
    }

}