import java.util.ArrayList;
import java.util.Objects;

public class Time_list {

    private ArrayList<Availability_interval> time_list;

    public Time_list(ArrayList<Availability_interval> time_list) {
        this.time_list = time_list;
    }

    public ArrayList<Availability_interval> getTime_list() {
        return time_list;
    }

    public void setTime_list(ArrayList<Availability_interval> time_list) {
        this.time_list = time_list;
    }

    @Override
    public String toString() {
        return "Time_list{" +
                "time_list=" + time_list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time_list time_list1 = (Time_list) o;
        return Objects.equals(time_list, time_list1.time_list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time_list);
    }
}
