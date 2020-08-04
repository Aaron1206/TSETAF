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
        Setaf setaf = tsetaf.atTime(new Availability_interval(110, 500, 1));
    /*
	 System.out.println("please select the function:");
        System.out.println("1. generate tsetaf\n2. generate setaf\n3. generate tsetaf graph\n4. generate setaf graph");
        Scanner scanner = new Scanner(System.in);
        int next = scanner.nextInt();
        if (next == 1) {
            System.out.println(tsetaf);
        } else if (next == 2) {
            System.out.println(setaf);
        } else if (next == 3) {
            Graph G1 = tsetaf.getGraph();
            write(G1);
            Viewer vTSETAF = G1.display();
        }else if(next==4){
            Graph G2 = setaf.getGraph();
            write(G2);
            Viewer vSETAF = G2.display();
        }


        try {
            setaf.convertAspartix();
        } catch (IOException e) {
            e.printStackTrace();
        }
	*/
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
        //   Viewer vTSETAF = G1.display();
        //Viewer vSETAF = G2.display();

        System.out.println(setaf.getHN_categoriser(10));

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
