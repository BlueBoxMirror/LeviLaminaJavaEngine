package bluebox.ll.worlds;

public class FloatPos implements Cloneable , Comparable<FloatPos> {
    public static final int UNKNOWN=-1;
    public static final int OVERWORLD=0;
    public static final int NETHER=1;
    public static final int THE_END=2;
    public FloatPos(){
        this(0f,0f,0f,0);
    }
    public FloatPos(float x, float y, float z, int dimension){
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
    }
    public float x;
    public float y;
    public float z;
    public int dimension;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FloatPos pos)) return false;

        return Float.compare(x, pos.x) == 0 && Float.compare(y, pos.y) == 0 && Float.compare(z, pos.z) == 0 && dimension == pos.dimension;
    }

    @Override
    public int hashCode() {
        int result = Float.hashCode(x);
        result = 31 * result + Float.hashCode(y);
        result = 31 * result + Float.hashCode(z);
        result = 31 * result + dimension;
        return result;
    }
    public IntPos toIntPos(){
        return toIntPos(false);
    }
    public IntPos toIntPos(boolean round){
        if(round) return new IntPos(Math.round(x),Math.round(y),Math.round(z),dimension);
        else return new IntPos((int)x,(int)y,(int)z,dimension);
    }

    @Override
    public FloatPos clone() {
        return new FloatPos(x,y,z,dimension);
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
        return getDimensionName() + " (" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + ", " + String.format("%.2f", z) + ")";
    }

    @Override
    public int compareTo(FloatPos o) {
        if(dimension!= o.dimension) return Integer.compare(dimension, o.dimension);
        if(x!= o.x) return Float.compare(x, o.x);
        if(y!= o.y) return Float.compare(y, o.y);
        return Float.compare(z, o.z);
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
