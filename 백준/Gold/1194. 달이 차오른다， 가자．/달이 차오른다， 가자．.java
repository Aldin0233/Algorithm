

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    static int[][] map;
    // . = 0 # = -1 1 == 1
    // key a b c d e f = 3 4 5 6 7 8
    // door A B C D E F = 9 10 11 12 13 14

    static Queue<int[]> q;
    static boolean[][][][][][][][] visited;

    static int X, Y;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = charToInt(line.charAt(j), i, j);
            }
        }
        visited = new boolean[N][M][2][2][2][2][2][2];
        ans = -1;
        search: while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(isValid(nr, nc) && !isVisited(cur, nr, nc)) {
                    if(map[nr][nc] == 1) {
                        ans = cur[2] + 1;
                        break search;
                    } else if(map[nr][nc] >= 3 && map[nr][nc] <= 8) {
                        int[] newState = calNewState(cur, nr, nc);
                        newState[map[nr][nc]] = 1;
                        q.add(newState);
                    } else if(map[nr][nc] >= 9) {
                        if(cur[map[nr][nc] - 6] != 1) continue;
                        q.add(calNewState(cur, nr, nc));
                    } else if(map[nr][nc] == 0) {
                        q.add(calNewState(cur, nr, nc));
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static int[] calNewState(int[] cur, int nr, int nc) {
        int[] state = new int[]{nr, nc, cur[2] + 1, cur[3], cur[4], cur[5], cur[6], cur[7], cur[8]};
        visit(state);
        return state;
    }

    private static void visit(int[] cur) {
        visited[cur[0]][cur[1]][cur[3]][cur[4]][cur[5]][cur[6]][cur[7]][cur[8]] = true;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static boolean isVisited(int[] cur, int nr, int nc) {
        return visited[nr][nc][cur[3]][cur[4]][cur[5]][cur[6]][cur[7]][cur[8]];
    }

    private static int charToInt(char c, int i, int j) {
        if(c == '#') {
            return -1;
        } else if(c >= 'a' && c <= 'f') {
            return (c - 'a' + 3);
        } else if(c >= 'A' && c <= 'F') {
            return (c - 'A' + 9);
        }
        if(c == '0') {
            q.add(new int[]{i, j, 0, 0, 0, 0, 0, 0, 0});
        } else if(c == '1') {
            return 1;
        }
        return 0;
    }

}


