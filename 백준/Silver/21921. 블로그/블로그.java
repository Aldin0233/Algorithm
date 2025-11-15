import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, X;
    static int[] prefixSum;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        prefixSum = new int[N + 1];
        prefixSum[0] = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }
        int maxVisiter = 0;
        int periodCnt = 1;
        for(int i = X; i <= N; i++) {
            int nowVisiter = prefixSum[i] - prefixSum[i - X];
            if(nowVisiter > maxVisiter) {
                maxVisiter = nowVisiter;
                periodCnt = 1;
            } else if(nowVisiter == maxVisiter) {
                periodCnt++;
            }
        }
        if(maxVisiter == 0) {
            System.out.println("SAD");
            return;
        }
        System.out.printf("%d\n%d", maxVisiter, periodCnt);
    }

}

