import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 500000;   // 높이 합의 최대치
    static int N;
    static int[] h;
    static int[] dp, next;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        h = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[MAX + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < N; i++) { //최대 50번
            next = dp.clone(); //매 블럭마다 새로운 dp 배열 생성
            int cur = h[i];
            for (int diff = 0; diff <= MAX; diff++) { //최대 50만
                if (dp[diff] < 0) continue;
                next[diff] = Math.max(next[diff], dp[diff]);

                if (diff + cur <= MAX) //일단 높은 쪽에 더한 것을 차이점에 저장
                    next[diff + cur] = Math.max(next[diff + cur], dp[diff]);
                int newDiff, newVal; //낮은 쪽에 더해보기
                if (cur > diff) {
                    newDiff = cur - diff;
                    newVal = dp[diff] + diff;
                } else {
                    newDiff = diff - cur;
                    newVal = dp[diff] + cur;
                }
                if (newDiff <= MAX)
                    next[newDiff] = Math.max(next[newDiff], newVal);
            }
            dp = next;
        }
        //차이점이 0인 최대 높이가 있는지 확인 0이면 없음
        System.out.println(dp[0] == 0 ? -1 : dp[0]);
    }
}