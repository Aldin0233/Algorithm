import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Cleaner cleaner = new Cleaner(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(cleaner.startCleaning(map));
    }

}

class Cleaner {
    int x, y;
    int d;
    int cleaned;
    static int[] dx = {-1, 0, 1, 0}; //북 동 남 서
    static int[] dy = {0, 1, 0, -1};

    public Cleaner(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.cleaned = 0;
    }

    public int startCleaning(int[][] map) {
        while(true) {
            if(map[x][y] == 0) {
                cleaned++;
                map[x][y] = -1;
            }
            if(!tryMoveToUncleaned(map)) {
                if(!backMove(map)) return cleaned;
            }
        }
    }

    //일단 회전 후 빈칸 있는지 탐색 후 전진(어차피 그 칸 빈칸 아니면 회전 해야함)
    private boolean tryMoveToUncleaned(int[][] map) {
        for(int i = 1 ; i <= 4 ; i++) {
            int turnD = (d + 4 - i) % 4;
            int nx = x + dx[turnD];
            int ny = y + dy[turnD];
            if(map[nx][ny] == 0) {
                this.d = turnD;
                this.x = nx;
                this.y = ny;
                return true;
            }
        }
        return false;
    }

    //벽이 없을때 백무빙
    private boolean backMove(int[][] map) {
        int backD = (d + 2) % 4;
        int nx = x + dx[backD];
        int ny = y + dy[backD];
        if(map[nx][ny] != 1) {
            x = nx;
            y = ny;
            return true;
        }
        return false;
    }

//    private boolean inMap(int[][] map, int nx, int ny) {
//        return nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length;
//    } 외벽이 있어서 굳이 확인할 필요 없다.

}
