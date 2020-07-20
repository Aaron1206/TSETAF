import java.util.ArrayList;
import java.util.Objects;

public class Time_list {
    String name;
    private ArrayList<Availability_interval> time_list;

    public Time_list(String name, ArrayList<Availability_interval> time_list) {
        this.name = name;
        this.time_list = time_list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Availability_interval> getTime_list() {
        return time_list;
    }

    public void setTime_list(ArrayList<Availability_interval> time_list) {
        this.time_list = time_list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time_list time_list1 = (Time_list) o;
        return Objects.equals(name, time_list1.name) &&
                Objects.equals(time_list, time_list1.time_list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time_list);
    }

    @Override
    public String toString() {
        return "Time_list{" +
                "name='" + name + '\'' +
                ", time_list=" + time_list +
                '}';
    }
}
