import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<int[]> intervalList;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        intervalList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            intervalList.add(new int[]{l, r});
        }
        merge();
        int lastMove = intervalList.get(0)[1];
        long canJumpMost = lastMove * 2L;
        for(int i = 1; i < intervalList.size(); i++) {
            if(canJumpMost >= intervalList.get(i)[0]) {
                lastMove = intervalList.get(i)[1];
                canJumpMost = Math.max(canJumpMost, lastMove + (intervalList.get(i)[1] - intervalList.get(i)[0]));
            }
        }
        System.out.println(lastMove);
    }

    static void merge() {
        intervalList.sort((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        List<int[]> mergedIntervals = new ArrayList<>();
        int curIntervalL = intervalList.get(0)[0];
        int curIntervalR = intervalList.get(0)[1];
        for (int i = 1; i < intervalList.size(); i++) {
            int[] nowInterval = intervalList.get(i);
            if(nowInterval[0] <= curIntervalR) {
                curIntervalR = Math.max(curIntervalR, nowInterval[1]);
            } else {
                mergedIntervals.add(new int[]{curIntervalL, curIntervalR});
                curIntervalL = nowInterval[0];
                curIntervalR = nowInterval[1];
            }
        }
        mergedIntervals.add(new int[]{curIntervalL, curIntervalR});
        intervalList = mergedIntervals;
    }

}




