import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int MAX = 1000000;
        int[] count = new int[MAX + 1];
        int[] dp = new int[MAX + 1];

        Arrays.fill(dp, -1); 

        // 입력 처리
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            count[arr[i]]++; 
        }

        for (int i = 1; i <= MAX; i++) {
            if (count[i] > 0) {
                for (int j = i; j <= MAX; j += i) {
                    dp[j] += count[i];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(dp[arr[i]]).append("\n");
        }
        System.out.print(sb);
    }
}