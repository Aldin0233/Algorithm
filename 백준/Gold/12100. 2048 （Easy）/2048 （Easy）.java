import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] firstMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                firstMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(firstMap, 0);
        System.out.println(ans);
    }

    private static void dfs(int[][] map, int time) {
        if(time == 5) {
            findBig(map);
            return;
        }
        for(int d = 0 ; d < 4 ; d++) {
            dfs(move(map, d), time + 1);
        }
    }

    private static int[][] move(int[][] map, int direction) {

        int[][] newMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            int[] temp = new int[N]; //한 줄 씩
            int idx = (direction % 2 == 0) ? 0 : N - 1;
            int previousNum = 0;

            for (int j = 0; j < N; j++) {
                int px, py;

                if (direction == 0) {
                    px = j; py = i;
                } else if (direction == 1) {
                    px = N - 1 - j; py = i;
                } else if (direction == 2) {
                    px = i; py = j;
                } else {
                    px = i; py = N - 1 - j;
                }

                if (map[px][py] == 0) continue;

                if (previousNum != 0 && previousNum == map[px][py]) {
                    temp[idx - ((direction % 2 == 0) ? 1 : -1)] = previousNum * 2;
                    previousNum = 0;
                } else {
                    temp[idx] = map[px][py];
                    previousNum = map[px][py];
                    idx += (direction % 2 == 0) ? 1 : -1;
                }
            }


            for (int j = 0; j < N; j++) {
                if (direction == 0) {
                    newMap[j][i] = temp[j];
                } else if (direction == 1) {
                    newMap[N - 1 - j][i] = temp[j];
                } else if (direction == 2) {
                    newMap[i][j] = temp[j];
                } else {
                    newMap[i][N - 1 - j] = temp[j];
                }
            }
        }

        return newMap;
    }

    private static void findBig(int[][] map) {
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                ans = Math.max(ans, map[i][j]);
            }
        }
    }


}
