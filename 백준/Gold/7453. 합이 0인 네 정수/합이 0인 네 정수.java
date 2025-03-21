import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[][] longs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        longs = new long[N][4];
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 4; j++) {
                longs[i][j] = Long.parseLong(st.nextToken());
            }
        }
        int nSquare = N * N;
        long[] ABSum = new long[nSquare];
        long[] CDSum = new long[nSquare];
        int idx = 0;
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                ABSum[idx] = longs[i][0] + longs[j][1];
                CDSum[idx++] = longs[i][2] + longs[j][3];
            }
        }
        Arrays.sort(ABSum);
        Arrays.sort(CDSum);
        long ans = getAns(ABSum, CDSum, nSquare);

        System.out.println(ans);
    }

    private static long getAns(long[] ABSum, long[] CDSum, int nSquare) {
        long ans = 0;
        int left = 0, right = nSquare - 1;
        while(left < nSquare && right >= 0) {
            if(ABSum[left] + CDSum[right] < 0) {
                left++;
            } else if(ABSum[left] + CDSum[right] > 0){
                right--;
            } else {
                int ABCnt = 1, CDCnt = 1;
                while(left < nSquare - 1 && ABSum[left] == ABSum[left + 1]) {
                    ABCnt++;
                    left++;
                }
                while(right > 0 && CDSum[right] == CDSum[right - 1]) {
                    CDCnt++;
                    right--;
                }
                ans += (long) ABCnt * CDCnt;
                left++;
                right--;
            }
        }
        return ans;
    }

}

