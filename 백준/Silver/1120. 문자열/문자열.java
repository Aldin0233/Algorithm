import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String A, B;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = st.nextToken();
        B = st.nextToken();
        int aLen = A.length();
        int bLen = B.length();
        int maxDiff = Integer.MAX_VALUE;
        for(int i = 0; i <= bLen - aLen; i++) {
            int diff = 0;
            for(int j = 0; j < aLen; j++) {
                if(A.charAt(j) != B.charAt(i + j) && ++diff >= maxDiff) break;
            }
            maxDiff = Math.min(maxDiff, diff);
        }
        System.out.println(maxDiff);
    }

}

