import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] sales = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sales[Integer.parseInt(st.nextToken())]++;
        }

        int ans = 0;
        ans += handleSales(0, 3); // Pair 0 and 3, weight 3
        ans += handleSales(1, 2); // Pair 1 and 2, weight 3
        ans += handleSales(2, 0); // Pair 2 and 0, weight 2
        ans += handleSales(3, 1); // Pair 3 and 1, weight 2
        ans += handleSales(1, 0); // Pair 1 and 0, weight 1
        ans += handleSales(2, 3); // Pair 2 and 3, weight 1

        System.out.println(ans);
    }

    private static int handleSales(int A, int B) {
        int pairedCount = Math.min(sales[A], sales[B]);
        if(pairedCount == 0) return 0;
        sales[A] -= pairedCount;
        sales[B] -= pairedCount;
        return pairedCount * (A ^ B);
    }
}