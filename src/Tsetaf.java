import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Tsetaf {
    private HashSet<Argument> setOfArguments;
    private HashMap<String,HashSet<Argument>> mapOfRelation;
    private HashMap<String,ArrayList<Availability_interval>> mapOfTime;

    public Tsetaf(){
        this.setOfArguments = new HashSet<>();
        this.mapOfRelation = new HashMap<>();
        this.mapOfTime = new HashMap<>();
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
        mapOfTime.put(time_list.getName(),time_list.getTime_list());
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

    public HashMap<String, ArrayList<Availability_interval>> getMapOfTime() {
        return mapOfTime;
    }

    public void setMapOfTime(HashMap<String, ArrayList<Availability_interval>> mapOfTime) {
        this.mapOfTime = mapOfTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tsetaf tsetaf = (Tsetaf) o;
        return Objects.equals(setOfArguments, tsetaf.setOfArguments) &&
                Objects.equals(mapOfRelation, tsetaf.mapOfRelation) &&
                Objects.equals(mapOfTime, tsetaf.mapOfTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setOfArguments, mapOfRelation, mapOfTime);
    }

    @Override
    public String toString() {
        return "Tsetaf{" +
                "setOfArguments=" + setOfArguments +
                ", mapOfRelation=" + mapOfRelation +
                ", mapOfTime=" + mapOfTime +
                '}';
    }
}

