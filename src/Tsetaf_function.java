public class Tsetaf_function {
    public static void main(String[] args) {
        Parser parser = new Parser();

        Tsetaf AF  = parser.Parse("/Users/zhujinlong/Downloads/TSETAF/file/TSETAF1.apx");
        System.out.println(AF.atTime(new Availability_interval(70,120,1)));

        System.out.println(AF);




    }

}
