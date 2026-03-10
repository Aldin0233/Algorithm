import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] printArr;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        printArr = new int[N][M];
        if(N >= 2 && M >= 2) {
            ans.append("4\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    ans.append(i % 2 * 2 + j % 2 + 1).append(" ");
                }
                ans.append("\n");
            }

        } else {
            if(N == 1 && M == 1) {
                ans.append("1\n1");
            } else if(N == 1) {
                ans.append("2\n");
                for(int i = 0; i < M; i++) {
                    ans.append(i % 2 == 0 ? 1 : 2).append(" ");
                }
            } else {
                ans.append("2\n");
                for(int i = 0; i < N; i++) {
                    ans.append(i % 2 == 0 ? 1 : 2).append("\n");
                }
            }
        }
        System.out.println(ans);
    }

}

