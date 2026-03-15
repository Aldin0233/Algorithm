import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //2453
    static int N;
    static String[] studentNo;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        char operator = st.nextToken().charAt(0);
        int b = Integer.parseInt(st.nextToken());
        st.nextToken();
        int c = Integer.parseInt(st.nextToken());
        if(c == check(a, b, operator)) System.out.println("YES");
        else System.out.println("NO");
    }

    static int check(int a, int b, char operator) {
        if(operator == '+') {
            return a + b;
        } else if(operator == '-') {
            return a - b;
        } else if(operator == '*') {
            return a * b;
        } else if(operator == '/') {
            return a / b;
        }
        return 0;
    }



}




