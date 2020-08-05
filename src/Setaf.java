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

    public HashSet<Set> getComplete_extension() {
        //取出一个论证a,找到被a攻击的b,c, 找到被b或c攻击d
        HashSet<Set> cmset = new HashSet<>();
        //遍历argument集合
        for (Argument argument : setOfArguments
        ) {
            HashSet<Argument> arguments_defend = new HashSet<>(); //cmset内存储的集合
            HashSet<Argument> attack_argument = new HashSet<>();//attack_argument is a set attack argument a
            for (Relation r : mapOfRelation
            ) {
                //System.out.println(r.getSetOfAttacker()+"attack\n"+r.getAttacked());
                if (r.getAttacked() == argument) {
                    for (Argument a : r.getSetOfAttacker()
                    ) {
                        attack_argument.add(a);
                    }
                }

            }
            HashSet<Argument> arguments_attacker = new HashSet<>();// arguments_attacker is a set attacked by argument a
            for (Relation relation : mapOfRelation
            ) {//找出被a攻击的bc
                if (relation.getSetOfAttacker().contains(argument)) { //
                    arguments_attacker.add(relation.getAttacked());//bc  the argument which attacker of a is added in arguments_attacker
                }
            }
            if (arguments_attacker.containsAll(attack_argument)) {  //attack_arguemnt is a subset of arguments_attacker , means the argument which attack a all attacked by a,so a is safe.
                System.out.println("集合的第一个元素:"+argument);
                arguments_defend.add(argument);
            }
            if (arguments_defend.iterator().hasNext()) { // if arguments_defend is null, do not print,
                //遍历bc
               // System.out.println(arguments_defend.iterator().next());
                for (Argument argument1 : arguments_attacker   //
                ) {//找出被bc攻击的d
                    System.out.println(argument1);
                    for (Relation relation : mapOfRelation
                    ) {
                        //System.out.println(relation.getSetOfAttacker()+"attack:"+relation.getAttacked());
                        //System.out.println(relation.getAttacked()+"的攻击者"+relation.getSetOfAttacker());
                        if (relation.getSetOfAttacker().contains(argument1)) {//d is attacked by the set which contains b,c.  if a argument is attacked by arguments_attacker，
                            { if (arguments_attacker.containsAll(relation.getSetOfAttacker()) && //and whole the arguments attacked this arguments is subset of arguments_attacker
                                    !(attack_argument.contains(relation.getAttacked()))) {  // 攻击a的集合不包含 d
                                    //System.out.println("第一个："+argument);
                                    //System.out.println("被第一个攻击的："+arguments_attacker);
                                    //System.out.println(relation.getAttacked()+"的攻击者："+relation.getSetOfAttacker());
                                    //System.out.println(arguments_defend);
                                    arguments_defend.add(relation.getAttacked());//ef;
                                }
                            }
                        }
                    }
                }
                cmset.add(arguments_defend);
            }
        }
        return cmset;

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
