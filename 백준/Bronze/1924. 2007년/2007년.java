import java.io.*;
import java.util.*;

public class Main {

    static String[] weeks = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    static int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int day = -1;
        for(int i = 0; i < x - 1; i++) {
            day += months[i];
        }
        day += y;
        System.out.println(weeks[day % 7]);
    }

}