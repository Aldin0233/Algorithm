import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long[] arr;
//    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        long M = 0;
        int l = 0, r = N % 2 == 0 ? N - 1 : N - 2;
        while (l < r) {
            M = Math.max(M, arr[l++] + arr[r--]);
        }
        if(N % 2 == 1) M = Math.max(arr[N - 1], M);
        System.out.println(M);
    }

}

