import java.io.*;
import java.util.*;

class Main {

    static int N, K;
    static int[][] dp;
    static final int div = 10007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken();
        String s2 = st.nextToken();
        int max = Math.max(s1.length(), s2.length());
        int[] arr = new int[max + 1];
        int idx = 0;
        int min = Math.min(s1.length(), s2.length());
        for(int i = 1; i <= min; i++) {
            arr[i] = s1.charAt(s1.length() - i) + s2.charAt(s2.length() - i) - ('0' * 2);
            if(arr[i - 1] >= 10) {
                arr[i - 1] -= 10;
                arr[i] += 1;
            }
        }
        String str;
        if(s1.length() > s2.length()) {
            str = s1;
        } else {
            str = s2;
        }
        for(int i = min + 1; i <= max; i++) {
            arr[i] = str.charAt(max - i) - '0';
            if(arr[i - 1] >= 10) {
                arr[i - 1] -= 10;
                arr[i] += 1;
            }
        }
        StringBuilder ans = new StringBuilder();
        for(int i = max; i >= 1; i--) {
            ans.append(arr[i]);
        }
        System.out.println(ans);
    }
}