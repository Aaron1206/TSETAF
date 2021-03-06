import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.*;

public class Tsetaf {
    private HashSet<Argument> setOfArguments;
    private HashSet<Relation> mapOfRelation;
    private HashMap<Argument, Time_list> setOfTime;


    public Tsetaf() {
        this.setOfArguments = new HashSet<>();
        this.mapOfRelation = new HashSet<>();
        this.setOfTime = new HashMap<>();
    }


    public void addArgument(Argument argument) {
        setOfArguments.add(argument);
    }

    public void addRelation(Relation relation) {
        mapOfRelation.add(relation);
    }

    public void addTime(Argument a, Time_list time_list) {
        setOfTime.put(a, time_list);
    }

    public Graph getGraph() {
        Graph graph = new SingleGraph("Tsetaf");
        graph.addAttribute("ui.stylesheet", "url('./stylesheet.css')");

        //We add the nodes corresponding to the arguments
        for (Argument argument : setOfArguments) {
            graph.addNode(argument.getName()).addAttribute("ui.label", argument.getName() + " " + setOfTime.get(argument).toString());
        }

        //We add the attacks
        for (Relation relation : mapOfRelation) {
            if (relation.getSetOfAttacker().size() > 1) {
                graph.addNode(relation.toString());
                graph.getNode(relation.toString()).setAttribute("ui.class", "set");
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

    public void addAvailabilityInterval(Argument a, Availability_interval b) {
        if (setOfTime.get(a) == null)
            setOfTime.put(a, new Time_list());
        setOfTime.get(a).getTime_list().add(b);
    }


public Setaf atTime() {
        Scanner sc = new Scanner(System.in);
        System.out.println("please input startpoint：");
        int start_point = sc.nextInt();
        System.out.println("please input endpoint：");
        int end_point = sc.nextInt();
        System.out.println("请输入type(0:(),1:(],2:[],3:[))：");
        int type = sc.nextInt();
        Availability_interval availability_interval = new Availability_interval(start_point, end_point, type);
        Setaf setaf = new Setaf();
        HashMap<String, HashSet<String>> Relation = new HashMap<>();
        for (Argument argument : setOfTime.keySet()) {
            ArrayList<Availability_interval> time_list = setOfTime.get(argument).getTime_list();
            for (Availability_interval interval : time_list
            ) {//a b c d        [c,d]      type:0();1(];2[];3[)
                if (availability_interval.getType() == 0 && (
                        (interval.getType() == 0 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point()))
                                || (interval.getType() == 1 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                                || (interval.getType() == 2 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                                || (interval.getType() == 3 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                        ))) {
                    setaf.addArgument(argument);
                } else if (availability_interval.getType() == 1 && (
                        (interval.getType() == 0 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                                || (interval.getType() == 1 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                                || (interval.getType() == 2 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() > availability_interval.getEnd_point())))
                                || (interval.getType() == 3 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() > availability_interval.getEnd_point())))
                )) {
                    setaf.addArgument(argument);
                } else if (availability_interval.getType() == 2 && (
                        (interval.getType() == 0 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                                || (interval.getType() == 1 && !((interval.getEnd_point() < availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                                || (interval.getType() == 2 && !((interval.getEnd_point() < availability_interval.getStart_point()) || (interval.getStart_point() > availability_interval.getEnd_point())))
                                || (interval.getType() == 3 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() > availability_interval.getEnd_point())))
                )) {
                    setaf.addArgument(argument);
                } else if (availability_interval.getType() == 3 && (
                        (interval.getType() == 0 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() > availability_interval.getEnd_point())))
                                || (interval.getType() == 1 && !((interval.getEnd_point() < availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                                || (interval.getType() == 2 && !((interval.getEnd_point() < availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                                || (interval.getType() == 3 && !((interval.getEnd_point() <= availability_interval.getStart_point()) || (interval.getStart_point() >= availability_interval.getEnd_point())))
                )) {
                    setaf.addArgument(argument);
                }
            }
            for (
                    Relation relation : mapOfRelation) {
                if (setaf.getSetOfArguments().contains(relation.getAttacked())) {
                    if (setaf.getSetOfArguments().containsAll(relation.getSetOfAttacker())) {
                        setaf.addRelation(relation);
                    }
                }
            }
        }
        return setaf;
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


