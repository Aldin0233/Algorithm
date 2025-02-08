import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static long ans;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int idx = 1;
        for(int i = 1 ; i <= N; i++) {
            if(arr[i] > idx) {
                ans += arr[i] - idx;
                idx++;
            } else if(arr[i] == idx) {
                idx++;
            }
        }
        System.out.println(ans);
    }

}
