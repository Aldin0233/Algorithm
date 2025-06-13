
import java.io.*;
import java.util.*;

public class Main {

    static int K;

    static GearWheel[] gears;

    static final int LEFT = -1;
    static final int RIGHT = 1;

    static int ansScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gears = new GearWheel[4];
        for (int i = 0; i < 4; i++) {
            gears[i] = new GearWheel(br.readLine());
        }
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            gearTurnTry(idx, direction);
        }
        ansScore = calScore();
        System.out.println(ansScore);
    }

    private static int calScore() {
        int score = 0;
        for(int i = 0; i < 4; i++) {
            score += (int) (gears[i].get12Setting() * Math.pow(2, i));
        }
        return score;
    }

    private static void gearTurnTry(int idx, int direction) {
        int[] turnOrder = new int[4];
        turnOrder[idx] = direction;
        int orderStart = direction;
        for(int i = idx + 1; i < 4; i++) {
            if(gears[i - 1].getRightSetting() != gears[i].getLeftSetting()) {
                orderStart = -orderStart;
                turnOrder[i] = orderStart;
            } else {
                break;
            }
        }
        orderStart = direction;
        for(int i = idx - 1; i >= 0; i--) {
            if(gears[i + 1].getLeftSetting() != gears[i].getRightSetting()) {
                orderStart = -orderStart;
                turnOrder[i] = orderStart;
            } else {
                break;
            }
        }
        gearTurn(turnOrder);
    }

    private static void gearTurn(int[] turnOrder) {
        for(int i = 0; i < 4; i++) {
            if(turnOrder[i] == LEFT) {
                gears[i].toLeft();
            } else if(turnOrder[i] == RIGHT) {
                gears[i].toRight();
            }
        }
    }

}

class GearWheel {
    int[] setting;
    int nowLeftIdx;

    GearWheel(String line) {
        setting = new int[8];
        for(int i = 0; i < 8; i++) {
            setting[i] = line.charAt(i) - '0';
        }
        nowLeftIdx = 6;
    }

    int getLeftSetting() {
        return setting[nowLeftIdx];
    }

    int getRightSetting() {
        return setting[(nowLeftIdx + 4) % 8];
    }

    int get12Setting() {
        return setting[(nowLeftIdx + 2) % 8];
    }

    void toLeft() {
        nowLeftIdx = (nowLeftIdx + 1) % 8;
    }

    void toRight() {
        nowLeftIdx = (nowLeftIdx + 7) % 8;
    }

}

