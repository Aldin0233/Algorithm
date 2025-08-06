import java.io.*;
import java.util.*;

public class Main {

    static final char[] charToIdxInfo = {'.', 'R', 'G', 'B', 'P', 'Y'};
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int[][] map = new int[12][6];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0 ; i < 12; i++) {
            String str = br.readLine();
            for(int j = 0 ; j < 6 ; j++) {
                map[i][j] = charToIdx(str.charAt(j));
            }
        }
        int combo = 0;
        boolean breakPuyo = true;
        while(true) {
            breakPuyo = findComb();
            if(breakPuyo) combo++;
            else break;
            downRemainPuyo();
        }
        ans = combo;
        System.out.println(ans);
    }

    private static boolean findComb() {
        boolean find = false;
        boolean[][] visited  = new boolean[12][6];
        for(int i = 0 ; i < 12; i++) {
            for(int j = 0 ; j < 6 ; j++) {
                if(!visited[i][j] && map[i][j] > 0) {
                    int size = bfs(i, j, visited);
                    if(size >= 4) {
                        find = true;
                        puyoBreakBfs(i, j);
                    }
                }
            }
        }
        return find;
    }

    private static int bfs(int x, int y, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        int target = map[x][y];
        int size = 1;
        visited[x][y] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == target) {
                    visited[nr][nc] = true;
                    size++;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return size;
    }

    private static void puyoBreakBfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        int target = map[x][y];
        map[x][y] = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(isValid(nr, nc) && map[nr][nc] == target) {
                    map[nr][nc] = 0;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < 12 && y >= 0 && y < 6;
    }

    private static void downRemainPuyo() {
        for(int j = 0; j < 6; j++) {
            int idx = 11;
            for(int i = 11; i >= 0; i--) {
                if(map[i][j] > 0) {
                    if(idx == i) {
                        idx--;
                        continue;
                    }
                    map[idx--][j] = map[i][j];
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int charToIdx(char c) {
        for(int i = 0 ; i < 6 ; i++) {
            if(c == charToIdxInfo[i]) {
                return i;
            }
        }
        return -1;
    }



}



