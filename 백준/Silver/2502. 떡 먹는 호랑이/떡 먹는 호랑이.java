
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int D, K;
    static State[] dp;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new State[D + 1];
        dp[1] = new State(1, 0);
        dp[2] = new State(0, 1);
        canBe(dfs(D));
        System.out.println(ans);
    }

    private static State dfs(int d) {
        if(dp[d] != null) { //1과 2도 걸러짐
            return dp[d];
        }
        return dp[d] = new State(dfs(d - 1), dfs(d - 2));
    }

    private static void canBe(State s) {
        int firstDay = s.firstDay;
        int secondDay = s.secondDay;
        for(int A = 1; A < K; A++) {
            int first = firstDay * A;
            int rest = K - first;
            if(rest % secondDay == 0) {
                int B = rest / secondDay;
                if(A <= B) {
                    ans.append(A).append("\n").append(B);
                    return;
                }
            }
        }
    }


}

class State {
    int firstDay;
    int secondDay;
    State() {

    }

    State(int firstDay, int secondDay) {
        this.firstDay = firstDay;
        this.secondDay = secondDay;
    }

    State(State s1, State s2) {
        this.firstDay = s1.firstDay + s2.firstDay;
        this.secondDay = s1.secondDay + s2.secondDay;
    }
}









