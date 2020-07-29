import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Tsetaf_function {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Tsetaf tsetaf  = parser.Parse("/Users/zhujinlong/Downloads/TSETAF/file/TSETAF1.apx");
        Setaf setaf = tsetaf.atTime(new Availability_interval(70,120,1));
        System.out.println(setaf);
        System.out.println(tsetaf);
        tsetaf.showTsetaf(tsetaf).display();
        setaf.showSetaf(setaf).display();




    }




}
