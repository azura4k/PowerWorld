package com.azura4k.PowerWorld.commands;


import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import com.azura4k.PowerWorld.PowerWorldCommon;

public class WandCmd extends Command{

    PowerWorldCommon PC = new PowerWorldCommon();

    public WandCmd() {
        super("/wand");
    }


    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender.hasPermission("op") || commandSender.isPlayer()){
            Player player = commandSender.getServer().getPlayerExact(commandSender.getName());
            Item wand = new Item(PC.WandID);
            wand.setCustomName("Magic Wand");
            wand.addEnchantment(Enchantment.getEnchantment(15));
            player.giveItem(wand);
            return true;
        }
        else {
            return false;
        }

    }
}
