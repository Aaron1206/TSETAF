import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.util.Scanner;

public class Tsetaf_function {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Tsetaf tsetaf = parser.Parse("/Users/zhujinlong/Downloads/TSETAF/file/TSETAF1.apx");
        //  type:0();1(];2[];3[)
        Setaf setaf = tsetaf.atTime(new Availability_interval(0, 130, 1));
        System.out.println(setaf);
        //System.out.println(setaf.getComplete_extension());
        setaf.getSubset();
        setaf.getConflictFree();
      /*  int next = -1;
        while (next != 0) {
            System.out.println("please select the function:");
            System.out.println("0-----exit");
            System.out.println("1-----generate tsetaf");
            System.out.println("2-----generate setaf");
            System.out.println("3-----generate tsetaf graph");
            System.out.println("4-----generate setaf graph");
            System.out.println("5-----show setaf HN-categoriser");
            Scanner scanner = new Scanner(System.in);
            next = scanner.nextInt();
            switch (next) {
                case 0:
                    break;
                case 1:
                    System.out.println(tsetaf);
                    break;
                case 2:
                    System.out.println(setaf);
                    break;
                case 3:
                    Graph G1 = tsetaf.getGraph();
                    write(G1);
                    Viewer vTSETAF = G1.display();
                    break;
                case 4:
                    Graph G2 = setaf.getGraph();
                    write(G2);
                    Viewer vSETAF = G2.display();
                    break;
                case 5:
                    System.out.println(setaf.getHN_categoriser(10));
                    break;
                case 6:
                    try {
                        setaf.convertAspartix();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("please input the correct number:");
                    break;
            }
        }*/
/*
        try {
            setaf.convertAspartix();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        System.out.println(setaf.getHN_categoriser(10));*/

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
