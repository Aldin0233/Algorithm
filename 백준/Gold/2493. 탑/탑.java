import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<int[]> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty()) {
                if (stack.peek()[0] >= height) {
                    sb.append(stack.peek()[1]).append(" ");
                    break;
                }
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append("0 ");
            }

            stack.push(new int[]{height, i});
        }

        System.out.println(sb.toString().trim());
    }
}
