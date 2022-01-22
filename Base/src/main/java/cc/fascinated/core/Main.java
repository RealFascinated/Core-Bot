package cc.fascinated.core;

import cc.fascinated.core.module.Module;
import cc.fascinated.core.module.ModuleManager;
import cc.fascinated.core.util.Config;
import cc.fascinated.core.util.Logger;
import lombok.Getter;

public class Main {

    @Getter private static Logger logger;
    @Getter private static ModuleManager moduleManager;

    public Main() {
        logger = new Logger();
        Config.load();
        moduleManager = new ModuleManager();
    }

    public static void main(String[] args) {
        new Main();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Module module : ModuleManager.getModules()) {
                logger.log("Unloading module \"" + module.getName() + "\"");
                module.unload();
            }
        }));
    }
}
