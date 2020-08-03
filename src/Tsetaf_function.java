import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;
import java.io.File;
import java.io.IOException;
import java.text.Format;

public class Tsetaf_function {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Tsetaf tsetaf = parser.Parse("/Users/zhujinlong/Downloads/TSETAF/file/TSETAF1.apx");
        //  type:0();1(];2[];3[)
        Setaf setaf = tsetaf.atTime(new Availability_interval(60, 100, 1));
        System.out.println(setaf);
        System.out.println(tsetaf);

        try {
            setaf.convertAspartix();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graph G1 = tsetaf.getGraph();
        write(G1);
        Graph G2 = setaf.getGraph();
        write(G2);
     // Graphviz.fromGraph(G1).height(100).render(Format.PNG).toFile(new File("example/ex1.png"));
        Viewer vTSETAF = G1.display();
        Viewer vSETAF = G2.display();

    }


    public static void write(Graph G) {

        G.setAttribute("ui.antialias");

        G.setAttribute("ui.stylesheet",
                "node { "
                        + "shape: box;"
                        + "size-mode: fit;"
                        + "fill-color: #F4E4D4;"
                        + "stroke-mode: plain;"
                        + "stroke-color: #4F4E5A;"
                        + "stroke-width: 2px;"
                        + "padding: 5px;" +
                        "text-size: 20;"
                        + "}"

                        + "node.set {"
                        + "shape: circle;"
                        + "fill-color: #4F4E5A;"
                        + "}"

                        + " edge {"
                        + "shape: line;"
                        + "size:2px;"
                        + "fill-color: #4F4E5A;"
                        + "arrow-size: 7px, 7px;"
                        + "}"

                        + " edge.toTarget {"
                        + "shape: line;"
                        + "size:2px;"
                        + "fill-color: #BEB7C2;"
                        + "arrow-size: 7px, 7px;"
                        + "}"
        );
    }


}
