import java.io.*;
import java.util.*;

class Main {

    static int N, K;
    static int[][] dp;
    static final int div = 10007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0 ;
        for(int i = 0 ; i < 5; i++) {
            int n = Integer.parseInt(br.readLine());
            sum += Math.max(n, 40);
        }
        System.out.println(sum/5);
    }
}