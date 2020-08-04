import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Setaf {
    private HashSet<Argument> setOfArguments;
    private HashSet<Relation> mapOfRelation;

    public static int count = 0;

    public Setaf() {
        this.setOfArguments = new HashSet<Argument>();
        this.mapOfRelation = new HashSet<Relation>();
    }

    public void addArgument(Argument argument) {
        setOfArguments.add(argument);
    }

    public void addRelation(Relation relation) {
        mapOfRelation.add(relation);
    }

    public Graph getGraph() {
        Graph graph = new SingleGraph("Setaf");

        for (Argument argument : setOfArguments) {
            graph.addNode(argument.getName()).addAttribute("ui.label", argument.getName());
        }
        for (Relation relation : mapOfRelation) {

            if (relation.getSetOfAttacker().size() > 1) {
                graph.addNode(relation.toString());
                for (Argument argument : relation.getSetOfAttacker()) {
                    graph.addEdge(argument.getName() + relation.toString(), argument.getName(), relation.toString(), false);
                }
                graph.addEdge(relation.toString() + relation.getAttacked().getName(), relation.toString(), relation.getAttacked().getName(), true);
            } else {
                for (Argument argument : relation.getSetOfAttacker()) {
                    graph.addEdge(argument.getName() + relation.getAttacked().getName(), argument.getName(), relation.getAttacked().getName(), true);
                }
            }


        }
        return graph;
    }

    public void convertAspartix() throws IOException {
        count++;
        BufferedWriter bw = new BufferedWriter(new FileWriter("setaf" + count + ".apx"));

        for (Argument argument : setOfArguments
        ) {
            //bw.write("Arg" + "(" + argument.getName() + ")");
            bw.write(argument.toString());
            bw.newLine();

        }
        bw.newLine();
        int count1 = 0;
        for (Relation relation : mapOfRelation
        ) {
            count1++;
            bw.write("att(" + "r" + count1 + "," + relation.getAttacked().getName() + ")");
            bw.newLine();
            for (Argument argument : relation.getSetOfAttacker()
            ) {
                bw.write("mem(" + "r" + count1 + "," + argument.getName() + ")");
                bw.newLine();

            }
            //bw.write(relation.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public HashMap<Argument, Double> getHN_categoriser(int numberOfsteps) {
        HashMap<Argument, Double> map = new HashMap<>();

        //We give a score of 1 to each argument at step 1
        for (Argument argument : setOfArguments) {
            map.put(argument, 1.0);
        }

        //We update the scores
        for(int i = 0; i <numberOfsteps; i++){
            HashMap<Argument, Double> PreviousScore = new HashMap<>(); //We store the score of map at the previous step
            for (Argument argument : setOfArguments) {
                PreviousScore.put(argument, map.get(argument));
            }

            //We now use the score of the previous step to update the current score
            for (Argument argument : setOfArguments) {
                HashSet<Relation> ArgAttackers = getAttackers(argument);
                Double sum = 1.0;
                for(Relation r : ArgAttackers){
                    Iterator<Argument> iterAttacker = r.getSetOfAttacker().iterator();
                    Double minAttacker = map.get(iterAttacker.next());
                    while(iterAttacker.hasNext())
                    {
                        Double nextValue = map.get(iterAttacker.next());
                        if(nextValue < minAttacker)
                            minAttacker = nextValue;
                    }
                    sum+=minAttacker;
                }
                map.put(argument, 1/sum);
            }
        }

        return map;

    }

    public HashSet<Relation> getAttackers(Argument argument) {
        HashSet<Relation> set = new HashSet<>();
        for (Relation relation : mapOfRelation
        ) {
            if (relation.getAttacked().equals(argument)) {
                set.add(relation);
            }
        }
        return set;
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
