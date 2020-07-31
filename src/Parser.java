import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
            HashSet<String> arguments = new HashSet<>(); //a, b, c,d
            HashMap<String, String> AttacksBuilder = new HashMap<>(); //{ r1: b, r2: c, ... }
            HashMap<String, HashSet<String>> Relation = new HashMap<>();  //{ r4: [b ,c ], r1 : [a], .... }
            HashMap<String, String> Argument_time = new HashMap<>();
            HashMap<String,Time_list> Timelist = new HashMap<>();

            while (true) {

                try {
                    if (!((st = br.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //The REGEX
                String regexarg = "arg\\(\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                String regextime_list = "time_list\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                String regexmem1 = "mem1\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*(\\(|\\[)\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*(\\)|\\])\\s*\\)";
                String regexatt = "att\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)";
                String regexmem2 = "mem2\\(\\s*([a-z,0-9,A-Z]+)\\s*,\\s*([a-z,0-9,A-Z]+)\\s*\\)";

                //The pattern matchers
                Pattern patternarg = Pattern.compile(regexarg);
                Pattern patterntime_list = Pattern.compile(regextime_list);
                Pattern patternmem1 = Pattern.compile(regexmem1);
                Pattern patternatt = Pattern.compile(regexatt);
                Pattern patternmem2 = Pattern.compile(regexmem2);


                //We recognise the arguments
                Matcher matcher = patternarg.matcher(st);
                while (matcher.find()){
                    arguments.add(matcher.group(1));
                }

                //We recognise the Availability interval names
                matcher = patterntime_list.matcher(st);
                while (matcher.find()){
                    Argument_time.put(matcher.group(1),matcher.group(2));
                }

                //We recognise the Availability interval content
                matcher = patternmem1.matcher(st);
                while (matcher.find()) {

                    int flag = 0;
                    if (matcher.group(2) == "(" && matcher.group(5) == ")") {
                        flag = 0;
                    } else if (matcher.group(2) == "(" && matcher.group(5) == "]") {
                        flag = 1;
                    } else if (matcher.group(2) == "[" && matcher.group(5) == "]") {
                        flag = 2;
                    } else if (matcher.group(2) == "[" && matcher.group(5) == "]") {
                        flag = 3;
                    }

                    Availability_interval availability_interval = new Availability_interval(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), flag);

                    if (Timelist.get(matcher.group(1)) == null)
                        Timelist.put(matcher.group(1), new Time_list());

                    if (!Timelist.get(matcher.group(1)).getTime_list().contains(availability_interval))
                        Timelist.get(matcher.group(1)).getTime_list().add(availability_interval);
                }

                //We recognise the attack names
                matcher = patternatt.matcher(st);
                while (matcher.find()) {
                    AttacksBuilder.put(matcher.group(1),matcher.group(2));
                }

                //We recognise the attack content
                matcher = patternmem2.matcher(st);
                while (matcher.find()){

                    if(Relation.get(matcher.group(1)) == null){
                        Relation.put(matcher.group(1), new HashSet<String>());
                    }
                    Relation.get(matcher.group(1)).add(matcher.group(2));
                }
            }

            // We create the arguments
            for(String s : arguments)
            {
                TAF.addArgument(new Argument(s));
            }

            //We create the relations
            for(String s : AttacksBuilder.keySet()){
                if(arguments.contains(AttacksBuilder.get(s))){
                    if(arguments.containsAll(Relation.get(s))){

                        TAF.addRelation(new Relation(F(AttacksBuilder.get(s),TAF.getSetOfArguments()),F(Relation.get(s), TAF.getSetOfArguments())));


                    }
                }
            }

            //We associate the time to the arguments
            for(String s : Argument_time.keySet()){
                TAF.addTime(F(s, TAF.getSetOfArguments()), Timelist.get(Argument_time.get(s)));
            }
            //We put infinite for the arguments we did not have time
            Availability_interval InfiniteAV = new Availability_interval(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
            Time_list InfiniteTL = new Time_list();
            InfiniteTL.add(InfiniteAV);
            for(String s : arguments){
                if(!Argument_time.keySet().contains(s))
                    TAF.addTime(F(s, TAF.getSetOfArguments()), InfiniteTL);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return TAF;
    }

    public HashSet<Argument> F(HashSet<String> S, HashSet<Argument> SA){
        HashSet<Argument> result = new HashSet<>();
        Iterator<Argument> iter = SA.iterator();
        while(iter.hasNext()){
            Argument a = iter.next();
            if(S.contains(a.getName()))
                result.add(a);
        }
        return result;
    }

    public Argument F(String s, HashSet<Argument> SA){
        Iterator<Argument> iter = SA.iterator();
        while(iter.hasNext()){
            Argument a = iter.next();
            if(s.equals(a.getName()))
                return a;
        }
        return null;
    }

}