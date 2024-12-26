package bluebox.ll;

import java.io.File;

abstract public class Plugin {
    public Logger logger;
    public Manifest manifest;
    public File modDir;
    abstract public void onEnable();
    abstract public void onDisable();
    public void onLoad(){}
}
