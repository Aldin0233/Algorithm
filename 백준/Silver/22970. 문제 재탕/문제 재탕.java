
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static boolean isUp;
    static int[] arr;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] LIS = new int[N];
        int[] LDS = new int[N];
        Arrays.fill(LIS, 1);
        Arrays.fill(LDS, 1);
        for (int i = 1; i < N; i++) {
            if(arr[i] > arr[i - 1]) {
                LIS[i] = LIS[i - 1] + 1;
            }
        }

        for(int i = N - 2; i >= 0; i--) {
            if(arr[i] > arr[i + 1]) {
                LDS[i] = LDS[i + 1] + 1;
            }
        }

        for(int i = 0; i < N; i++) {
            ans = Math.max(ans, LIS[i] + LDS[i] - 1);
        }

        System.out.println(ans);
    }

}









