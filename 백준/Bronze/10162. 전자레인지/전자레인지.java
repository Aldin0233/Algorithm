import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N % 10 != 0) {
            System.out.println(-1);
        } else {
            int A = N / 300;
            N %= 300;
            int B = N / 60;
            N %= 60;
            System.out.println(String.format("%d %d %d", A, B, N / 10));
        }
    }

}