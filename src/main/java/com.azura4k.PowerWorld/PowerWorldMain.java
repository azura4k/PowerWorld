package com.azura4k.PowerWorld;

import cn.nukkit.command.CommandMap;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.command.simple.SimpleCommand;
import cn.nukkit.plugin.PluginBase;
import com.azura4k.PowerWorld.commands.WandCmd;

public class PowerWorldMain extends PluginBase {
    @Override
    public void onLoad() {
        super.onEnable();
        //Register Commands
        SimpleCommandMap CM = this.getServer().getCommandMap();
        CM.register("/wand", new WandCmd());
    }

    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("Activated PowerWorld ;)");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().critical("Critical Error :p");
    }
}
