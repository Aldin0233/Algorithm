import java.io.*;

public class Main {

    static String targetStr;
    static int[] targetToIntArr;
    static int[] nowFrontNum;
    static int N, targetSum, frontSum;
    static boolean[][] dp;
    static final int[] LINE_INFO = {6, 2, 5, 5, 4, 5, 6, 3, 7, 5};
    static long ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        targetStr = br.readLine();
        targetToIntArr = strToIntArr(targetStr);
        N = targetToIntArr.length;
        targetSum = 0;
        for(int num : targetToIntArr) targetSum += LINE_INFO[num];
        makeDp();
        long res = -1L;
        res = upperSearchDp(0, 0, 0, true);
        if(res == -1L) res = zeroToUpperSearchDp(0, 0, 0, true);
        long originNum = Long.parseLong(targetStr);
        if(originNum < res) {
            ans = res - originNum;
        } else {
            long fullCycle = 1;
            for(int i = 0; i < N; i++) fullCycle *= 10;
            ans = res + fullCycle - originNum;
        }
        System.out.println(ans);
    }

    private static int[] strToIntArr(String str) {
        int[] arr = new int[str.length()];
        for(int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i) - '0';
        }
        return arr;
    }

    private static long zeroToUpperSearchDp(int pos, int frontSum, long currentDigits, boolean tight) {
        if(pos == N) return currentDigits;
        int maxDigit = tight ? targetToIntArr[pos] : 9;
        for(int i = 0; i <= maxDigit; i++) {
            int remainSum = targetSum - frontSum - LINE_INFO[i];
            if(remainSum < 0 || remainSum >= dp[pos + 1].length) continue;
            if(dp[pos + 1][remainSum]) {
                boolean newTight = tight && (i == maxDigit);
                long res = zeroToUpperSearchDp(pos + 1, frontSum + LINE_INFO[i], currentDigits * 10 + i, newTight);
                if(res != -1L) return res;
            }
        }
        return -1L;
    }

    private static long upperSearchDp(int pos, int frontSum, long currentDigits, boolean tight) {
        if(pos == N) {
            return currentDigits;
        }
        int minDigit = tight ? targetToIntArr[pos] : 0;
        if(tight && pos == N - 1) minDigit++;
        for(int i = minDigit; i < 10; i++) {
            int remainSum = targetSum - frontSum - LINE_INFO[i];
            if(remainSum < 0 || remainSum >= dp[pos + 1].length) continue;
            if(dp[pos + 1][remainSum]) {
                boolean newTight = tight && (i == minDigit);
                long res = upperSearchDp(pos + 1, frontSum + LINE_INFO[i], currentDigits * 10 + i, newTight);
                if(res != -1L) return res;
            }
        }
        return -1L;
    }

    private static void makeDp() {
        dp = new boolean[N + 1][];
        for (int i = 0; i <= N; i++) {
            int remain = N - i;
            dp[i] = new boolean[7 * remain + 1];
            if(i == N - 1) {
                dp[i][2] = true;
                dp[i][3] = true;
                dp[i][4] = true;
                dp[i][5] = true;
                dp[i][6] = true;
                dp[i][7] = true;
            } else if(i == N) {
                dp[i][0] = true;
            }
        }

        for(int i = N - 2; i >= 0; i--) { //10의 자리부터
            int remain = N - i;
            for(int j = dp[i].length - 1; j >= 2 * remain; j--) {
                if(dp[i][j]) continue;
                for(int k = 2; k < 8; k++) {
                    if(j - k < 0 || j - k >= dp[i + 1].length) continue;
                    if(dp[i + 1][j - k]) dp[i][j] = true;
                }
            }
        }
    }


}


