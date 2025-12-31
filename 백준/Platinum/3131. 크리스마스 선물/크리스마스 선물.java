import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int A, B, N;
    static long[] gifts;
    static long[] prefixDiff;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        gifts = new long[N + 1];
        for (int i = 1; i < N; i++) {
            gifts[i] = Integer.parseInt(br.readLine());
        }
        prefixDiff = new long[N + 1];
        long diff = 0;
        for(int i = 1; i < N; i++) {
            prefixDiff[i] = gifts[i];
            if(i % 2 == 1) diff += prefixDiff[i];
            else diff -= prefixDiff[i];
        }
        prefixDiff[0] = diff + B;
        long ans = 0;
        for(int i = N; i >= 1; i--) {
            if(i % 2 == 1) diff -= 2L * prefixDiff[i];
            else diff += 2L * prefixDiff[i];
            long diffV1= prefixDiff[i] + 1;
            long diffV2= prefixDiff[i - 1];
            long l = Math.max(i % 2 == 1 ? diff + diffV1 : diff - diffV2, A);
            long r = Math.min(i % 2 == 1 ? diff + diffV2 : diff - diffV1, B);
            if(l <= r) ans += (r - l + 1);
        }
        System.out.println(ans);
    }

}



