import java.io.*;
import java.util.*;

public class Main {

    static int N, S, ans = Integer.MAX_VALUE;
    static int[] arr;
    static long[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        prefixSum = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }

        int left = 0;
        int right = 1;
        while(left < N && right <= N) {
            long now = prefixSum[right] - prefixSum[left];
            if(now >= S) {
                ans = Math.min(ans, right - left);
                left++;
            } else {
                right++;
            }
            if(left == right) {
                right++;
            }
        }
        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }


}