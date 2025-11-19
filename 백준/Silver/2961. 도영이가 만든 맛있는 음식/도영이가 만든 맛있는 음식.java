import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Taste[] tasteInfo;
    static int minTasteDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tasteInfo = new Taste[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            tasteInfo[i] = new Taste(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        dfs(0, 0);

        System.out.println(minTasteDiff);
    }

    private static void dfs(int depth, int used) {
        if(depth == N) {
            int diff = checkTaste(used);
            minTasteDiff = Math.min(minTasteDiff, diff);
            return;
        }
        dfs(depth + 1, used | (1 << depth));
        dfs(depth + 1, used);

    }

    private static int checkTaste(int used) {
        if(used == 0) return Integer.MAX_VALUE;
        int sour = 1, bitter = 0;
        for (int i = 0; i < N; i++) {
            if((used & (1 << i)) != 0) {
                sour *= tasteInfo[i].sourness;
                bitter += tasteInfo[i].bitter;
            }
        }
        return Math.abs(bitter - sour);
    }

}

class Taste {
    int sourness, bitter;

    Taste(int sourness, int bitter) {
        this.sourness = sourness;
        this.bitter = bitter;
    }
}


