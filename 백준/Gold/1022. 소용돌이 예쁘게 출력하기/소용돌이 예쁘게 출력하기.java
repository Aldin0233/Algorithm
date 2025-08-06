import java.io.*;
import java.util.*;

public class Main {

    static int r1, c1, r2, c2, maxLen;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        int maxNum = 0;
        for(int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                maxNum = Math.max(maxNum, changePosToNum(i, j));
            }
        }
        maxLen = String.valueOf(maxNum).length();
        for(int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                int num = changePosToNum(i, j);
                ans.append(formatWithPadding(num));
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }

    private static String formatWithPadding(int num) {
        int len = String.valueOf(num).length();
        int padding = maxLen - len;
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(padding)).append(num).append(" ");
        return sb.toString();
    }

    private static int changePosToNum(int r, int c) {
        int L = Math.max(Math.abs(r), Math.abs(c));
        if(L == 0) return 1; //시작 지점
        int maxNum = (int) Math.pow(2 * L + 1, 2);
        int L2 = 2 * L;
        if(r == L) {
            return maxNum - (L - c);
        } else if(c == L) {
            return maxNum - (3 * L2)  - (L + r);
        } else if(r == -L) {
            return maxNum - (2 * L2) - (L + c);
        } else {
            return maxNum - L2 - (L - r);
        }
    }



}



