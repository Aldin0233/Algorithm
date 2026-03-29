import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[][][] visited;
    static String[] map;

    static final int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static final int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new String[8];
        for(int i = 0; i < 8; i++) {
            map[i] = br.readLine();
        }
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[8][8][10];
        visited[7][0][0] = true;
        q.offer(new int[]{7, 0, 0});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int t = cur[2];
            if((r == 0 && c == 7) || t == 9) {
                System.out.println(1);
                return;
            }
            for(int d = 0; d < 9; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int nt = t + 1;
                if(invalid(nr, nc) && !hasWall(nr, nc, t)) {
                    if(!visited[nr][nc][nt]) {
                        visited[nr][nc][nt] = true;
                        q.offer(new int[]{nr, nc, nt});
                    }
                }
            }
        }
        System.out.println(0);
    }

    static boolean invalid(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8;
    }

    static boolean hasWall(int r, int c, int t) {
        int curTimeOriginR = r - t;
        if(curTimeOriginR < 0) return false;
        if(map[curTimeOriginR].charAt(c) == '#') {
            return true;
        }
        int nextTimeOriginR = r - (t + 1);
        if(nextTimeOriginR < 0) return false;
        return map[nextTimeOriginR].charAt(c) == '#';
    }

}

