package cc.fascinated.core.module;

import cc.fascinated.core.Main;
import cc.fascinated.core.util.Config;
import cc.fascinated.core.util.io.ClassUtil;
import cc.fascinated.core.util.io.FileUtil;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    @Getter private static List<Module> modules; // All the modules

    public ModuleManager() {
        modules = new ArrayList<>();
        File moduleDir = FileUtil.getAndCreateDir(Config.MODULE_DIR.get(), true); // Load the directory, so we can get ready to load the modules in.
        File[] moduleFiles = moduleDir.listFiles(); // Fetch all the module files.
        if (moduleFiles == null) {
            Main.getLogger().log("No modules were found.");
            return;
        }
        loadModules(moduleFiles); // Load all modules from the files.
    }

    /**
     * Reloads the given module
     *
     * @param module The module to reload.
     */
    private static void reloadModule(Module module) {
        modules.removeIf(m -> {
            if (m.getClass().equals(module.getClass())) {
                m.unload();
                return true;
            }
            return false;
        });
        List<Class<?>> classes = ClassUtil.getClassesFromJar(module.getPath());
        for (Class<?> clazz : classes) {
            if (clazz.getSuperclass().equals(Module.class)) {
                loadModule(new File(module.getPath()));
            }
        }
    }

    /**
     * Loads the given module using the given file
     *
     * @param file The module file
     */
    private static void loadModule(File file) {
        List<Class<?>> classes = ClassUtil.getClassesFromJar(file.getPath());
        for (Class<?> clazz : classes) {
            if (clazz.getSuperclass().equals(Module.class)) {
                Module module;
                try {
                    module = (Module) clazz.newInstance();
                    modules.add(module);
                    Main.getLogger().log("Loading module \"" + module.getName() + "\"");
                    module.load();
                    module.setPath(file.getPath());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    Main.getLogger().log("Unable to load the module \"" + clazz.getSimpleName() + "\"");
                    return;
                }
                return;
            }
        }
    }

    /**
     * Loads all modules from the given files.
     *
     * @param moduleFiles The module File list
     */
    private void loadModules(File[] moduleFiles) {
        for (File moduleFile : moduleFiles) {
            loadModule(moduleFile);
        }
    }
}
