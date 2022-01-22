package cc.fascinated;

import cc.fascinated.core.module.Module;
import cc.fascinated.core.module.ModulePriority;

public class Main extends Module {

    public Main() {
        super("Test", ModulePriority.NORMAL);
    }

    @Override
    public void load() {
        cc.fascinated.core.Main.getLogger().log("Hello from " + getName());
    }

    @Override
    public void unload() {
        cc.fascinated.core.Main.getLogger().log("Goodbye from " + getName());
    }
}
