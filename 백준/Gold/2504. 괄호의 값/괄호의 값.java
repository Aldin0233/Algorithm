import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Stack<Character> stack = new Stack<>();
        int ans = 0;
        int tempValue = 1;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                stack.push(c);
                tempValue *= 2;
            } else if (c == '[') {
                stack.push(c);
                tempValue *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    ans = 0;
                    break;
                }
                if (str.charAt(i - 1) == '(') ans += tempValue;
                stack.pop();
                tempValue /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    ans = 0;
                    break;
                }
                if (str.charAt(i - 1) == '[') ans += tempValue;
                stack.pop();
                tempValue /= 3;
            } else {
                ans = 0;
                break;
            }
        }

        System.out.println(stack.isEmpty() ? ans : 0);
    }
}
