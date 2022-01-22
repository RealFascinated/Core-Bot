package cc.fascinated.minecraft;

import cc.fascinated.core.module.Module;
import cc.fascinated.core.module.ModulePriority;

public class Main extends Module {

    public Main() {
        super("Minecraft", ModulePriority.NORMAL);
    }

    @Override
    public void load() {
        System.out.println("loading a module!");
    }

    @Override
    public void unload() {
        System.out.println("unloading a module!");
    }
}
