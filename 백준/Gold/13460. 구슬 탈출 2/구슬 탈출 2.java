import java.io.*;
import java.util.*;

public class Main {

    static int N, M; //세로 크기 N, 가로크기 M
    static Point hole, red, blue; //구멍, 빨간구슬, 파란구슬
    static boolean[][] wallMap; //주변은 벽으로 다 둘러쌓여있으니 벽만으로 인맵 체크
    static int ans = 0; //결과값
    static int[] dx = {-1, 1, 0, 0}; //방향
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wallMap = new boolean[N][M]; //벽 처리할 맵
        for(int i = 0 ; i < N ; i++) { //입력
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
        ans = bfs(); //리팩토링 -> 메서드 분리
        System.out.println(ans);
    }

    private static int bfs() {
        Queue<NowState> q = new LinkedList<>();
        q.add(new NowState(red, blue, 0));
        while(!q.isEmpty()) {
            NowState now = q.poll();
            for(int d = 0; d < 4; d++) {
                Point newRed, newBlue;
                if(isRedFirst(now.red, now.blue, d)) { //방향에 따라 먼저 움직일 구슬 선정
                    newRed = move(now.red, now.blue, d);
                    newBlue = move(now.blue, newRed, d);
                } else {
                    newBlue = move(now.blue, now.red, d);
                    newRed = move(now.red, newBlue, d);
                }
                if(newBlue.samePos(hole)) { //파랑 구멍에 들어갔다면 무조건 필요 없는 결과
                    continue;
                }
                if(now.move < 10) {
                    if(newRed.samePos(hole)) { //빨간 구슬만 들어간 상황
                        return now.move + 1;
                    }
                    q.add(new NowState(newRed, newBlue, now.move + 1));
                }
            }
        }
        return -1;
    }

    private static Point move(Point moveOne, Point another, int d) {
        int x = moveOne.x;
        int y = moveOne.y; 
        while(!wallMap[x + dx[d]][y + dy[d]]) { //벽인지부터 파악
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(hole.samePos(nx, ny)) return new Point(nx, ny); //구멍이면 정지
            else if(another.samePos(nx, ny)) return new Point(x, y); // 다른 구슬 있으면 그 전에 정지
            x = nx;
            y = ny;
        }
        return new Point(x, y); //벽에 닿은 상황임
    }

    private static boolean isRedFirst(Point red, Point blue, int d) {
        boolean first = d % 2 == 0; //- 방향으로 움직이는지 + 방향으로 움직이는지
        if(d < 2) { //어느 축인지
            if(red.y != blue.y) return true;
            else return red.x > blue.x ^ first;
        } else {
            if(red.x != blue.x) return true;
            else return red.y > blue.y ^ first;
        }
    }



}

class NowState { //구슬 위치랑 이동한 횟수만 저장해서 시점으로 판단
    Point red;
    Point blue;
    int move;
    public NowState(Point red, Point blue, int move) {
        this.red = red;
        this.blue = blue;
        this.move = move;
    }
}

class Point { //구슬
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean samePos(int x, int y) { //좌표값 이동 중 확인하는 메서드
        return this.x == x && this.y == y;
    }

    public boolean samePos(Point p) { //포인트랑 직접 비교하는 메서드
        return this.x == p.x && this.y == p.y;
    }

}

