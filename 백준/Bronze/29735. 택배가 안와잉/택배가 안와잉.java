import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int stTime, edTime, beforePost, timeCost;

    static int resultDay;
    static String resultTime;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        testInput();
        testProcess();
        testOutput();
    }

    private static void testInput() {
        stTime = changeTime(sc.next());
        edTime = changeTime(sc.next());
        beforePost = sc.nextInt();
        timeCost = sc.nextInt();
    }

    private static void testProcess() {
        int canPostTime = edTime - stTime;
        int canPostPerDay = canPostTime / timeCost;
        if(canPostTime% timeCost==0){
            canPostPerDay--;
        }
        beforePost++; 
        while(canPostPerDay < beforePost) {
            beforePost -= canPostPerDay;
            resultDay++;
        }
        resultTime = changeTime((timeCost * beforePost) + stTime);
    }

    private static int changeTime(String time){
        int timeInt = 0;
        timeInt += (time.charAt(0) - '0') * 600;
        timeInt += (time.charAt(1) - '0') * 60;
        timeInt += (time.charAt(3) - '0') * 10;
        timeInt += (time.charAt(4) - '0');
        return timeInt;
    }

    private static String changeTime(int time) {
        int hour = time / 60;
        int min = time % 60;
        String timeString = "";
        if(hour < 10){
            timeString += 0;
        }
        timeString += hour + ":";
        if(min < 10) {
            timeString += 0;
        }
        timeString += min;
        return timeString;
    }

    private static void testOutput() {
        System.out.println(resultDay + "\n" + resultTime);
    }
}
