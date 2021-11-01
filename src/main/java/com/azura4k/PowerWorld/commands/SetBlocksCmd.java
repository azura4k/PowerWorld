package com.azura4k.PowerWorld.commands;

import cn.nukkit.block.Block;
import cn.nukkit.blockstate.BlockState;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
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
        //If args is over one in length, error
        if (args.length == 0){
            return false;
        }
        if (args.length > 1){
            return false;
        }

        //Check for Colen,if not then just pass on
        String[] BlockData;
        if ( args[0].contains(":") ){
            //Parse Data
            BlockData = args[0].split(":");
            int BlockID = Integer.valueOf(BlockData[0]);
            int Meta = Integer.valueOf(BlockData[1]);

            Block SetBlock = Block.get(BlockID, Meta);
            PC.StoreBlock(SetBlock);
        }
        else
        {
            BlockData = args;
            int BlockID = Integer.valueOf(BlockData[0]);
            Block SetBlock = Block.get(BlockID);
            PC.StoreBlock(SetBlock);
        }
        PC.SetBlocks(commandSender.getServer().getPlayer(commandSender.getName()));
        return true;
    }
}
