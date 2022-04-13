package com.azura4k.PowerWorld.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import com.azura4k.PowerWorld.PowerWorldAPI;


public class WandPositionListener implements Listener {
    PowerWorldAPI Api = new PowerWorldAPI();
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        //Determine Type of Click
            Item wand =  player.getInventory().getItemInHand();
            if (event.getAction() == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK){
                player.sendMessage("Getting Coords");
            }
            else if (event.getAction() == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK){
                player.sendMessage("Getting Coords2");
        }

    }
}
