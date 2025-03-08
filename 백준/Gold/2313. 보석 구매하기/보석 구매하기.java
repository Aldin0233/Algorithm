import java.io.*;
import java.util.*;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Result[] results = new Result[N];

        int totalSum = 0;
        for (int row = 0; row < N; row++) {
            int L = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] gems = new int[L];
            for (int i = 0; i < L; i++)
                gems[i] = Integer.parseInt(st.nextToken());

            results[row] = findMaxSubArray(gems);
            totalSum += results[row].sum;
        }

        System.out.println(totalSum);
        for (Result r : results) {
            System.out.println((r.start + 1) + " " + (r.end + 1));
        }
    }

    private static Result findMaxSubArray(int[] arr) {
        int maxSum = Integer.MIN_VALUE, sum = 0, start = 0, end = 0, s = 0, minLen = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > maxSum || (sum == maxSum && (i - s < minLen || (i - s == minLen && s < start)))) { // 길이가 짧을수록, 사전순으로 앞일 수록
                maxSum = sum;
                start = s;
                end = i;
                minLen = i - s;
            }
            if (sum <= 0) {
                sum = 0;
                s = i + 1;
            }
        }
        return new Result(maxSum, minLen, start, end);
    }
}

class Result {
    int sum, len, start, end;
    Result(int sum, int len, int start, int end) {
        this.sum = sum; this.len = len; this.start = start; this.end = end;
    }
}
