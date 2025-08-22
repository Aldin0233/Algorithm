import java.io.*;
import java.util.*;

public class Main {

    static int L, n;
    static int[] arr;
    static int low = 0, high = 1001; //집합 내의 모든 정수는 1보다 크거나 같고, 1000보다 작거나 같다
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        arr = new int[L];
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] == n) {
                System.out.println(0);
                return;
            } else if(arr[i] < n) {
                low = Math.max(low, arr[i]); //N보다 작은 것중 가장 큰 것
            } else {
                high = Math.min(high, arr[i]); //N보다 큰 것중 가장 작은 것
            }
        }
        ans = (n - low) * (high - n);
        ans--; //n ~ n 제거

        System.out.println(ans);
    }

}



