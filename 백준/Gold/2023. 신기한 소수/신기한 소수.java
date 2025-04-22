import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> results = new ArrayList<>();
    static StringBuilder ans = new StringBuilder();
    //와 메모리제한!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(0, 0);

        Collections.sort(results);

        for (int num : results) {
            ans.append(num).append("\n");
        }
        System.out.print(ans);
    }

    static void dfs(int depth, int num) {
        if (depth == N) {
            results.add(num);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            int next = num * 10 + i;
            if (next == 0) continue;
            if (isPrime(next)) {
                dfs(depth + 1, next);
            }
        }
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

}
