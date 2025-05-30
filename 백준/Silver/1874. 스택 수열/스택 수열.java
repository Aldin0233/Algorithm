import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int K, N;
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int nowNum = 1;
        for(int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(stack.isEmpty() || num >= stack.peek()) {
                while(nowNum <= num) {
                    stack.push(nowNum++);
                    ans.append("+\n");
                }
                stack.pop();
                ans.append("-\n");
            } else {
                ans = new StringBuilder();
                ans.append("NO");
                break;
            }
        }


        System.out.println(ans);
    }







}
