import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static int ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
        solve(0, new int[N], new boolean[N]);
        System.out.println(ans);
    }

    private static void solve(int idx, int[] selectedOrder, boolean[] visited) {
        if(idx == N) {
            check(selectedOrder);
            return;
        }
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selectedOrder[idx] = i;
                solve(idx + 1, selectedOrder, visited);
                visited[i] = false;
            }
        }
    }

    private static void check(int[] selectedOrder) {
        int sum = 0;
        int idx = 0;
        int cnt = 0;
        while(sum < 50) {
            int checkNextSum = sum;
            int checkIdx = idx;
            while(checkIdx < N && checkNextSum < sum + 50) {
                checkNextSum += arr[selectedOrder[checkIdx++]];
            }
            if(checkNextSum == sum + 50) {
                cnt++;
            }
            sum += arr[selectedOrder[idx++]];
        }
        ans = Math.max(cnt, ans);
    }







}