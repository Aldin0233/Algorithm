import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] nums;
    static int[] result;
    static boolean[] visited;
    static boolean found = false;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        result = new int[N];
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        backtrack(0);
        for (int num : result) ans.append(num).append(" ");
        System.out.println(ans.toString().trim());
    }

    static void backtrack(int depth) {
        if (found) return;
        if (depth == N) {
            found = true;
            return;
        }
        int last = -1;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && nums[i] != last) {
                if (depth > 0 && result[depth - 1] + 1 == nums[i]) continue;
                visited[i] = true;
                result[depth] = nums[i];
                backtrack(depth + 1);
                last = nums[i];
                visited[i] = false;
                if (found) return;
            }
        }
    }
}
