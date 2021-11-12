package com.azura4k.PowerWorld;

import cn.nukkit.Player;
import cn.nukkit.item.Item;

public class PowerWorldAPI {
    //Declare Wand Stuff
    public static final int wandID = 271;
    public static final int wandEnchantID = 15;
    public static final String wandCustomName = "Power Wand";
    public static final String permissionName = "PowerWorld.cmd.admin";
    public static final String defaultLore = "Use it well, use it wisely.";

    public void PowerWorldAPI(){

    }
    //Wand Commands
    public boolean AuthIsWandHolding(Player player){
        Item wand = player.getInventory().getItemInHand();
        if (
                wand.getCustomName() == wandCustomName
                && wand.hasEnchantment(wandEnchantID)
                && player.hasPermission(permissionName)
        ){
           return true;
        }
        else {
            return false;
        }
    }

}
