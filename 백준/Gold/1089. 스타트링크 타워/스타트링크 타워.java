import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    static final String[] NUMBERS = new String[]{
            "###...#.###.###.#.#.###.###.###.###.###",
            "#.#...#...#...#.#.#.#...#.....#.#.#.#.#",
            "#.#...#.###.###.###.###.###...#.###.###",
            "#.#...#.#.....#...#...#.#.#...#.#.#...#",
            "###...#.###.###...#.###.###...#.###.###"
    };
    static int[] numberBitInfos;

    static String[] liftStatus;
    static int[] liftBitInfos;

    static StringBuilder ans = new StringBuilder();


    public static void main(String[] args) throws Exception {
        numberBitInfos = displayConvertBitStatus(NUMBERS, 10);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        liftStatus = new String[5];
        for(int i = 0; i < 5; i++) {
            liftStatus[i] = br.readLine();
        }
        liftBitInfos = displayConvertBitStatus(liftStatus, N);
        long[][] sumAndCnt = new long[N][2];
        long total = 1L;
        for(int i = 0; i < N; i++) {
            int bitInfo = liftBitInfos[i];
            long sum = 0;
            long cnt = 0;
            for(int j = 0; j < 10; j++) {
                if((numberBitInfos[j] & bitInfo) == bitInfo) {
                    sum += j;
                    cnt++;
                }
            }
            if(cnt == 0) {
                System.out.println(-1);
                return;
            }
            sumAndCnt[i][0] = sum;
            sumAndCnt[i][1] = cnt;
            total *= cnt;
        }
        long[] pow10 = new long[N];
        pow10[0] = 1;
        for(int i = 1; i < N; i++) pow10[i] = pow10[i - 1] * 10;

        double totalSum = 0.0;
        for(int i = 0; i < N; i++) {
            long otherDisplayCnt = total / sumAndCnt[i][1];
            long pow10P = pow10[N - i - 1];
            totalSum += (double) sumAndCnt[i][0] * otherDisplayCnt * pow10P;
        }

        System.out.println(totalSum / (double) total);
    }

    static int[] displayConvertBitStatus(String[] display, int len) {
        int[] bitStatus = new int[len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 3; k++) {
                    if(display[j].charAt(i * 4 + k) == '#') {
                        bitStatus[i] |= (1 << (k + (j * 3)));
                    }
                }
            }
        }
        return bitStatus;
    }






}

