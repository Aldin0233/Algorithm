import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R;
    static int[][] arr;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int operation = Integer.parseInt(st.nextToken());
            switch (operation) {
                case 1: firstOperation(); break;
                case 2: secondOperation(); break;
                case 3: thirdOperation(); break;
                case 4: fourthOperation(); break;
                case 5: fifthOperation(); break;
                case 6: sixthOperation(); break;
            }
        }

        for (int[] row : arr) {
            for (int anInt : row) {
                ans.append(anInt).append(' ');
            }
            ans.append('\n');
        }
        System.out.println(ans);
    }

    private static void firstOperation() {
        //상하반전
        int rowLen = arr.length;
        int colLen = arr[0].length;
        int[][] copied = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                copied[rowLen - 1 - i][j] = arr[i][j];
            }
        }
        arr = copied;
    }

    private static void secondOperation() {
        //좌우반전
        int rowLen = arr.length;
        int colLen = arr[0].length;
        int[][] copied = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                copied[i][colLen - 1 - j] = arr[i][j];
            }
        }
        arr = copied;
    }

    private static void thirdOperation() {
        //우측으로 90도
        int rowLen = arr.length;
        int colLen = arr[0].length;
        int[][] copied = new int[colLen][rowLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                copied[j][rowLen - 1 - i] = arr[i][j];
            }
        }
        arr = copied;
    }

    private static void fourthOperation() {
        //좌측으로 90도
        int rowLen = arr.length;
        int colLen = arr[0].length;
        int[][] copied = new int[colLen][rowLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                copied[colLen - 1 - j][i] = arr[i][j];
            }
        }
        arr = copied;
    }

    private static void fifthOperation() {
        int rowLen = arr.length;
        int colLen = arr[0].length;
        int[][] copied = new int[rowLen][colLen];
        int rowMid = rowLen / 2;
        int colMid = colLen / 2;
        for(int i = 0; i < rowMid; i++) {
            for(int j = 0; j < colMid; j++) {
                //1번을 2번으로
                copied[i][colMid + j] = arr[i][j];
                //2번을 3번으로
                copied[rowMid + i][colMid + j] = arr[i][colMid + j];
                //3번을 4번으로
                copied[rowMid + i][j] = arr[rowMid + i][colMid + j];
                //4번을 1번으로
                copied[i][j] = arr[rowMid + i][j];
            }
        }
        arr = copied;
    }

    private static void sixthOperation() {
        int rowLen = arr.length;
        int colLen = arr[0].length;
        int[][] copied = new int[rowLen][colLen];
        int rowMid = rowLen / 2;
        int colMid = colLen / 2;
        for(int i = 0; i < rowMid; i++) {
            for(int j = 0; j < colMid; j++) {
                //1번을 4번으로
                copied[rowMid + i][j] = arr[i][j];
                //4번을 3번으로
                copied[rowMid + i][colMid + j] = arr[rowMid + i][j];
                //3번을 2번으로
                copied[i][colMid + j] = arr[rowMid + i][colMid + j];
                //2번을 1번으로
                copied[i][j] = arr[i][colMid + j];
            }
        }
        arr = copied;
    }


}