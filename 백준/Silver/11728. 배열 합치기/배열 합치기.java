import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] A, B;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        int aIdx = 0, bIdx = 0;
        while(aIdx < N && bIdx < M) {
            if(A[aIdx] <= B[bIdx]) {
                ans.append(A[aIdx++]).append(" ");
            } else {
                ans.append(B[bIdx++]).append(" ");
            }
        }
        if(aIdx == N) {
            for(int i = bIdx; i < M; i++) {
                ans.append(B[i]).append(" ");
            }
        } else {
            for(int i = aIdx; i < N; i++) {
                ans.append(A[i]).append(" ");
            }
        }
        System.out.println(ans);
    }

}