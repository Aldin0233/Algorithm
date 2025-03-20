import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = N;
        for (int joker = 0; joker < N; joker++) {
            boolean[] used = new boolean[M];
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                if (joker == i) continue;

                int flag = 0;
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0) flag++;
                }

                if (flag == 0) continue;
                else if (flag == 1) {
                    int color = -1;
                    for (int j = 0; j < M; j++) {
                        if (arr[i][j] != 0) {
                            color = j;
                            break;
                        }
                    }
                    if (used[color]) cnt++;
                    else used[color] = true;
                } else if (flag > 1) {
                    cnt++;
                }
            }

            ans = Math.min(ans, cnt);
        }

        System.out.println(ans);
    }
}