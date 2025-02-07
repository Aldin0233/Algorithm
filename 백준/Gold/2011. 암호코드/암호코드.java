import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();
        if(str.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2 ; i <= n; i++) {
            int idx = i-1;
            int firstChar = str.charAt(idx) - '0';
            int secondChar = ((str.charAt(idx - 1) - '0') * 10) + firstChar;
            if(firstChar == 0 && (secondChar < 10 || secondChar > 20)) {
                //연속된 0은 와선 안됨 //숫자 생성안됨
                System.out.println(0);
                return;
            }
            if(firstChar >= 1 && firstChar <= 9) {
                dp[i] = dp[i-1];
            }
            if(secondChar >= 10 && secondChar <= 26) {
                //두 자리로 해석 가능
                dp[i] += dp[i-2];
            } 
            dp[i] %= 1000000;
        }
        System.out.println(dp[str.length()]);
    }


}
