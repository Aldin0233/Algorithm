import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; t++) {
            int K, N;
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int ans = -1;
            int[][] classInfo = new int[4][N];
            for(int i = 0 ; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    classInfo[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[] A = new int[N * N];
            int[] B = new int[N * N];
            int idx = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0 ; j < N; j++) {
                    A[idx] = classInfo[0][i] + classInfo[1][j];
                    B[idx++] = classInfo[2][i] + classInfo[3][j];
                }
            }
            Arrays.sort(A);
            Arrays.sort(B);
            for(int i = 0 ; i < N * N; i++) {
                int nowA = A[i];
                int needB = K - nowA;
                int Bidx = Arrays.binarySearch(B, needB);
                if(Bidx < 0) {
                    Bidx = -(Bidx + 1);
                }

                for(int checkPos : new int[]{Bidx, Bidx-1}){
                    if(checkPos >= 0 && checkPos < B.length){
                        int candidate = nowA + B[checkPos];
                        if(moreFit(ans, candidate, K)){
                            ans = candidate;
                        }
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean moreFit(int ans, int candidate, int K) {
        if(ans == -1) {
            return true;
        } else if(Math.abs(candidate - K) == Math.abs(ans - K)) {
            return candidate < ans;
        }
        return Math.abs(candidate - K) < Math.abs(ans - K);
    }

}

