package com.azura4k.PowerWorld.listeners;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.math.Vector3;
import com.azura4k.PowerWorld.PowerWorldCommon;

import java.util.ArrayList;
import java.util.List;

public class WandListener implements Listener {
    PowerWorldCommon PC = new PowerWorldCommon();

    Vector3 PrimaryPoint = new Vector3();
    Vector3 SecondaryPoint = new Vector3();

    @EventHandler
    public void onWandClick(PlayerInteractEvent event){
        if (PC.WandisSelected(event.getPlayer())){
            switch (event.getAction()){
                case RIGHT_CLICK_BLOCK -> {
                    PrimaryPoint.x = event.getBlock().getFloorX();
                    PrimaryPoint.y = event.getBlock().getFloorY();
                    PrimaryPoint.z = event.getBlock().getFloorZ();
                    event.getPlayer().sendMessage("Primary Location Selected: X" + PrimaryPoint.x + " Y" + PrimaryPoint.y + " Z" + PrimaryPoint.z);
                    PC.StorePosLocation(this.PrimaryPoint, this.SecondaryPoint);
                }
                case LEFT_CLICK_BLOCK -> {
                    SecondaryPoint.x = event.getBlock().getFloorX();
                    SecondaryPoint.y = event.getBlock().getFloorY();
                    SecondaryPoint.z = event.getBlock().getFloorZ();
                    event.getPlayer().sendMessage("Secondary Location Selected: X" + SecondaryPoint.x + " Y" + SecondaryPoint.y + " Z" + SecondaryPoint.z);
                    PC.StorePosLocation(this.PrimaryPoint, this.SecondaryPoint);
                }
            }
        }
    }
}