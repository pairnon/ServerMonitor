package io.papermc.sm.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.papermc.sm.MemoryBar;

@SuppressWarnings("deprecation")
public class CommandToggleRam implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            BossBar memBar = MemoryBar.memoryBar;
            List<Player> players = memBar.getPlayers();

            for(Player p : players) {
                if (p.equals(player)) {
                    memBar.removePlayer(player);
                    String message = ChatColor.GREEN + "Turned OFF memory usage bar";
                    player.sendMessage(message);
                    return true;
                }
            }
            memBar.addPlayer(player);
            String message = ChatColor.GREEN + "Turned ON memory usage bar";
            player.sendMessage(message);
        }
        return true;
    }
}