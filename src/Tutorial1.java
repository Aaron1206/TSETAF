import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Tutorial1 {
    public static void main(String args[]) {

        Graph graph = new SingleGraph("Tutorial 1");
          /*
        graph.setStrict(false);
        graph.setAutoCreate( true );
        graph.addEdge( "AB", "A", "B" );
        graph.addEdge( "BC", "B", "C" );
        graph.addEdge( "CA", "C", "A" );
         */
        Node A =graph.addNode("A" );
        Node B = graph.addNode("B" );
        Node C =graph.addNode("C" );
        graph.addEdge("AB", "A", "B",true);//true means that print directed edge
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        A.addAttribute("ui.label","node A");
        B.addAttribute("ui.label","node B");
        C.addAttribute("ui.label","node C");


        graph.display();
    }
}
