import java.io.*;
import java.util.*;

public class Main {
    static int N, K, L, ans = -1;
    static Queue<int[]> bodyQueue = new LinkedList<>();
    static boolean[][] bodyVisited;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        board = new int[N][N];
        for(int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            board[a][b] = 1;
        }
        bodyVisited = new boolean[N][N];
        bodyQueue.add(new int[]{0, 0});
        int lastTime = 0;
        int[] headInfo = new int[] {0, 0, 1};
        L = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < L ; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            if(!move(headInfo, lastTime, X)) {
                break;
            }
            lastTime = X;
            turn(headInfo, C);
        }
        if(ans == -1) {
            move(headInfo, lastTime, Integer.MAX_VALUE);
        }

        System.out.println(ans);
    }

    private static boolean move(int[] headInfo, int lastTime, int X) {
        int movingTime = X - lastTime;
        int x = headInfo[0];
        int y = headInfo[1];
        for(int t = 1; t <= movingTime; t++) {
            int nx = x + dx[headInfo[2]];
            int ny = y + dy[headInfo[2]];
            if(inMap(nx, ny)) {
                if(bodyVisited[nx][ny]) {
                    ans = lastTime + t;
                    return false;
                } else if(board[nx][ny] != 1) {
                    int[] point = bodyQueue.poll();
                    bodyVisited[point[0]][point[1]] = false;
                }
                x = nx;
                y = ny;
                board[x][y] = 0;
                bodyVisited[x][y] = true;
                bodyQueue.add(new int[]{x, y});
            } else {
                ans = lastTime + t;
                return false;
            }
        }
        headInfo[0] = x;
        headInfo[1] = y;
        return true;
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static void turn(int[] info, char C) {
        if(C == 'L') {
            if(info[2] == 0) {
                info[2] = 3;
            } else {
                info[2]--;
            }
        } else {
            if(info[2] == 3) {
                info[2] = 0;
            } else {
                info[2]++;
            }
        }
    }

}

