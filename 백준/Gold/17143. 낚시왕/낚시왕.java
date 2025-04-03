import java.io.*;
import java.util.*;

public class Main {

    static int R, C, M;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Shark[][] map = new Shark[R][C];
        for(int i = 0; i < M; i++) {
            Shark shark = strToShark(br.readLine());
            map[shark.r][shark.c] = shark;
        }
        int ans = 0;
        for(int i = 0; i < C; i++) {
            for(int j = 0; j < R; j++) {
                if(map[j][i] != null) {
                    ans += map[j][i].z;
                    map[j][i] = null;
                    break;
                }
            }
            Shark[][] newMap = new Shark[R][C];
            for(int k = 0; k < R; k++) {
                for(int l = 0; l < C; l++) {
                    if(map[k][l] != null) {
                        Shark shark = map[k][l];
                        shark.move(R, C, dr, dc);
                        newMap[shark.r][shark.c] = shark.compare(newMap[shark.r][shark.c]);
                    }
                }
            }
            map = newMap;
        }


        System.out.println(ans);
    }

    private static Shark strToShark(String s) {
        StringTokenizer st = new StringTokenizer(s);
        return new Shark(tti(st) - 1, tti(st) - 1, tti(st), tti(st) - 1, tti(st));
    }

    private static int tti(StringTokenizer s) { //tokenToInt
        return Integer.parseInt(s.nextToken());
    }

}

class Shark {
    int r, c;
    int s, d, z;
    public Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }

    public void move(int R, int C, int[] dr, int[] dc) {
        int move = s;
        if(d < 2) {
            move %= (R - 1) * 2;
            for(int now = 0; now < move; now++) {
                if(r == 0) d = 1;
                if(r == R - 1) d = 0;
                r += dr[d];
            }
        } else {
            move %= (C - 1) * 2;
            for(int now = 0; now < move; now++) {
                if(c == 0) d = 2;
                if(c == C - 1) d = 3;
                c += dc[d];
            }
        }
    }

    public Shark compare(Shark o) {
        if(o == null) {
            return this;
        } else {
            return o.z > this.z ? o : this;
        }
    }
}