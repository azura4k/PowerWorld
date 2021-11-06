package com.azura4k.PowerWorld;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.plugin.PluginBase;
import com.azura4k.PowerWorld.commands.ReplaceBlocksCmd;
import com.azura4k.PowerWorld.commands.SetBlocksCmd;
import com.azura4k.PowerWorld.commands.UndoCmd;
import com.azura4k.PowerWorld.commands.WandCmd;
import com.azura4k.PowerWorld.listeners.WandListener;

import java.util.ArrayList;


public class PowerWorld extends PluginBase {
    public static ArrayList<ArrayList<Block>> UndoList = new ArrayList<>();
    public static final int UndoStorageLimit = 5;

    @Override
    public void onLoad() {
        super.onLoad();
        //Load All Command Classes
        SimpleCommandMap CM = this.getServer().getCommandMap();
        CM.register("/wand", new WandCmd());
        CM.register("/set", new SetBlocksCmd());
        CM.register("/replace", new ReplaceBlocksCmd());
        CM.register("/undo", new UndoCmd());
        //Config Stuff
    }

    public void onEnable() {
        getLogger().info("PowerWorld is Enabled. Lets Get to Work :D");
        //LoadListeners
        getServer().getPluginManager().registerEvents( new WandListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().critical("Critical Error :P");
        getLogger().info("Shutting Down");
        super.onDisable();
    }


}
