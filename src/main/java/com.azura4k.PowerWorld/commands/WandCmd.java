package com.azura4k.PowerWorld.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import com.azura4k.PowerWorld.PowerWorldAPI;

public class WandCmd extends Command {
    public WandCmd() {

        super("/wand", "/Wand. Make sure you have no items in hand");
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        //Grab Player for Command
        Player player = commandSender.getServer().getPlayerExact(commandSender.getName());
        //Check for Permissions
        if (player.hasPermission(PowerWorldAPI.permissionName)){
            //Check for Item in hand
            if(player.getInventory().getItemInHand().isNull()){

                //Set Wand Attributes
                Item wand = new Item(PowerWorldAPI.wandID);
                wand.addEnchantment(Enchantment.getEnchantment(PowerWorldAPI.wandEnchantID));
                wand.setCustomName(PowerWorldAPI.wandCustomName);
                wand.setLore(PowerWorldAPI.defaultLore);

                //Player Interaction
                player.sendMessage("With great power comes great responsibility");
                player.getInventory().setItemInHand(wand);
                player.getInventory().setItemInHand(wand);return true;
            } else {
                player.sendMessage("Please Clear your hand of any Items before trying again");return false;}
        } else {return false;}
    }
}
