import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num1 = st.nextToken();
            String num2 = st.nextToken();
            int num1ZeroDiff = 0, num1OneDiff = 0;
            for(int i = 0; i < num1.length(); i++) {
                if(num1.charAt(i) != num2.charAt(i)) {
                    if(num1.charAt(i) == '0') num1ZeroDiff++;
                    else num1OneDiff++;
                }
            }
            ans.append(Math.max(num1ZeroDiff, num1OneDiff)).append("\n");
        }
        System.out.println(ans);
    }
}
