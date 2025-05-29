import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int K, N;
    static int[] lengths;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lengths = new int[K];
        int max_len = 0;
        for (int i = 0; i < K; i++) {
            lengths[i] = Integer.parseInt(br.readLine());
            max_len = Math.max(max_len, lengths[i]);

        }
        ans = -1;
        long left = 1; long right = max_len;
        while(left <= right) {
            long mid = (left + right) / 2;
            if(canCutEnough(mid)) {
                left = mid + 1;
                ans = Math.max(ans, mid);
            } else {
                right = mid - 1;
            }
        }


        System.out.println(ans);
    }

    private static boolean canCutEnough(long length) {
        int cnt = 0;
        for(int i = 0; i < K; i++) {
            cnt += (int) (lengths[i] / length);
        }
        return cnt >= N;
    }





}
