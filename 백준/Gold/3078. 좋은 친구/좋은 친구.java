import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, K;
    private static int[] arr, nameWindow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().length();
        }
        nameWindow = new int[21];
        for(int i = 0 ; i < K; i++) {
            nameWindow[arr[i]]++;
        }
        long ans = 0;
        for(int i = 0 ; i < N; i++) {
            if(i + K < N) {
                nameWindow[arr[i + K]]++;
            }
            if(nameWindow[arr[i]] > 1) {
                ans += nameWindow[arr[i]] - 1;
            }
            nameWindow[arr[i]]--;
        }
        System.out.println(ans);
    }
}