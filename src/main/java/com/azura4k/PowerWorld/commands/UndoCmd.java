package com.azura4k.PowerWorld.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.azura4k.PowerWorld.PowerWorldCommon;

public class UndoCmd extends Command {
    PowerWorldCommon PC =  new PowerWorldCommon();
    public UndoCmd(){
        super("/undo");
    }
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        Player player = commandSender.getServer().getPlayerExact(commandSender.getName());
        if (args.length == 0 || args[0] == null || args[0] == ""){
            //Assume latest Undo
            return PC.RestoreUndoCmd(player, 0);
        }
        int Sequence = Integer.parseInt(args[0]);
            return PC.RestoreUndoCmd(player, Sequence);
        }


    }
