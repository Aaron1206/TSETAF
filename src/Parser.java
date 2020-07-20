import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public Tsetaf Parse(String filepath) {
        File file = new File(filepath);

        BufferedReader br = null;
        Tsetaf TAF = new Tsetaf();
        try {
            br = new BufferedReader(new FileReader(file));
            String st = "";
            HashSet<Argument> arguments = new HashSet<>();
           // HashMap<String, HashSet<String>> AttacksBuilder = new HashMap<>();
            HashMap<String, HashSet<Argument>> Relation = new HashMap<>();
            HashMap<String, Time_list> Argument_time = new HashMap<>();

            while (true) {

                try {
                    if (!((st = br.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(st);


                String regexarg = "arg\\(\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                String regexav = "av\\(\\s*([a-z,A-Z]+)\\s*\\)\\s*=\\s*(\\(|\\[)\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*(\\)|\\])\\s*|U\\s*(\\(|\\[)\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*(\\)|\\])";
                String regexatt = "att\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                String regexmem = "mem\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)";

                Pattern patternarg = Pattern.compile(regexarg);
                Pattern patternav = Pattern.compile(regexav);
                Pattern patternatt = Pattern.compile(regexatt);
                Pattern patternmem = Pattern.compile(regexmem);

                Matcher matcher = patternav.matcher(st);
                int groupCount = matcher.groupCount();
                if (matcher.find()){
                    for (int i = 0; i <= groupCount; i++) {
                        // Group i substring
                        System.out.println("Group " + i + ": " + matcher.group(i));
                    }

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return TAF;
    }
}