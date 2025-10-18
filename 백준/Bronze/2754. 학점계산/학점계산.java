import java.io.*;
import java.util.*;


public class Main {

    static String grade;
    static double[] alphabet = {4.0, 3.0, 2.0, 1.0, 0, 0.0};
    static double ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        grade = br.readLine();
        ans = alphabet[grade.charAt(0) - 'A'];
        if(ans != 0) {
            ans += subGrade(grade.charAt(1));
        }
        System.out.println(ans);
    }

    private static double subGrade(char c) {
        if(c == '+') return 0.3;
        else if(c == '-') return -0.3;
        return 0;
    }


}



