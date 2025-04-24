import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 0, right = 1;
        while (right < N) {
            int diff = arr[right] - arr[left];
            if(diff >= M) {
                ans = Math.min(ans, diff);
                if(ans == M) {
                    break;
                }
                left++;
            }
            else {
                right++;
            }
        }
        System.out.println(ans);
    }

}