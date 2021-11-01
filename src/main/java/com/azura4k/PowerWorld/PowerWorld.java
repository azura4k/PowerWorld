package com.azura4k.PowerWorld;

import cn.nukkit.block.Block;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;
import com.azura4k.PowerWorld.commands.SetBlocksCmd;
import com.azura4k.PowerWorld.commands.WandCmd;
import com.azura4k.PowerWorld.listeners.WandListener;

import java.util.ArrayList;
import java.util.List;

public class PowerWorld extends PluginBase {
    public static Vector3 Vector1;
    public static Vector3 Vector2;
    public static List<Block> Blocks = new ArrayList<>();

    @Override
    public void onLoad() {
        super.onLoad();
        //Load All Command Classes
        SimpleCommandMap CM = this.getServer().getCommandMap();
        CM.register("/wand", new WandCmd());
        CM.register("/set", new SetBlocksCmd());
        saveDefaultConfig();
    }

    public void onEnable() {
        getLogger().info("PowerWorld is Enabled. Lets Get to Work :D");
        //LoadListeners
        getServer().getPluginManager().registerEvents( new WandListener(), this);
        saveConfig();
    }

    @Override
    public void onDisable() {
        getLogger().critical("Critical Error :P");
        getLogger().info("Shutting Down");
        super.onDisable();
    }

    public void StorePosLocation(Vector3 Vector1, Vector3 Vector2){
        this.Vector1 = Vector1;
        this.Vector2 = Vector2;
    }
    public void StoreBlock(Block AddOnBlock){
        this.Blocks.add(AddOnBlock);
    }


    public void ClearVariables(){
    //TODO
    }

    public final Vector3 GetPos1() {
        return this.Vector1;
    }

    public final Vector3 GetPos2() {
        return this.Vector2;
    }

    public final List<Block> getBlocks() {
        return this.Blocks;
    }
}
