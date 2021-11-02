package com.azura4k.PowerWorld;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PowerWorldCommon extends PowerWorld {

    public int WandID;

    public PowerWorldCommon() {
        //Setup All Config stuff here
        this.WandID = 288;
    }

    public boolean WandisSelected(Player player) {
        //Returns false if both are not true.
        return player.getInventory().getItemInHand().getId() == this.WandID || player.getInventory().getItemInHand().getCustomName().equals("Magic Wand");
    }
}