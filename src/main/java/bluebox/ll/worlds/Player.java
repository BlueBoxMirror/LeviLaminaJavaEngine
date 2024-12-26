package bluebox.ll.worlds;

public class Player extends Entity {
    private Player(){}

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
    public native void setName(String name);

}
