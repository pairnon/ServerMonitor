package io.papermc.sm;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.papermc.sm.commands.*;

public class Main extends JavaPlugin implements Listener {

    public static final int intervalInSeconds = 3;

    private static boolean showStatsByDefault;

    private FileConfiguration config;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        this.saveDefaultConfig();
        config = this.getConfig();
        showStatsByDefault = config.getBoolean("show-stats-by-default");
        
        MemoryBar.run(this);
        TpsBar.run(this);

        this.getCommand("toggleram").setExecutor(new CommandToggleRam());
        this.getCommand("toggletps").setExecutor(new CommandToggleTps());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (showStatsByDefault) {
            BossBar memoryBar = MemoryBar.memoryBar;
            BossBar tpsBar = TpsBar.tpsBar;
    
            Player player = event.getPlayer();
    
            memoryBar.addPlayer(player);
            tpsBar.addPlayer(player);
        }
    }
}