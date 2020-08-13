import org.graphstream.graph.Graph;
import org.graphstream.ui.view.Viewer;
import java.io.IOException;
import java.util.Scanner;

public class Tsetaf_mainfunction {
    public static void main(String[] args) {
        Parser parser = new Parser();
        System.out.println("******** Welcome to use T-SETAF system *********");
        System.out.println("Please input the file path:");
        Scanner scanner3 = new Scanner(System.in);
        String filePath = scanner3.next();
        String path =filePath;
        //"/Users/zhujinlong/Downloads/TSETAF/file/TSETAF1.apx"
        Tsetaf tsetaf = parser.Parse(path);
       int next = -1;
        while (next != 0) {

            System.out.println("Please select the functions:");
            System.out.println("0. exit");
            System.out.println("1. generate tsetaf");
            Scanner scanner = new Scanner(System.in);
            next = scanner.nextInt();
            switch (next) {
                case 0:
                    break;
                case 1:
                    while (next != 0) {
                        System.out.println(tsetaf);
                        System.out.println("Please select the functions:");
                        System.out.println("0. exist");
                        System.out.println("1. show tsetaf graph");
                        System.out.println("2. generate setaf ");
                        Scanner scanner1 = new Scanner(System.in);
                        next = scanner1.nextInt();
                        switch (next) {
                            case 0:
                                break;
                            case 1:
                                Graph G1 = tsetaf.getGraph();
                                write(G1);
                                Viewer vTSETAF = G1.display();
                                vTSETAF.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
                                break;
                            case 2:
                                Setaf setaf = tsetaf.atTime();
                                while (next != 0) {
                                    System.out.println(setaf);
                                    System.out.println("Please select the functions:");
                                    System.out.println("0. exist");
                                    System.out.println("1. show setaf graph");
                                    System.out.println("2. show setaf HN-categoriser");
                                    System.out.println("3. generate setaf .apx file");
                                    System.out.println("4. get extensions:");
                                    Scanner scanner2 = new Scanner(System.in);
                                    next = scanner2.nextInt();
                                    switch (next) {
                                        case 0:
                                            break;
                                        case 1:
                                            Graph G2 = setaf.getGraph();
                                            write(G2);
                                            Viewer vSETAF = G2.display();
                                            vSETAF.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
                                            break;
                                        case 2:
                                            System.out.println("Nh-categoriser:\n" + setaf.getNh_categoriser(10));
                                            break;
                                        case 3:
                                            try {
                                                setaf.convertAspartix();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 4:
                                            System.out.println("Conflict-free:\n" + setaf.getConflictFree());
                                            System.out.println("Admissible extension:\n" + setaf.getAdmissible());
                                            System.out.println("Complete extension:\n" + setaf.getComplete());
                                            System.out.println("Preferred extension:\n" + setaf.getPreferred());
                                            System.out.println("Grounded extension:\n" + setaf.getGrounded());
                                            break;
                                        default:
                                            System.out.println("Please input the correct number:");
                                            break;
                                    }
                                }
                                break;
                            default:
                                System.out.println("Please input the correct number:");
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Please input the correct number:");
                    break;
            }
        }
        System.out.println("******** You have logged out the system ********");
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
