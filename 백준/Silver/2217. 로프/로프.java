import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long[] weights;
    static long max;
//    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weights = new long[N];
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(weights);
        long max = 0L;
        for(int i = 0; i < N; i++) {
            max = Math.max(max, weights[i] * (N - i));
        }
        System.out.println(max);

    }

}

