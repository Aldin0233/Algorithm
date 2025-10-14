import java.io.*;
import java.util.*;

public class Main {

    static int T, N, K, M;
    static int[] P;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            make();
            K = Integer.parseInt(br.readLine());
            for(int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int refA = find(Integer.parseInt(st.nextToken()));
                int refB = find(Integer.parseInt(st.nextToken()));
                union(refA, refB);
            }
            ans.append(String.format("Scenario %d:\n", t));
            M = Integer.parseInt(br.readLine());
            for(int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int refA = find(Integer.parseInt(st.nextToken()));
                int refB = find(Integer.parseInt(st.nextToken()));
                ans.append(refA == refB ? 1: 0).append("\n");
            }
            ans.append("\n");
        }
        ans.setLength(ans.length() - 2);

        System.out.println(ans);

    }

    private static void make() {
        P = new int[N];
        for(int i = 0; i < N; i++) {
            P[i] = i;
        }
    }

    private static int find(int x) {
        if(P[x] == x) return x;
        return P[x] = find(P[x]);
    }

    private static void union(int a, int b) {
        P[a] = P[b];
    }

}

