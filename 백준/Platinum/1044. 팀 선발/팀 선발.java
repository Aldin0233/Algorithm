import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long[] aScoreInfo, bScoreInfo;
    static TreeMap<Long, Long>[] leftHalfCombs, rightHalfCombs;
    static long minDiff = Long.MAX_VALUE;
    static BitFair ansBitMask;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        aScoreInfo = new long[N];
        bScoreInfo = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            aScoreInfo[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bScoreInfo[i] = Long.parseLong(st.nextToken()) * -1;
        }
        leftHalfCombs = generateComb(0, (N + 1) / 2);
        rightHalfCombs = generateComb((N + 1) / 2, N);
        findMinDiff();
        for(int part = 0; part < 2; part++) {
            long bits = part == 0 ? ansBitMask.left : ansBitMask.right;
            int len = part == 0 ? (N + 1) / 2 : N / 2;
            for(int i = len - 1; i >= 0; i--) {
                ans.append((bits & (1L << i)) == 0 ? "1 " : "2 ");
            }
        }
        System.out.print(ans);
    }

    private static TreeMap<Long, Long>[] generateComb(int start, int end) {
        int len = end - start;
        int totalCombCnt = 1 << len;
        TreeMap<Long, Long>[] combs = new TreeMap[len + 1];
        for(int i = 0; i <= len; i++) {
            combs[i] = new TreeMap<>();
        }
        for(long comb = 0; comb < totalCombCnt; comb++) { //두번 나눠서
            long diff = 0;
            int selectedA = 0;

            for(int i = 0 ; i < len; i++) {
                int isOn = (int) ((comb >> (len - i - 1)) & 1);
                if(isOn == 0) { // 1번 팀 선택
                    diff += aScoreInfo[start + i];
                    selectedA++;
                } else {
                    diff += bScoreInfo[start + i];
                }
            }

            combs[selectedA].putIfAbsent(diff, (long) comb); //1번 팀에서 selectedA 만큼 선수를 뽑았을 때 조합과 차이
        }
        return combs;
    }

    private static void findMinDiff() {
        for(int i = 0; i <= (N + 1) / 2; i++) { // i = left 팀에서 뽑힌 인원 수
            int rightSelectedACnt = (N / 2) - i; // 오른 쪽 팀에서 뽑아야할 인원 수
            for(Map.Entry<Long, Long> entry : leftHalfCombs[i].entrySet()) {
                long leftDiff = entry.getKey();
                long leftBits = entry.getValue();

                Map.Entry<Long, Long> ceiling = rightHalfCombs[rightSelectedACnt].ceilingEntry(-leftDiff);
                comparedDiff(leftDiff, leftBits, ceiling);

                Map.Entry<Long, Long> floor = rightHalfCombs[rightSelectedACnt].floorEntry(-leftDiff);
                comparedDiff(leftDiff, leftBits, floor);
            }
        }
    }

    private static void comparedDiff(long leftDiff, long leftBits, Map.Entry<Long, Long> entry) {
        if(entry != null) { 
            long totalDiff = Math.abs(leftDiff + entry.getKey());
            BitFair newFair = new BitFair(leftBits, entry.getValue());
            if(totalDiff < minDiff) {
                minDiff = totalDiff;
                ansBitMask = newFair;
            } else if(totalDiff == minDiff) { // 차이 같을 때만 사전 순 비교
                ansBitMask = ansBitMask.compareFair(newFair);
            }
        }
    }

}

class BitFair implements Comparable<BitFair> {
    long left;
    long right;
    BitFair(long left, long right) {
        this.left = left;
        this.right = right;
    }

    public BitFair compareFair(BitFair other) {
        if(this.compareTo(other) < 0) return this;
        return other;
    }

    @Override
    public int compareTo(BitFair o) { //사전순으로 앞서는 거 출력
        if(this.left == o.left) {
            return Long.compare(this.right, o.right);
        }
        return Long.compare(this.left, o.left);
    }
}


