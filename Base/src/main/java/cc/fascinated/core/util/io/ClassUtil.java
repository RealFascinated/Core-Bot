package cc.fascinated.core.util.io;

import lombok.SneakyThrows;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

    @SneakyThrows
    public static List<Class<?>> getClassesFromJar(String path) {
        List<Class<?>> classes = new ArrayList<>();
        JarFile jarFile = new JarFile(path);
        Enumeration<JarEntry> e = jarFile.entries();
        URL[] urls = { new URL("jar:file:" + path+"!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class<?> clazz = cl.loadClass(className);
            classes.add(clazz);
        }
        return classes;
    }
}
