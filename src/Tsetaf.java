import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Tsetaf {
    private HashSet<Argument> setOfArguments;
    private HashMap<String,HashSet<Argument>> mapOfRelation;
    private HashSet<ArrayList<Availability_interval>> setOfTime;

    public Tsetaf(){
        this.setOfArguments = new HashSet<>();
        this.mapOfRelation = new HashMap<>();
        this.setOfTime = new HashSet<>();
    }


    /*
    public Tsetaf(HashSet<Argument> setOfArguments, HashMap<String, HashSet<Argument>> mapOfRelation, HashMap<String, ArrayList<Availability_interval>> mapOfTime) {
        this.setOfArguments = setOfArguments;
        this.mapOfRelation = mapOfRelation;
        this.mapOfTime = mapOfTime;
    }
    */

    public void addArgument(Argument argument){
        setOfArguments.add(argument);
    }
    public void addRelation(Relation relation){
        mapOfRelation.put(relation.getName(),relation.getSetOfAttacker());
    }
    public void addTime(Time_list time_list){
        setOfTime.add(time_list.getTime_list());
    }

    public Tsetaf(HashSet<Argument> setOfArguments, HashMap<String, HashSet<Argument>> mapOfRelation, HashSet<ArrayList<Availability_interval>> setOfTime) {
        this.setOfArguments = setOfArguments;
        this.mapOfRelation = mapOfRelation;
        this.setOfTime = setOfTime;
    }

    public HashSet<Argument> getSetOfArguments() {
        return setOfArguments;
    }

    public void setSetOfArguments(HashSet<Argument> setOfArguments) {
        this.setOfArguments = setOfArguments;
    }

    public HashMap<String, HashSet<Argument>> getMapOfRelation() {
        return mapOfRelation;
    }

    public void setMapOfRelation(HashMap<String, HashSet<Argument>> mapOfRelation) {
        this.mapOfRelation = mapOfRelation;
    }

    public HashSet<ArrayList<Availability_interval>> getSetOfTime() {
        return setOfTime;
    }

    public void setSetOfTime(HashSet<ArrayList<Availability_interval>> setOfTime) {
        this.setOfTime = setOfTime;
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
        return "Tsetaf{" +
                "setOfArguments=" + setOfArguments +
                ", mapOfRelation=" + mapOfRelation +
                ", setOfTime=" + setOfTime +
                '}';
    }
}

