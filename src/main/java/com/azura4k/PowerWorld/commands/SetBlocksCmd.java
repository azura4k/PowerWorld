package com.azura4k.PowerWorld.commands;

import cn.nukkit.block.Block;
import cn.nukkit.blockstate.BlockState;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import com.azura4k.PowerWorld.PowerWorldCommon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetBlocksCmd extends Command {
    PowerWorldCommon PC =  new PowerWorldCommon();

    public SetBlocksCmd() {
        super("/set");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        //Part ONE - PARSE COMMAND FOR INFO
        //If args is over one in length, error
        if (args.length == 0){
            return false;
        }
        if (args.length > 1){
            return false;
        }
        //Check for Colen,if not then just pass on
        String[] BlockData;
        Block SetBlock;
        if ( args[0].contains(":") ){
            //Parse Data
            BlockData = args[0].split(":");
            int BlockID = Integer.parseInt(BlockData[0]);
            int Meta = Integer.parseInt(BlockData[1]);

            SetBlock = Block.get(BlockID, Meta);
        }
        else
        {
            BlockData = args;
            int BlockID = Integer.parseInt(BlockData[0]);
            SetBlock = Block.get(BlockID);
        }

        //PART TWO - GATHER PLAYER INFO
        Level world = commandSender.getServer().getPlayer(commandSender.getName()).level;
        Vector3 Pos1 = PC.GetPos1();
        Vector3 Pos2 = PC.GetPos2();


        //PART THREE - PLACE BLOCKS IN WORLD
        //Curtsy of DaPorkChop - Thank You SOOOOO Much
        int minX = Math.min(Pos1.getFloorX(), Pos2.getFloorX());
        int minY = Math.min(Pos1.getFloorY(), Pos2.getFloorY());
        int minZ = Math.min(Pos1.getFloorZ(), Pos2.getFloorZ());
        int maxX = Math.max(Pos1.getFloorX(), Pos2.getFloorX());
        int maxY = Math.max(Pos1.getFloorY(), Pos2.getFloorY());
        int maxZ = Math.max(Pos1.getFloorZ(), Pos2.getFloorZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    world.setBlock(x, y, z, SetBlock, true, true);
                }
            }
        }
        return true;
    }
}
