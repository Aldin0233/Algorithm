import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static String S;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        S = sc.nextLine();
        long answer = extract();
        System.out.println(answer);
    }

    private static long extract() {
        Stack<Long> stack = new Stack<>();
        long count = 0;
        for(int i = S.length()-1 ; i >= 0 ; i--) {
            if(S.charAt(i) == ')') {
                stack.push(count);
                count = 0;
            } else if(S.charAt(i) == '(') {
                count *= S.charAt(i-1) - '0';
                count += stack.pop();
                i--;
            } else {
                count++;
            }
        }
        return count;
    }
}
