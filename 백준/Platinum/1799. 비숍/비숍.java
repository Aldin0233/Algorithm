import java.io.*;
import java.util.*;

public class Main {

    static int fieldSize;
    static boolean[][] canPick;
    static boolean[] upperLeftDiagonal, upperRightDiagonal;
    static int blackMax, whiteMax, ans; //흑 백 나눠서 판단

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fieldSize = Integer.parseInt(br.readLine());
        canPick = new boolean[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < fieldSize; j++) {
                canPick[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }
        upperLeftDiagonal = new boolean[(fieldSize * 2) -1];
        upperRightDiagonal = new boolean[(fieldSize * 2) -1];
        dfs(0, 0, 0, true);
        dfs(0, 0, 0, false);
        ans = blackMax + whiteMax;
        System.out.println(ans);
    }

    static void dfs(int x, int y, int nowPick, boolean isBlack) {
        if(isBlack) blackMax = Math.max(blackMax, nowPick);
        else whiteMax = Math.max(whiteMax, nowPick);
        for(int i = x; i < fieldSize; i++) {
            for(int j = i == x ? y : 0; j < fieldSize; j++) {
                if(((i + j) % 2 == 0) == isBlack && canPick[i][j]) {
                    int leftDiagonalIdx = calLeftDiagonalIdx(i, j);
                    int rightDiagonalIdx = calRightDiagonalIdx(i, j);
                    if(!upperLeftDiagonal[leftDiagonalIdx] && !upperRightDiagonal[rightDiagonalIdx]) {
                        upperLeftDiagonal[leftDiagonalIdx] = true;
                        upperRightDiagonal[rightDiagonalIdx] = true;
                        dfs(i, j + 1, nowPick + 1, isBlack);
                        upperLeftDiagonal[leftDiagonalIdx] = false;
                        upperRightDiagonal[rightDiagonalIdx] = false;
                    }
                }
            }
        }
    }

    //좌상단 대각선 인덱스 부여
    static int calLeftDiagonalIdx(int x, int y) {
        return (y - x - 1) + fieldSize;
    }

    //우상단 대각선 인덱스 부여
    static int calRightDiagonalIdx(int x, int y) {
        return (fieldSize * 2) - (x + y + 2);
    }


}
