import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static boolean ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long usedSkip = -1, unusedSkip = 1;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            char operator1 = s1.charAt(0);
            int x1 = Integer.parseInt(s1.substring(1));
            char operator2 = s2.charAt(0);
            int x2 = Integer.parseInt(s2.substring(1));
            long newUnusedSkip = -1, newUsedSkip = -1;
            if(unusedSkip > 0) {
                newUnusedSkip = Math.max(
                        apply(unusedSkip, operator1, x1),
                        apply(unusedSkip, operator2, x2)
                );
                newUsedSkip = Math.max(newUsedSkip, unusedSkip);
            }
            if(usedSkip > 0) {
                newUsedSkip = Math.max(
                        newUsedSkip,
                        Math.max(
                                apply(usedSkip, operator1, x1),
                                apply(usedSkip, operator2, x2)
                        )
                );
            }
            usedSkip = newUsedSkip;
            unusedSkip = newUnusedSkip;
            if(usedSkip <= 0 && unusedSkip <= 0) {
                System.out.println("ddong game");
                return;
            }
        }
        System.out.println(Math.max(usedSkip, unusedSkip));
    }

    private static long apply(long v, char operator, int x) {
        switch (operator) {
            case '+' : return v + x;
            case '-' : return v - x;
            case '*' : return v * x;
            case '/' : return v / x;
        }
        return -1;
    }


}