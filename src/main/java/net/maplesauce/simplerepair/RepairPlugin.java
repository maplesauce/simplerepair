package net.maplesauce.simplerepair;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.plugin.java.JavaPlugin;

public class RepairPlugin extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("repair")) {
            if (!player.hasPermission("simplerepair.use")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
                return true;
            }
            repairAll(player);
            player.sendMessage(ChatColor.GREEN + "You have repaired your tools and armor!");
            return true;
        }
        return false;
    }

    private void repairAll(Player player) {
        for (ItemStack items : player.getInventory().getContents()) {
            if (items instanceof Repairable) {
                items.setDurability((short)0);
            }
        }
        for (ItemStack items : player.getInventory().getArmorContents()) {
            if (items instanceof Repairable) {
                items.setDurability((short)0);
            }
        }
    }
}
