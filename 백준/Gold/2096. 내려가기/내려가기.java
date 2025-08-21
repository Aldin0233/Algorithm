
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Score score;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        score = new Score();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            score.changeNewScore(x, y, z);
        }
        int[] ansScore = score.getMaxAndMinScore();
        ans.append(ansScore[0]).append(" ").append(ansScore[1]);
        System.out.println(ans);
    }

}

class Score {
    int maxA, maxB, maxC;
    int minA, minB, minC;
    Score() {
        maxA = maxB = maxC = minA = minB = minC = 0;
    }

    public void changeNewScore(int a, int b, int c) {
        int newMaxA, newMaxB, newMaxC, newMinA, newMinB, newMinC;
        newMaxA = Math.max(a + this.maxA, a + this.maxB);
        newMaxB = Math.max(b + this.maxA, Math.max(b + this.maxB, b + this.maxC));
        newMaxC = Math.max(c + this.maxB, c + this.maxC);
        newMinA = Math.min(a + this.minA, a + this.minB);
        newMinB = Math.min(b + this.minA, Math.min(b + this.minB, b + this.minC));
        newMinC = Math.min(c + this.minB, c + this.minC);
        this.maxA = newMaxA;
        this.maxB = newMaxB;
        this.maxC = newMaxC;
        this.minA = newMinA;
        this.minB = newMinB;
        this.minC = newMinC;
    }

    public int[] getMaxAndMinScore() {
        return new int[] {Math.max(maxA, Math.max(maxB, maxC)), Math.min(minA, Math.min(minB, minC))};
    }
}