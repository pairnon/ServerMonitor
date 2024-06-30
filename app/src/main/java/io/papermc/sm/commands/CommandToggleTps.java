package io.papermc.sm.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.papermc.sm.TpsBar;

@SuppressWarnings("deprecation")
public class CommandToggleTps implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            BossBar tpsBar = TpsBar.tpsBar;
            List<Player> players = tpsBar.getPlayers();

            for(Player p : players) {
                if (p.equals(player)) {
                    tpsBar.removePlayer(player);
                    String message = ChatColor.GREEN + "Turned OFF TPS bar";
                    player.sendMessage(message);
                    return true;
                }
            }
            tpsBar.addPlayer(player);
            String message = ChatColor.GREEN + "Turned ON TPS bar";
            player.sendMessage(message);
        }
        return true;
    }
}