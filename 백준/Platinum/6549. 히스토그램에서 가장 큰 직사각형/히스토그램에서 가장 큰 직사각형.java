import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long[] heights;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        while (!line.equals("0")) {
            StringTokenizer st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            heights = new long[N + 1];
            for (int i = 0; i < N; i++) {
                heights[i] = Long.parseLong(st.nextToken());
            }
            heights[N] = 0;
            long maxArea = getMaxArea();
            ans.append(maxArea).append("\n");
            line = br.readLine();
        }

        System.out.print(ans);
    }

    private static long getMaxArea() {
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;

        for (int i = 0; i <= N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                long height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
