import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String l, r;
    static int[] lArr, rArr;
    static int[] sumResult;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = st.nextToken();
        r = st.nextToken();
        sumResult = new int[Math.max(l.length(), r.length())];
        int len = sumResult.length;
        for (int i = 0; i < sumResult.length; i++) {
            int nowIdx = len - i - 1;
            sumResult[nowIdx] = 0;
            int nowLIdx = l.length() - i - 1;
            if(nowLIdx >= 0) {
                sumResult[nowIdx] += l.charAt(nowLIdx) - '0';
            }
            int nowRIdx = r.length() - i - 1;
            if(nowRIdx >= 0) {
                sumResult[nowIdx] += r.charAt(nowRIdx) - '0';
            }
        }
        for(int i = len - 1; i > 0; i--) {
            if(sumResult[i] > 1) {
                sumResult[i] -= 2;
                sumResult[i - 1]++;
            }
        }
        boolean firstZero = true;
        if(sumResult[0] > 0) firstZero = false;
        if(sumResult[0] > 1) {
            ans.append(1);
            sumResult[0] -= 2;
        }
        for(int i = 0; i < len; i++) {
            if(sumResult[i] > 0) {
                firstZero = false;
                ans.append(1);
            } else if(!firstZero) {
                ans.append(0);
            }
        }
        if(ans.length() == 0) ans.append(0);

        System.out.println(ans);
    }




}

