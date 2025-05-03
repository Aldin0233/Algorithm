
import java.io.*;
import java.util.Stack;

public class Main {

    static String str;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        strToPostFix();
        System.out.println(ans);
    }

    private static void strToPostFix() {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '(') { //그냥 넣음
                stack.push(c);
            } else if(c == ')') { //괄호 내부 연산 처리
                popUntilOpening(stack);
                stack.pop();
            } else if(isPriorityOperationSymbol(c)) {
                while(!stack.isEmpty() && isPriorityOperationSymbol(stack.peek())) {
                    ans.append(stack.pop());
                }
                stack.push(c);
            } else if(isGenericOperationSymbol(c)) {
                popUntilOpening(stack);
                stack.push(c);
            } else {
                ans.append(c);
            }
        }
        while(!stack.isEmpty()) {
            ans.append(stack.pop());
        }
    }

    //여는 괄호 만날때까지 팝
    private static void popUntilOpening(Stack<Character> stack) {
        while(!stack.isEmpty() && stack.peek() != '(') {
            ans.append(stack.pop());
        }
    }

    // * or /
    private static boolean isPriorityOperationSymbol(char c) {
        return c == '*' || c == '/';
    }

    // + or -
    private static boolean isGenericOperationSymbol(char c) {
        return c == '+' || c == '-';
    }



}
