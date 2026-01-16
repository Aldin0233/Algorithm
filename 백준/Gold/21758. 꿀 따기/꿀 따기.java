import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static long[] arr;
    static long[] prefixSum;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        prefixSum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }
        long totalS = prefixSum[N];
        long ans = 0;
        for(int i = 1; i <= N - 2; i++) {
            long bee1 = totalS - arr[0] - arr[i];
            long bee2 = totalS - prefixSum[i + 1];
            ans = Math.max(ans, bee1 + bee2);
        }
        for(int i = 1; i <= N - 2; i++) {
            long bee1 = totalS - arr[i] - arr[N - 1];
            long bee2 = prefixSum[i];
            ans = Math.max(ans, bee1 + bee2);
        }
        long base = totalS - arr[0] - arr[N - 1];
        for(int i = 1; i <= N - 2; i++) {
            ans = Math.max(ans, base + arr[i]);
        }

        System.out.println(ans);
    }

}




