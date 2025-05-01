
import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static int[] arr;
    static Map<Integer, Long> sum = new HashMap<>();
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        leftCalculate(0, 0);
        rightCalculate(N/2, 0, 0);
        if (S == 0) ans--;
        System.out.println(ans);
    }
    static void leftCalculate(int idx, int leftPartSum) {
        if(idx == N / 2) {
            sum.put(leftPartSum, sum.getOrDefault(leftPartSum, 0L) + 1);
            return;
        }
        leftCalculate(idx + 1, leftPartSum + arr[idx]);
        leftCalculate(idx + 1, leftPartSum);
    }

    static void rightCalculate(int idx, int rightPartSum, int size) {
        if(idx == N) {
            ans += sum.getOrDefault(S - rightPartSum, 0L);
            return;
        }
        rightCalculate(idx + 1, rightPartSum + arr[idx], size + 1);
        rightCalculate(idx + 1, rightPartSum, size);
    }




}
