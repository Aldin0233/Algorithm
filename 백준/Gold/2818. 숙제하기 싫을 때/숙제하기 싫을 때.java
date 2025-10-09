import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static long ans;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Dice dice = new Dice();
        ans = dice.getScore(R, C);

        System.out.println(ans);
    }
}

class Dice {
    int nowUp, nowRight, nowFront;

    Dice () {
        nowUp = 1;
        nowRight = 3;
        nowFront = 2;
    }

    public long getScore(int R, int C) {
        long sum = 0;
        int defaultTurn = (C - 1) / 4;
        int move = (C - 1) % 4;
        for(int i = 0; i < R; i++) {
            sum += nowUp;
            sum += 14L * defaultTurn;
            boolean goRight = i % 2 == 0;
            for(int j = 0; j < move; j++) {
                if(goRight) rollRight();
                else rollLeft();
                sum += nowUp;
            }
            rollDown();
        }
        return sum;
    }

    private int getOpposite(int num) {
        return 7 - num;
    }

    private void rollRight() {
        int tmp = nowUp;
        nowUp = getOpposite(nowRight);
        nowRight = tmp;
    }

    private void rollLeft() {
        int tmp = nowUp;
        nowUp = nowRight;
        nowRight = getOpposite(tmp);
    }

//    문제를 잘못 이해했다. 각 끝부분이 아니라 모든 윗부분을 더해야함
//    private void rollHalf() {
//        nowUp = getOpposite(nowUp);
//        nowRight = getOpposite(nowRight);
//    }

    private void rollDown() {
        int tmp = nowUp;
        nowUp = getOpposite(nowFront);
        nowFront = tmp;
    }



}

