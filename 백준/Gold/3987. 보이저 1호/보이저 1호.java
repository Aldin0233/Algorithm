import java.io.*;
import java.util.*;

public class Main {

    private static int N, M, PR, PC;
    private static String[] map;
    private static boolean[][][] visited;
    private static int[] dr = {1, -1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static char[] chars = {'D', 'U', 'L', 'R'};
    private static int ansD = -1;
    private static int ansSecond = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N];
        for(int i = 0 ; i < N; i++) {
            map[i] = br.readLine();
        }
        st = new StringTokenizer(br.readLine());
        PR = Integer.parseInt(st.nextToken()) - 1;
        PC = Integer.parseInt(st.nextToken()) - 1;
        for(int d = 0 ; d < 4; d++) {
            visited = new boolean[N][M][4];
            dfs(PR + dr[d], PC + dc[d], d, 1, d);
        }
        System.out.println(chars[ansD]);
        System.out.println(ansSecond == Integer.MAX_VALUE ? "Voyager" : ansSecond);
    }

    private static void dfs(int r, int c, int d, int second, int primaryD) {
        if(!inMap(r, c)) {
            if(second > ansSecond) {
                ansSecond = second;
                ansD = primaryD;
            }
            return;
        }
        if(visited[r][c][d]) {
            ansSecond = Integer.MAX_VALUE;
            ansD = primaryD;
            return;
        }
        visited[r][c][d] = true;
        char space = map[r].charAt(c);
        if(space == 'C') {
            if(second > ansSecond) {
                ansSecond = second;
                ansD = primaryD;
            }
            return;
        }
        if(space == '/' || space == '\\' ) {
            int nd = turn(d, space);
            dfs(r + dr[nd], c + dc[nd], nd, second + 1, primaryD);
        } else {
            dfs(r + dr[d], c + dc[d], d, second + 1, primaryD);
        }
    }

    private static int turn(int d, char planet) {
        if(planet == '/') {
            if(d == 0) {
                return 2;
            } else if(d == 1) {
                return 3;
            } else if(d == 2) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if(d == 0) {
                return 3;
            } else if(d == 1) {
                return 2;
            } else if(d == 2) {
                return 1;
            } else {
                return 0;
            }

        }
    }

    private static boolean inMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }


}

