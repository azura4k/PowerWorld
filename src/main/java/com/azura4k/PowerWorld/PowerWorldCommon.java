package com.azura4k.PowerWorld;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class PowerWorldCommon extends PowerWorld {
    public int WandID;

    public PowerWorldCommon() {
        //Setup All Config stuff here
        this.WandID = 271;
    }
    public boolean WandisSelected(Player player) {
        //Returns false if both are not true.
        return player.getInventory().getItemInHand().getId() == this.WandID & player.getInventory().getItemInHand().getCustomName().equals("Magic Wand") & player.getInventory().getItemInHand().hasEnchantment(15);
    }


    //Needed for Wand Selector
    public void StorePosLocation(Vector3 Vector1, Vector3 Vector2, Player player) {
        Item wand = player.getInventory().getItemInHand();
        int[] Positions = new int[6];
        Positions[0] = Vector1.getFloorX();
        Positions[1] = Vector1.getFloorY();
        Positions[2] = Vector1.getFloorZ();
        Positions[3] = Vector2.getFloorX();
        Positions[4] = Vector2.getFloorY();
        Positions[5] = Vector2.getFloorZ();
        wand.setNamedTag(new CompoundTag().putIntArray("Coords", Positions));
        wand.setLore(
                "X:" + Positions[0] + " Y:" + Positions[1] + " Z:" + Positions[2]
                        + "\n X1:" + Positions[3] + " Y1:" + Positions[4] + " Z1:" + Positions[5]
        );
        wand.setCustomName("Magic Wand");
        wand.addEnchantment(Enchantment.getEnchantment(15));
        player.getInventory().setItemInHand(wand);
    }
    public final Vector3 GetPos1(Player player) {
        Vector3 temp = new Vector3();
        int[] pos1 = player.getInventory().getItemInHand().getNamedTag().getIntArray("Coords");
        temp.x = pos1[0];
        temp.y = pos1[1];
        temp.z = pos1[2];
        return temp;
    }
    public final Vector3 GetPos2(Player player) {
        Vector3 temp = new Vector3();
        int[] pos2 = player.getInventory().getItemInHand().getNamedTag().getIntArray("Coords");
        temp.x = pos2[3];
        temp.y = pos2[4];
        temp.z = pos2[5];
        return temp;
    }


    public void InsertFunctionHistory(Vector3 Pos1, Vector3 Pos2, Level world, Player player) {
        ArrayList<Block> Execution = new ArrayList<Block>();
        int minX = Math.min(Pos1.getFloorX(), Pos2.getFloorX());
        int minY = Math.min(Pos1.getFloorY(), Pos2.getFloorY());
        int minZ = Math.min(Pos1.getFloorZ(), Pos2.getFloorZ());
        int maxX = Math.max(Pos1.getFloorX(), Pos2.getFloorX());
        int maxY = Math.max(Pos1.getFloorY(), Pos2.getFloorY());
        int maxZ = Math.max(Pos1.getFloorZ(), Pos2.getFloorZ());
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlock(x, y, z);
                    Execution.add(block);
                }
            }
        }
        this.UndoList.add(Execution);
        //Down Shifts list as we go
        if (this.UndoList.size() > this.UndoStorageLimit) {
            ArrayList<ArrayList<Block>> Temp = new ArrayList<>();
            for (int i = 1; i < this.UndoList.size(); i++) {
                Temp.add(this.UndoList.get(i));
            }
            this.UndoList = Temp;
        }
    }

    public boolean RestoreUndoCmd(Player player, int Sequence) {
        //Sets Sequence = to the latest, minus one for
        //Index protection and minus sequence for step count
        Sequence = this.UndoList.size() - 1 - Sequence;
        if (Sequence > this.UndoList.size() || Sequence > this.UndoStorageLimit || Sequence < 0) {
            player.sendMessage("The sequence you entered overflows \nthe range of stored commands executed.");
            return false;
        }
        for (int i = 0; i < this.UndoList.get(Sequence).size(); i++){
            Block block = this.UndoList.get(Sequence).get(i);
            player.level.setBlock(block.getFloorX(), block.getFloorY(), block.getFloorZ(), block, true, true);
        }
        return true;
    }
}



