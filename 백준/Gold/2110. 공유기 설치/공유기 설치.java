import java.io.*;
import java.util.*;

public class Main {

    static int N, C;
    static int[] arr;
    static int ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int left = 1; int right = arr[N - 1] - arr[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canNetworkShareInstall(mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean canNetworkShareInstall(int dist) {
        int count = 1;
        int last = arr[0];
        for (int i = 1; i < N; i++) {
            if (arr[i] - last >= dist) {
                count++;
                last = arr[i];
            }
        }
        return count >= C;
    }


}
