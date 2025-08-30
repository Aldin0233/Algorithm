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
        targetStr = br.readLine(); //타겟
        targetToIntArr = strToIntArr(targetStr); //타겟을 인트 배열로
        N = targetToIntArr.length; //자릿수
        targetSum = 0; //목적 하는 수
        for(int num : targetToIntArr) targetSum += LINE_INFO[num];
        makeDp(); //N 15일때 가능한 조합 최대 105 (7(최대값) * 15);
        long res = -1L;
        res = upperSearchDp(0, 0, 0, true);
        //위에서 못 찾았으면 아래에서 탐색
        if(res == -1L) res = zeroToUpperSearchDp(0, 0, 0, true);
        long originNum = Long.parseLong(targetStr); //기존 값
        if(originNum < res) { //기존 값보다 위에서 찾음
            ans = res - originNum;
        } else { //기존 값보다 아래서 찾음
            long fullCycle = 1; //한바퀴 다 돌았음
            for(int i = 0; i < N; i++) fullCycle *= 10;
            ans = res + fullCycle - originNum;
        }
        System.out.println(ans);
    }

    //기존 값 인트 배열로
    private static int[] strToIntArr(String str) {
        int[] arr = new int[str.length()];
        for(int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i) - '0';
        }
        return arr;
    }

    //0에서 기존 값까지 탐색하는 메서드
    //현재 탐색하는 위치, 현재 앞부분 합, 탐색중인 조합, 높아지면 안됨 여부
    private static long zeroToUpperSearchDp(int pos, int frontSum, long currentDigits, boolean tight) {
        if(pos == N) return currentDigits;
        int maxDigit = tight ? targetToIntArr[pos] : 9;
        for(int i = 0; i <= maxDigit; i++) {
            int remainSum = targetSum - frontSum - LINE_INFO[i];
            if(remainSum < 0 || remainSum >= dp[pos + 1].length) continue; //범위 초과
            if(dp[pos + 1][remainSum]) {
                boolean newTight = tight && (i == maxDigit);
                long res = zeroToUpperSearchDp(pos + 1, frontSum + LINE_INFO[i], currentDigits * 10 + i, newTight);
                if(res != -1L) return res;
            }
        }
        return -1L;
    }

    //하한 값에서 위로 탐색하는 메서드
    //현재 탐색하는 위치, 현재 앞부분 합, 탐색중인 조합, 낮아지면 안됨 여부
    private static long upperSearchDp(int pos, int frontSum, long currentDigits, boolean tight) {
        if(pos == N) {
            return currentDigits;
        }
        int minDigit = tight ? targetToIntArr[pos] : 0;
        if(tight && pos == N - 1) minDigit++; //기존 값보다 최소 1은 커져야 함 //기존값은 무조건 동일
        for(int i = minDigit; i < 10; i++) {
            int remainSum = targetSum - frontSum - LINE_INFO[i];
            if(remainSum < 0 || remainSum >= dp[pos + 1].length) continue; //범위 초과
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
        for (int i = 0; i <= N; i++) { //해당 자리 수
            int remain = N - i;
            dp[i] = new boolean[7 * remain + 1]; //에서 가능한 조합의 수(최대 합)
            if(i == N - 1) { //끝부분
                dp[i][2] = true;
                dp[i][3] = true;
                dp[i][4] = true;
                dp[i][5] = true;
                dp[i][6] = true;
                dp[i][7] = true;
            } else if(i == N) { //끝 더미 합이 0 // 최종적으로 남은 값이 0이 되어야 함
                dp[i][0] = true;
            }
        }

        for(int i = N - 2; i >= 0; i--) { //밑에서 두번째부터 가능한 조합 탐색
            int remain = N - i;
            for(int j = dp[i].length - 1; j >= 2 * remain; j--) {
                for(int k = 2; k < 8; k++) { //어차피 나오는 수는 2에서 7
                    if(j - k < 0 || j - k >= dp[i + 1].length) continue;
                    if(dp[i + 1][j - k]) { //하나라도 있으면 다음 것으로 넘어가기
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
    }


}


