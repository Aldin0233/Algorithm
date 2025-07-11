import java.io.*;
import java.util.*;

public class Main {

    static long X, Y;
    static int originalWinRate;

    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());
        originalWinRate = getRate(X, Y);
        ans = binarySearch();
        System.out.println(ans);
    }

    private static long binarySearch() {
        long left = 1, right = X;
        long count = -1;
        while(left <= right) {
            long mid = (left + right) / 2;
            int newRate = getRate(X + mid, Y + mid);
            if(newRate > originalWinRate) {
                count = mid;
                right = mid - 1; // 일단 낮춰봄
            } else {
                left = mid + 1;
            }
        }
        return count;
    }

    private static int getRate(long X, long Y) {
        return (int) (Y * 100 / X);
    }

}


