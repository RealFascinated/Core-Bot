package cc.fascinated.core.util.io;

import cc.fascinated.core.Main;

import java.io.File;

public class FileUtil {

    /**
     * Get the File for a directory and create it if it does not exist.
     *
     * @param path The path of the directory
     * @param log Whether to log that the directory has been made or not.
     * @return The File of the directory
     */
    public static File getAndCreateDir(String path, boolean log) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
            if (log) {
                Main.getLogger().log("Created \"" + path + "\" directory.");
            }
        }
        return dir;
    }
}
