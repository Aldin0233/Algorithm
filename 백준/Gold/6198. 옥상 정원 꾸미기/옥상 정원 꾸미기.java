
import java.io.*;
import java.util.Stack;

public class Main {

    static int N;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            if(!stack.isEmpty()) {
                if(stack.peek() <= a) {
                    while (!stack.isEmpty() && stack.peek() <= a) {
                        stack.pop();
                    }
                }
                if(!stack.isEmpty()) {
                    ans += stack.size();
                }
            }
            stack.push(a);

        }
        System.out.println(ans);
    }



}
