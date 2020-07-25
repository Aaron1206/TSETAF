import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Tsetaf {
    private HashSet<Argument> setOfArguments;
    private HashSet<Relation> mapOfRelation;
    private HashMap<Argument,Time_list> setOfTime;


    public Tsetaf(){
        this.setOfArguments = new HashSet<>();
        this.mapOfRelation = new HashSet<>();
        this.setOfTime = new HashMap<>();
    }


    public void addArgument(Argument argument){
        setOfArguments.add(argument);
    }

    public void addRelation(Relation relation){
        mapOfRelation.add(relation);
    }
    public void addTime(Argument a, Time_list time_list)
    {
        setOfTime.put(a, time_list);
    }

    public void addAvailabilityInterval(Argument a, Availability_interval b)
    {
        if(setOfTime.get(a) == null)
            setOfTime.put(a, new Time_list());
        setOfTime.get(a).getTime_list().add(b);
    }



    public HashSet<Argument> getSetOfArguments() {
        return setOfArguments;
    }

    public HashSet<Relation> getMapOfRelation() {
        return mapOfRelation;
    }


    public HashMap<Argument, Time_list> getSetOfTime() {
        return setOfTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tsetaf tsetaf = (Tsetaf) o;
        return Objects.equals(setOfArguments, tsetaf.setOfArguments) &&
                Objects.equals(mapOfRelation, tsetaf.mapOfRelation) &&
                Objects.equals(setOfTime, tsetaf.setOfTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setOfArguments, mapOfRelation, setOfTime);
    }

    @Override
    public String toString() {
        return "Tsetaf:\n" +
                "setOfArguments=" + setOfArguments +
                "\nmapOfRelation=" + mapOfRelation +
                "\nsetOfTime=" + setOfTime;
    }
}

