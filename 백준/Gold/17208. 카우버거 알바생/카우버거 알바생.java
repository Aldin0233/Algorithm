import java.io.*;
import java.util.*;

public class Main {

    private static int N, M, K, ans;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Order[] orders = new Order[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int burgerOrder = Integer.parseInt(st.nextToken());
            int potatoOrder = Integer.parseInt(st.nextToken());
            orders[i] = new Order(burgerOrder, potatoOrder);
        }

        dp = new int[M + 1][K + 1];
        for (Order order : orders) {
            for (int m = M; m >= order.burgerOrder; m--) {
                for (int k = K; k >= order.potatoOrder; k--) {
                    dp[m][k] = Math.max(dp[m][k], dp[m - order.burgerOrder][k - order.potatoOrder] + 1);
                }
            }
        }
        ans = dp[M][K];
        System.out.println(ans);
    }
}

class Order {
    int burgerOrder;
    int potatoOrder;

    public Order(int burgerOrder, int potatoOrder) {
        this.burgerOrder = burgerOrder;
        this.potatoOrder = potatoOrder;
    }
}