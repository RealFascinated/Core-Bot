package cc.fascinated.core.util;

import cc.fascinated.core.Main;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Config {

    TOKEN("token", "Please set me"),
    MODULE_DIR("module-directory", "./Modules");

    private final String path;
    private final String defaultValue;

    private static final String configPath = "./config.yml";
    private static HashMap<String, String> valueCache;

    /**
     * Creates the config file if it does not exist,
     * then loads all the values into the config.
     */
    public static void load() {
        valueCache = new HashMap<>();
        File configFile = new File(configPath);
        if (!configFile.exists()) {
            Main.getLogger().log("Creating config file.");
            try {
                configFile.createNewFile();
                for (Config config : values()) {
                    config.get(); // Calling to make it create the default values.
                }
            } catch (IOException ex) {
                Main.getLogger().log("Failed to create the config file, do I have enough permissions?");
            }
        }
    }

    /**
     * Gets the value from the config
     *
     * @return The value
     */
    public String get() {
        return valueCache.computeIfAbsent(this.path, val -> {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(configPath);
            } catch (FileNotFoundException ex) {
                Main.getLogger().log("Unable to load config file, is the config path correct?");
            }

            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            Map<String, Object> data = yaml.load(inputStream);
            if (data == null) {
                data = new HashMap<>();
            }
            String value;
            if (data.containsKey(this.path)) {
                value = (String) data.get(this.path);
            } else {
                data.put(this.path, this.defaultValue);
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(configPath);
                } catch (FileNotFoundException e) {
                    Main.getLogger().log("Unable to load config file, is the config path correct?");
                }
                yaml.dump(data, writer);
                value = defaultValue;
            }
            return value;
        });
    }
}
