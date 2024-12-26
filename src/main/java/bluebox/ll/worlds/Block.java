package bluebox.ll.worlds;

public class Block {
    private Block(){}
    private long nativePtr=0;
    public native String getName();
}
