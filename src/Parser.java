import java.io.*;
import java.util.ArrayList;
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
            HashSet<String> arguments = new HashSet<>();
            HashMap<String, String> AttacksBuilder = new HashMap<>();
            HashMap<String, HashSet<String>> Relation = new HashMap<>();
            HashMap<String, String> Argument_time = new HashMap<>();
            HashMap<String,Time_list> Timelist = new HashMap<>();

            while (true) {

                try {
                    if (!((st = br.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println(st);


                String regexarg = "arg\\(\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                String regextime_list = "time_list\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                String regexmem1 = "mem1\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*(\\(|\\[)\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*(\\)|\\])\\s*\\)";
                String regexatt = "att\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                String regexmem2 = "mem2\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                //String regexav = "av\\(\\s*([a-z,A-Z]+)\\s*\\)\\s*=\\s*(\\(|\\[)\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*(\\)|\\])\\s*|U\\s*(\\(|\\[)\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*(\\)|\\])";
                //String regexmem ="mem\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*|\\s*(\\(|\\[)\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*(\\)|\\])\\s*\\)";


                Pattern patternarg = Pattern.compile(regexarg);
                Pattern patterntime_list = Pattern.compile(regextime_list);
                Pattern patternmem1 = Pattern.compile(regexmem1);
                Pattern patternatt = Pattern.compile(regexatt);
                Pattern patternmem2 = Pattern.compile(regexmem2);
                //Pattern patternav = Pattern.compile(regexav);

                Matcher matcher = patternarg.matcher(st);
                while (matcher.find()){
                    arguments.add(matcher.group(1));
                }
                //System.out.println(arguments);


                matcher = patterntime_list.matcher(st);
                //int groupCount1 = matcher.groupCount();
                while (matcher.find()){
                   // for (int i = 0; i <= groupCount1; i++) {
                        // Group i substring
                       // System.out.println("Group " + i + ": " + matcher.group(i));
                    Argument_time.put(matcher.group(1),matcher.group(2));
                    }
                //System.out.println(Argument_time);

                matcher = patternmem1.matcher(st);
                //int groupCount2 = matcher.groupCount();
                while (matcher.find()){
                    //for (int i = 0; i <= groupCount2; i++) {
                        // Group i substring
                    //    System.out.println("Group " + i + ": " + matcher.group(i));
                   // }
                    int flag = 0;
                    if (matcher.group(2)=="("&&matcher.group(5)==")"){
                        flag = 0;
                    }else if (matcher.group(2)=="("&&matcher.group(5)=="]"){
                        flag = 1;
                    }else if (matcher.group(2)=="["&&matcher.group(5)=="]"){
                        flag = 2;
                    }else if (matcher.group(2)=="["&&matcher.group(5)=="]"){
                        flag = 3;
                    }
                    Availability_interval availability_interval = new Availability_interval(Integer.parseInt(matcher.group(3)),Integer.parseInt(matcher.group(4)),flag);
                    ArrayList<Availability_interval> availability_intervalArrayList = new ArrayList<>();
                    availability_intervalArrayList.add(availability_interval);
                    Time_list time_list = new Time_list(availability_intervalArrayList);
                    Timelist.put(matcher.group(1),time_list);
                }
                //System.out.println(Timelist);

                matcher = patternatt.matcher(st);
                int groupCount = matcher.groupCount();
                while (matcher.find()) {
                    for (int i = 0; i <= groupCount; i++) {
                        System.out.println("Group " + i + ": " + matcher.group(i));
                    }
                    //group(1) is r, group(2) is attacked
                    AttacksBuilder.put(matcher.group(1),matcher.group(2));
                }

                matcher = patternmem2.matcher(st);
                //int groupCount = matcher.groupCount();
                while (matcher.find()){
                    // for (int i = 0; i <= groupCount ; i++) {
                    //    System.out.println("Group " + i + ": " + matcher.group(i));
                    // }
                    HashSet<String> attackerSet = new HashSet<>();
                    System.out.println("attacker:"+matcher.group(2));
                    attackerSet.add(matcher.group(2));
                    String attacked = AttacksBuilder.get(matcher.group(1));// according to key r get value attacked
                    System.out.println("attacked:"+attacked);
                    Relation.put(attacked,attackerSet);
                }
            }


            System.out.println(AttacksBuilder);// r = c means r attacks c
            System.out.println(Relation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return TAF;
    }
}