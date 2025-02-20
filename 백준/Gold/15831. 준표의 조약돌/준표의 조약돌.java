import java.io.*;
import java.util.*;

public class Main {

    public static int N, B, W, ans;
    public static int[][] arr;
    public static String pathInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        pathInfo = br.readLine();
        arr = new int[N][2];
        if(pathInfo.charAt(0) == 'W') arr[0][0]++;
        else arr[0][1]++;
        for(int i = 1 ; i < N; i++) {
            arr[i][0] = arr[i-1][0];
            arr[i][1] = arr[i-1][1];
            if(pathInfo.charAt(i) == 'W') {
                arr[i][0]++;
            } else {
                arr[i][1]++;
            }
        }
        search();
        System.out.println(ans);
    }

    private static void search() {
        ans = 0;
        int l = 0;
        for (int r = 0; r < N; r++) {
            while (l <= r && getB(l, r) > B) {
                l++;
            }
            if (getW(l, r) >= W) {
                ans = Math.max(ans, r - l + 1);
            }
        }
    }

    private static int getW(int l, int r) {
        if (l == 0) return arr[r][0];
        return arr[r][0] - arr[l-1][0];
    }

    private static int getB(int l, int r) {
        if (l == 0) return arr[r][1];
        return arr[r][1] - arr[l-1][1];
    }

}