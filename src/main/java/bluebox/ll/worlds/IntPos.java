package bluebox.ll.worlds;

public class IntPos implements Cloneable , Comparable<IntPos> {
    public static final int UNKNOWN=-1;
    public static final int OVERWORLD=0;
    public static final int NETHER=1;
    public static final int THE_END=2;
    public int x;
    public int y;
    public int z;
    public int dimension;

    public IntPos(int x, int y, int z, int dimension) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
    }
    public IntPos(){
        this(0,0,0,0);
    }
    public FloatPos toFloatPos(){
        return toFloatPos(true);
    }
    public FloatPos toFloatPos(boolean isCentered){
        if(isCentered) return new FloatPos(x+0.5f,y,z+0.5f,dimension);
        else return new FloatPos(x,y,z,dimension);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntPos intPos)) return false;

        return x == intPos.x && y == intPos.y && z == intPos.z && dimension == intPos.dimension;
    }

    public String getDimensionName(){
        switch(dimension){
            case OVERWORLD:
                return "主世界";
            case NETHER:
                return "下界";
            case THE_END:
                return "末地";
            default:
                return "Unknown";
        }
    }

    @Override
    public String toString() {
        return getDimensionName() + " (" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        result = 31 * result + dimension;
        return result;
    }

    @Override
    public int compareTo(IntPos o) {
        if(this.x!= o.x) return Integer.compare(this.x, o.x);
        else if(this.y!= o.y) return Integer.compare(this.y, o.y);
        else if(this.z!= o.z) return Integer.compare(this.z, o.z);
        else if(this.dimension!= o.dimension) return Integer.compare(this.dimension, o.dimension);
        else return 0;
    }

    @Override
    public IntPos clone() {
        return new IntPos(x, y, z, dimension);
    }

    public float distanceTo(FloatPos pos){
        if(pos.dimension!= dimension) return Float.MAX_VALUE;
        return (float)Math.sqrt(Math.pow(pos.x-x,2)+Math.pow(pos.y-y,2)+Math.pow(pos.z-z,2));
    }
    public float distanceTo(float x, float y, float z){
        return (float)Math.sqrt(Math.pow(x-this.x,2)+Math.pow(y-this.y,2)+Math.pow(z-this.z,2));
    }
    public float distanceTo(IntPos pos){
        return distanceTo(pos.toFloatPos());
    }
    public float distanceTo(int x, int y, int z){
        return distanceTo(new IntPos(x,y,z,dimension).toFloatPos());
    }
}
