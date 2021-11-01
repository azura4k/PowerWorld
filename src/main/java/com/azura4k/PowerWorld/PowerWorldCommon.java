package com.azura4k.PowerWorld;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PowerWorldCommon extends PowerWorld{

    public int WandID;
    public PowerWorldCommon(){
        //Setup All Config stuff here
        this.WandID = 288;
    }
    public boolean WandisSelected(Player player){
        //Returns false if both are not true.
        return player.getInventory().getItemInHand().getId() == this.WandID || player.getInventory().getItemInHand().getCustomName().equals("Magic Wand");
    }

    public void SetBlocks(Player sender){
        Level world = sender.level;
        Vector3 Pos1 = GetPos1();
        Vector3 Pos2 = GetPos2();
        Block TargetBlock = getBlocks().get(0);
        double Volume = CalcVolume(Pos1, Pos2);

        int x = (int) Math.floor(Pos1.x);
        int y = (int) Math.floor(Pos1.y);
        int z = (int) Math.floor(Pos1.z);

        int x1 = (int) Math.floor(Pos2.x);
        int y1 = (int) Math.floor(Pos2.y);
        int z1 = (int) Math.floor(Pos2.z);


        //Insert into Coordlist
        List CoordList;
        CoordList = new ArrayList<Vector3>();

        //For X to calculate z
        for (int i=0; i < CalcDistance(x, x1); i++){
                Vector3 temp = new Vector3();
                temp.x = x +1;
                temp.y = y;
                temp.z = z;
                CoordList.add(temp);
            for (i = 1; i < CalcDistance(z, z1); i++) {
                Vector3 temp2 = new Vector3();
                temp2.x = temp.x;
                temp2.y = temp.y;
                temp2.z = z + i;
                CoordList.add(temp2);
            }
        }
        //Place Blocks In CoordList
        for (int i = CoordList.size(); i < 0; i--){
            world.setBlock((Vector3) CoordList.get(i), TargetBlock);
        }
















    }

    //Calc Distance Between Two Points
    public int CalcDistance(int Point1, int Point2){
        if (Point1 > Point2){
            return Point1 + 1 - Point2;
        }
        else {
            return Point2 + 1 - Point1;
        }
    }
    //Calculate Volume
    public double CalcVolume(Vector3 Pos1, Vector3 Pos2){
        return CalcDistance(Pos1.getFloorX(), Pos2.getFloorX()) * CalcDistance(Pos1.getFloorY(), Pos2.getFloorY()) * CalcDistance(Pos1.getFloorZ(), Pos2.getFloorZ());
    }
    //Calculate Area
    public double CalcArea(Vector3 Pos1, Vector3 Pos2){
        if (CalcDistance(Pos1.getFloorX(), Pos2.getFloorX()) == 0) {
            return CalcDistance(Pos1.getFloorZ(), Pos2.getFloorZ()) * CalcDistance(Pos1.getFloorY(), Pos2.getFloorY());
        }
        else if(CalcDistance(Pos1.getFloorY(), Pos2.getFloorY()) == 0) {
            return CalcDistance(Pos1.getFloorZ(), Pos2.getFloorZ()) * CalcDistance(Pos1.getFloorX(), Pos2.getFloorX());
        }
        else {
            return CalcDistance(Pos1.getFloorX(), Pos2.getFloorX()) * CalcDistance(Pos1.getFloorY(), Pos2.getFloorY());
        }
    }
}