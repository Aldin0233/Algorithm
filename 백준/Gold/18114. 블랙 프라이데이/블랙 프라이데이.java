import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static boolean find;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[] W = new int[N];
        find = false;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
            if(W[i] == C) {
                System.out.println(1);
                return;
            }
        }
        Arrays.sort(W);
        roop: for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;
                int sum = W[i] + W[j];
                if(sum > C) continue;
                else if(sum == C) {
                    System.out.println(1);
                    return;
                }
                int idx = Arrays.binarySearch(W, C - sum);
                if(idx < 0 || idx == i || idx == j) continue;
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }


}