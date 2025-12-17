import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String A, B;
    static int MAX = 1_000_000_000;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();
        int[] cnt = new int[26];
        for(int i = 0; i < A.length(); i++) {
            cnt[A.charAt(i) - 'A']++;
            cnt[B.charAt(i) - 'A']--;
        }
        for(int x: cnt) {
            if(x != 0) {
                System.out.println(-1);
                return;
            }
        }

        int i = A.length() - 1;
        int j = B.length() - 1;
        while(i >= 0) {
            if(j >= 0 && A.charAt(i) == B.charAt(j)) {
                i--;
                j--;
            } else {
                i--;
                ans++;
            }
        }
        System.out.println(ans);
    }

}



