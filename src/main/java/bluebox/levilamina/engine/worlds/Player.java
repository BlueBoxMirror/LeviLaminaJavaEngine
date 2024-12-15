package bluebox.levilamina.engine.worlds;

public class Player {
    private Player(){}
    private long nativePtr=0;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player)
            return ((Player) obj).nativePtr == nativePtr;
        else return false;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(nativePtr);
    }

    public native void tell(String message);
    public native String getRealName();
}
