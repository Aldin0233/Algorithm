import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static Point hole, red, blue;
    static boolean[][] wallMap;
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wallMap = new boolean[N][M];
        for(int i = 0 ; i < N ; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                if(line.charAt(j) == '#') {
                    wallMap[i][j] = true;
                } else if(line.charAt(j) == 'R') {
                    red = new Point(i, j);
                } else if(line.charAt(j) == 'B') {
                    blue = new Point(i, j);
                } else if(line.charAt(j) == 'O') {
                    hole = new Point(i, j);
                }
            }
        }
        Queue<NowState> q = new LinkedList<>();
        q.add(new NowState(red, blue, 0));
        while(!q.isEmpty()) {
            NowState now = q.poll();
            for(int d = 0; d < 4; d++) {
                Point newRed, newBlue;
                if(isRedFirst(now.red, now.blue, d)) {
                    newRed = move(now.red, now.blue, d);
                    newBlue = move(now.blue, newRed, d);
                } else {
                    newBlue = move(now.blue, now.red, d);
                    newRed = move(now.red, newBlue, d);
                }
                if(newBlue.samePos(hole)) {
                    continue;
                }
                if(now.move < 10) {
                    if(newRed.samePos(hole)) {
                        System.out.println(now.move + 1);
                        return;
                    }
                    q.add(new NowState(newRed, newBlue, now.move + 1));
                }
            }
        }
        System.out.println(-1);
    }

    private static Point move(Point moveOne, Point another, int d) {
        int x = moveOne.x;
        int y = moveOne.y;
        while(!wallMap[x + dx[d]][y + dy[d]]) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(hole.samePos(nx, ny)) return new Point(nx, ny);
            else if(another.samePos(nx, ny)) return new Point(x, y);
            x = nx;
            y = ny;
        }
        return new Point(x, y);
    }

    private static boolean isRedFirst(Point red, Point blue, int d) {
        boolean first = d % 2 == 0;
        if(d < 2) {
            if(red.y != blue.y) return true;
            else return red.x > blue.x ^ first;
        } else {
            if(red.x != blue.x) return true;
            else return red.y > blue.y ^ first;
        }
    }



}

class NowState {
    Point red;
    Point blue;
    int move;
    public NowState(Point red, Point blue, int move) {
        this.red = red;
        this.blue = blue;
        this.move = move;
    }
}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean samePos(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean samePos(Point p) {
        return this.x == p.x && this.y == p.y;
    }

}

