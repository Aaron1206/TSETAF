import java.util.Objects;

public class Argument {
    private String name;

    public Argument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return Objects.equals(name, argument.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Argument{" +
                "name='" + name + '\'' +
                '}';
    }

    /*
    private String name;
    private HashMap<String,Time_list> argument_time;
    private HashSet<HashSet<Argument>> set_attackers;

    public Argument(String name, HashMap<String, Time_list> argument_time, HashSet<HashSet<Argument>> set_attackers) {
        this.name = name;
        this.argument_time = argument_time;
        this.set_attackers = set_attackers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Time_list> getArgument_time() {
        return argument_time;
    }

    public void setArgument_time(HashMap<String, Time_list> argument_time) {
        this.argument_time = argument_time;
    }

    public HashSet<HashSet<Argument>> getSet_attackers() {
        return set_attackers;
    }

    public void setSet_attackers(HashSet<HashSet<Argument>> set_attackers) {
        this.set_attackers = set_attackers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return Objects.equals(name, argument.name) &&
                Objects.equals(argument_time, argument.argument_time) &&
                Objects.equals(set_attackers, argument.set_attackers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, argument_time, set_attackers);
    }

    @Override
    public String toString() {
        return "Argument{" +
                "name='" + name + '\'' +
                ", argument_time=" + argument_time +
                ", set_attackers=" + set_attackers +
                '}';
    }

     */
}
