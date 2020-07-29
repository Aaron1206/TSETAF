import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashSet;
import java.util.Objects;

public class Setaf {
    private HashSet<Argument> setOfArguments;
    private HashSet<Relation> mapOfRelation;
    Graph graph = new SingleGraph("Setaf");

    public Setaf() {
        this.setOfArguments = new HashSet<Argument>();
        this.mapOfRelation = new HashSet<Relation>();
    }
    public void addArgument(Argument argument){
        setOfArguments.add(argument);
    }

    public void addRelation(Relation relation){
        mapOfRelation.add(relation);
    }

    public Graph showSetaf(Setaf setaf){
        for (Argument argument:setaf.setOfArguments
        ) {graph.addNode(argument.getName()).addAttribute("ui.label","Argument"+"\n"+argument.getName());
        }
        for (Relation relation:setaf.mapOfRelation
        ) {
            for (Argument argument:relation.getSetOfAttacker()
            ) {graph.addEdge(argument.getName()+relation.getAttacked(),argument.getName(),relation.getAttacked().getName(),true);

            }

        }
        return  graph;
    }

    public HashSet<Argument> getSetOfArguments() {
        return setOfArguments;
    }

    public void setSetOfArguments(HashSet<Argument> setOfArguments) {
        this.setOfArguments = setOfArguments;
    }

    public HashSet<Relation> getMapOfRelation() {
        return mapOfRelation;
    }

    public void setMapOfRelation(HashSet<Relation> mapOfRelation) {
        this.mapOfRelation = mapOfRelation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setaf setaf = (Setaf) o;
        return Objects.equals(setOfArguments, setaf.setOfArguments) &&
                Objects.equals(mapOfRelation, setaf.mapOfRelation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setOfArguments, mapOfRelation);
    }

    @Override
    public String toString() {
        return "Setaf:\n" +
                "setOfArguments=" + setOfArguments +
                "\nmapOfRelation=" + mapOfRelation;
    }
}
