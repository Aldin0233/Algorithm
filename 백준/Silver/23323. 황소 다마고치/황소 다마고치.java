import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static long N, M;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Long.parseLong(st.nextToken());
            M = Long.parseLong(st.nextToken());
            long surviveDays = 64 - Long.numberOfLeadingZeros(N);
            surviveDays += M;
            ans.append(surviveDays).append("\n");
        }
        System.out.println(ans);
    }



}




