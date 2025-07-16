import java.io.*;
import java.util.*;

public class Main {

    static int N, M, X, Y, K;
    static Dice dice = new Dice();
    static int[][] field;
    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; k++) {
            int order = Integer.parseInt(st.nextToken());
            int nr = X + dr[order];
            int nc = Y + dc[order];
            if(inValid(nr, nc)) {
                ans.append(dice.changeSide(order)).append('\n');
                X = nr;
                Y = nc;
                if(field[X][Y] == 0) {
                    field[X][Y] = dice.getBottom();
                } else {
                    dice.changeBottom(field[X][Y]);
                    field[X][Y] = 0;
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean inValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}

class Dice {
    int[] diceNumber = {0, 0, 0, 0, 0, 0};
    int nowTop = 0, nowNorth = 1, nowEast = 2;

    int changeSide(int d) {
        switch (d) {
            case 1: turnEast(); break;
            case 2: turnWest(); break;
            case 3: turnNorth(); break;
            case 4: turnSouth();
        }
        return diceNumber[nowTop];
    }

    private void turnEast() {
        int newTop = calSideIdx(nowEast);
        int newEast = nowTop;
        nowTop = newTop;
        nowEast = newEast;
    }

    private void turnWest() {
        int newTop = nowEast;
        int newEast = calSideIdx(nowTop);
        nowTop = newTop;
        nowEast = newEast;
    }

    private void turnNorth() {
        int newTop = calSideIdx(nowNorth);
        int newNorth = nowTop;
        nowTop = newTop;
        nowNorth = newNorth;
    }

    private void turnSouth() {
        int newTop = nowNorth;
        int newNorth = calSideIdx(nowTop);
        nowTop = newTop;
        nowNorth = newNorth;
    }

    int getBottom() {
        return diceNumber[calSideIdx(nowTop)];
    }

    void changeBottom(int num) {
        diceNumber[calSideIdx(nowTop)] = num;
    }

    private int calSideIdx(int idx) {
        return (idx + 3) % 6;
    }


}
