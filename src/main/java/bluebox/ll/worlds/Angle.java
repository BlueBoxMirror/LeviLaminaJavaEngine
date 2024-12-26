package bluebox.ll.worlds;

public class Angle implements Cloneable, Comparable<Angle> {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    public float pitch;
    public float yaw;

    public Angle(float yaw, float pitch) {
        this.pitch = pitch;
        this.yaw = yaw;
    }
    public Angle(){
        this(0f,0f);
    }

    public int toFacing(){
        return Math.round(yaw / 90f) % 4;
    }

    public Angle clone() {
        return new Angle(this.yaw, this.pitch);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Angle angle)) return false;

        return Float.compare(pitch, angle.pitch) == 0 && Float.compare(yaw, angle.yaw) == 0;
    }

    @Override
    public int hashCode() {
        int result = Float.hashCode(pitch);
        result = 31 * result + Float.hashCode(yaw);
        return result;
    }


    @Override
    public int compareTo(Angle o) {
        if (pitch == o.pitch) {
            return Float.compare(yaw, o.yaw);
        } else {
            return Float.compare(pitch, o.pitch);
        }
    }

    @Override
    public String toString() {
        return "(" + String.format("%.2f", pitch) + ", " + String.format("%.2f", yaw) + ")";
    }
}
