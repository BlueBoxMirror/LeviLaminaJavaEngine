package bluebox.levilamina.engine;

public class Log {
    public Log(String tag){
        this.tag=tag;
        init();
    }
    final public String tag;
    private long nativePtr=0;
    private native void init();
    public native void info(String message);
    public native void error(String message);
    public native void debug(String message);
    public native void warn(String message);
    public native void dispose();
}
