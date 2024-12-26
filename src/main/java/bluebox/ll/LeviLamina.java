package bluebox.ll;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

public class LeviLamina{
    private static final HashMap<String,Plugin> mods=new HashMap<>();
    private static final Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static File modRootDir;
    private static Logger logger;

    public static File getModRootDir(){
        return modRootDir;
    }
    public static Logger getLogger(){
        return logger;
    }
    public static PrintStream getErrorStream(){
        return new PrintStream(new LoggerStream(logger,LoggerStream.MODE_ERROR),true);
    }
    public static PrintStream getOutputStream(){
        return new PrintStream(new LoggerStream(logger),true);
    }

    private static boolean load(String modDirPath){
        try {
            File modDir=new File(modDirPath);
            File manifestFile=new File(modDir,"manifest.json");
            Manifest manifest=gson.fromJson(new FileReader(manifestFile,StandardCharsets.UTF_8),Manifest.class);
            File entryFile=new File(modDir,manifest.entry);
            if(!entryFile.exists()){
                logger.error("Entry file not found: "+entryFile.getAbsolutePath());
                return false;
            }
            if(manifest.entryClass==null){
                logger.error("Entry class not specified in manifest,you need to specify it at manifest.json/entryClass");
                return false;
            }
            URLClassLoader classLoader=URLClassLoader.newInstance(new URL[]{entryFile.toURI().toURL()});
            Class<Plugin> pluginClass= (Class<Plugin>) classLoader.loadClass(manifest.entryClass);
            Constructor<Plugin> pluginConstructor= pluginClass.getConstructor();
            pluginConstructor.setAccessible(true);
            Plugin plugin=pluginConstructor.newInstance();
            plugin.modDir=modDir;
            plugin.manifest=manifest;
            plugin.logger=new Logger(plugin.manifest.name);
            mods.put(manifest.name,plugin);
            try {
                plugin.onLoad();
            } catch (Exception e) {
                e.printStackTrace(getErrorStream());
                return false;
            }
            logger.info("Loaded plugin "+manifest.name+" from "+modDirPath);
        } catch (Exception e) {
            e.printStackTrace(getErrorStream());
            return false;
        }
        return true;
    }
    private static void enable(){
        for (Plugin plugin : mods.values()) {
            try{
                plugin.onEnable();
            }catch (Exception e){
                removeMod(plugin.manifest.name);
                e.printStackTrace(getErrorStream());
            }
        }
    }
    private static void disable(){
        for (Plugin plugin : mods.values()) {
            try {
                plugin.onDisable();
            } catch (Exception e) {
                e.printStackTrace(getErrorStream());
            }
            removeMod(plugin.manifest.name);
        }
    }
    private static void removeMod(String name){
        if(mods.remove(name)!=null) removeModInNative(name);
    }
    private static native void removeModInNative(String name);
}