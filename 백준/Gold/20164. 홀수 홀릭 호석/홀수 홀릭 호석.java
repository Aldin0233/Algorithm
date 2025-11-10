import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] dp;
    static int minAns = Integer.MAX_VALUE, maxAns = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dfs(N, 0);
        System.out.printf("%d %d", minAns, maxAns);
    }

    private static void dfs(int n, int prevOddCnt) {
        if(n < 10) {
            if(n % 2 != 0) prevOddCnt++;

            minAns = Math.min(minAns, prevOddCnt);
            maxAns = Math.max(maxAns, prevOddCnt);
        } else if(n < 100){
            if((n / 10) % 2 != 0) prevOddCnt++;
            if(n % 10 % 2 != 0) prevOddCnt++;
            dfs((n / 10) + (n % 10), prevOddCnt);
        } else {
            int nowCnt = countOddNumbers(n);
            Set<Integer> set = searchCanComb(n);
            for(Integer i : set) {
                dfs(i, nowCnt + prevOddCnt);
            }
        }


    }

    private static Set<Integer> searchCanComb(int n) {
        Set<Integer> set = new HashSet<>();
        String strN = String.valueOf(n);
        for(int i = 1; i < strN.length() - 1; i++) {
            int a = Integer.parseInt(strN.substring(0, i));
            for(int j = i + 1; j < strN.length(); j++) {
                int b = Integer.parseInt(strN.substring(i, j));
                int c = Integer.parseInt(strN.substring(j));
                set.add(a + b + c);
            }
        }
        return set;
    }

    private static int countOddNumbers(int n) {
        int cnt = 0;
        while(n > 0) {
            if(n % 2 != 0) cnt++;
            n /= 10;
        }
        return cnt;
    }



}
