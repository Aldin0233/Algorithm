
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, X;
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
        X = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int l = 0, r = N - 1;
        while(l < r) {
            int sum = arr[l] + arr[r];
            if(sum == X) {
                ans++;
                l++;
            } else if(sum < X) {
                l++;
            } else {
                r--;
            }
        }
        System.out.println(ans);
    }



}


