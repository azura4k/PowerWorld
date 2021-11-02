package com.azura4k.PowerWorld.commands;

import cn.nukkit.block.Block;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import com.azura4k.PowerWorld.PowerWorldCommon;

public class ReplaceBlocksCmd extends Command {
    PowerWorldCommon PC =  new PowerWorldCommon();
    public ReplaceBlocksCmd() {
        super("/replace");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        //PART ONE - GET INFORMATION
        //If args is over one in length, error
        if (args.length == 0){
            return false;
        }


        Block TargetBlock;
        Block ReplacementBlock;
        //For TargetBlock
        String[] BlockData;
        if ( args[0].contains(":") ){
            //Parse Data
            BlockData = args[0].split(":");
            int BlockID = Integer.parseInt(BlockData[0]);
            int Meta = Integer.parseInt(BlockData[1]);
            TargetBlock = Block.get(BlockID, Meta);
        }
        else {
            BlockData = args;
            int BlockID = Integer.parseInt(BlockData[0]);
            TargetBlock = Block.get(BlockID);
        }
        //For ReplacementBlock
        String[] BlockData2;
        if ( args[1].contains(":") ){
            //Parse Data
            BlockData2 = args[1].split(":");
            int BlockID = Integer.parseInt(BlockData2[0]);
            int Meta = Integer.parseInt(BlockData2[1]);
            ReplacementBlock = Block.get(BlockID, Meta);
        }
        else {
            BlockData2 = args;
            int BlockID = Integer.parseInt(BlockData2[1]);
            ReplacementBlock = Block.get(BlockID);
        }

        //PART TWO - GET PLAYER INFO
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
                    //Test if Replacement is specific or general
                    if (ReplacementBlock.getFullId() > 0){
                        //Get specific
                        if (world.getBlock(x, y, z).getFullId() == ReplacementBlock.getFullId()) {
                            world.setBlock(x, y, z, TargetBlock, true, true);
                        }
                    }
                    else if (world.getBlock(x, y, z).getId() == ReplacementBlock.getId()) {
                        world.setBlock(x, y, z, TargetBlock, true, true);
                    }
                }
            }
        }
        return true;
    }
}

