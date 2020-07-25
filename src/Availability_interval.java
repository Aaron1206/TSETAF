import java.util.Objects;

public class Availability_interval {
    private int start_point;
    private int end_point;
    /*
    type:0();1(];2[];3[)
    */
    private int type;

    public Availability_interval(int start_point, int end_point, int type) {
        this.start_point = start_point;
        this.end_point = end_point;
        this.type = type;
    }

    public int getStart_point() {
        return start_point;
    }

    public void setStart_point(int start_point) {
        this.start_point = start_point;
    }

    public int getEnd_point() {
        return end_point;
    }

    public void setEnd_point(int end_point) {
        this.end_point = end_point;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Availability_interval that = (Availability_interval) o;
        return start_point == that.start_point &&
                end_point == that.end_point &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start_point, end_point, type);
    }

    @Override
    public String toString() {
        return ((type == 0 | type == 1)? "(" : "[" )+
                start_point + "," + end_point + ((type == 0 | type == 3)? ")": "]");
    }
}
