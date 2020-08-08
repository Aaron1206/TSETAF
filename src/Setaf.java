import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    //find all subsets of set
    public HashSet<HashSet<Argument>> getSubset() {
        int n = setOfArguments.size();
        ArrayList<Argument> ArrayArguments = new ArrayList<>(setOfArguments);

        HashSet<HashSet<Argument>> result = new HashSet<>();
        // Run a loop from 0 to 2^n
        for (int i = 0; i < (1 << n); i++) {
            HashSet<Argument> setToBeConstructed = new HashSet<>();
            int m = 1; // m is used to check set bit in binary representation.
            // Print current subset
            for (int j = 0; j < n; j++) {
                if ((i & m) > 0) {
                    setToBeConstructed.add(ArrayArguments.get(j));
                }
                m = m << 1;
            }
            result.add(setToBeConstructed);
        }
        return result;
    }


    // the function of conflict free
    public HashSet<HashSet<Argument>> getConflictFree() {
        HashSet<HashSet<Argument>> allSubsets = getSubset();
        HashSet<HashSet<Argument>> result = new HashSet<>();
        for (HashSet<Argument> X : allSubsets) {
            Boolean IsXConflictFree = true;
            for (Relation R : mapOfRelation) {
                if (X.contains(R.getAttacked()) && X.containsAll(R.getSetOfAttacker()))
                    IsXConflictFree = false;
            }
            if (IsXConflictFree)
                result.add(X);
        }
        return result;
    }

    public HashSet<Relation> getAttackersOfArgument(Argument a) {
        HashSet<Relation> set = new HashSet<>();
        for (Relation relation : mapOfRelation) {
            if (relation.getAttacked().equals(a)) {
                set.add(relation);
            }
        }
        return set;
    }

    // the function of getting admissible extension
    public HashSet<HashSet<Argument>> getAdmissible() {
        HashSet<HashSet<Argument>> admissible_set = new HashSet<>();
        for (HashSet<Argument> C : getConflictFree()) {//[a,b]  c attack a   d attack b  b attack d
            Boolean IsCAdmissible = true;
            for (Argument c : C) {//a b
                Boolean IsDefendedAgainstAllAttacks = true;
                for (Relation R : getAttackersOfArgument(c)) {//d attack b
                    Boolean IscDefendedAgainstR = false;
                    for (Argument r : R.getSetOfAttacker())//d
                        for (Relation C1 : getAttackersOfArgument(r)) { //
                            if (C.containsAll(C1.getSetOfAttacker())) { //b
                                IscDefendedAgainstR = true;
                            }
                        }
                    if (!IscDefendedAgainstR)
                        IsDefendedAgainstAllAttacks = false;
                }
                if (!IsDefendedAgainstAllAttacks)
                    IsCAdmissible = false;
            }
            if (IsCAdmissible)
                admissible_set.add(C);
        }
        return admissible_set;
    }

    // the function of getting complete extension
    public HashSet<HashSet<Argument>> getComplete() {
        HashSet<HashSet<Argument>> completeSet = new HashSet<>();
        for (HashSet<Argument> C : getAdmissible()) {
            Boolean IsComplete = true;
            for (Argument c : NotInSet(C)) {
                Boolean IsDefendedByC = true;
                for (Relation R : getAttackersOfArgument(c)) {
                    Boolean IsDefendedAgainstR = false;
                    for (Argument r : R.getSetOfAttacker()) {
                        for (Relation C1 : getAttackersOfArgument(r)) {
                            if (C.containsAll(C1.getSetOfAttacker())) {
                                //It means C defends c against R
                                IsDefendedAgainstR = true;
                            }
                        }
                    }
                    if (!IsDefendedAgainstR)
                        IsDefendedByC = false;
                }

                if (IsDefendedByC)
                    IsComplete = false;
            }
            if (IsComplete)
                completeSet.add(C);
        }
        return completeSet;
    }

    public HashSet<Argument> NotInSet(HashSet<Argument> C) {
        HashSet<Argument> result = new HashSet<>();
        for (Argument a : setOfArguments) {
            if (!C.contains(a))
                result.add(a);
        }
        return result;
    }

    // the function of getting preferred extension
    public HashSet<HashSet<Argument>> getPreferred() {
        HashSet<HashSet<Argument>> preferredSet = new HashSet<>();
        for (HashSet<Argument> C : getAdmissible()
        ) {

        }

        return preferredSet;
    }

    // the function of getting grounded extension
    public HashSet<HashSet<Argument>> getGrounded() {

        HashSet<HashSet<Argument>> groundedSet = new HashSet<>();
        for (HashSet<Argument> C : getComplete()
        ) {
            Boolean IsCGrounedeSet = true;
            for (HashSet<Argument> D : getComplete()
            ) {
                if (!(D.containsAll(C))) {
                    IsCGrounedeSet = false;
                }
            }
            if (IsCGrounedeSet) {
                groundedSet.add(C);
            }
        }
        return groundedSet;
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

    public HashMap<Argument, Double> getNh_categoriser(int numberOfsteps) {
        HashMap<Argument, Double> map = new HashMap<>();

        //We give a score of 1 to each argument at step 1
        for (Argument argument : setOfArguments) {
            map.put(argument, 1.0);
        }

        //We update the scores
        for (int i = 0; i < numberOfsteps; i++) {
            HashMap<Argument, Double> PreviousScore = new HashMap<>(); //We store the score of map at the previous step
            for (Argument argument : setOfArguments) {
                PreviousScore.put(argument, map.get(argument));
            }

            //We now use the score of the previous step to update the current score
            for (Argument argument : setOfArguments) {
                HashSet<Relation> ArgAttackers = getAttackers(argument);
                Double sum = 1.0;
                for (Relation r : ArgAttackers) {
                    Iterator<Argument> iterAttacker = r.getSetOfAttacker().iterator();
                    Double minAttacker = map.get(iterAttacker.next());
                    while (iterAttacker.hasNext()) {
                        Double nextValue = map.get(iterAttacker.next());
                        if (nextValue < minAttacker)
                            minAttacker = nextValue;
                    }
                    sum += minAttacker;
                }
                map.put(argument, 1 / sum);
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
