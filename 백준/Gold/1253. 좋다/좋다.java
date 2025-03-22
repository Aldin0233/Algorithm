import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] ints;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ints);
        int ans = solution();
        System.out.println(ans);
    }

    private static int solution() {
        int cnt = 0;
        for(int i = 0 ; i < N; i++) {
            int left = 0, right = N - 1;
            while(left < right) {
                if(left == i || right == i) {
                    if(left == i) left++;
                    else right--;
                } else {
                    long sum = ints[left] + ints[right];
                    if(sum > ints[i]) {
                        right--;
                    } else if(sum < ints[i]) {
                        left++;
                    } else {
                        cnt++;
                        break;
                    }
                }
            }
        }
        return cnt;
    }

}
