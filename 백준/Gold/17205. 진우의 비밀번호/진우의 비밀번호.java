import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long[] prefixN;
    static String target;
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        target = br.readLine();
        prefixN = new long[N + 1];
        prefixN[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            prefixN[i] = 1 + 26 * prefixN[i + 1];
        }
        ans = 0;
        for(int i = 0; i < target.length(); i++) {
            char targetChar = target.charAt(i);
            ans++;
            for(char c = 'a'; c < targetChar; c++) {
                ans += prefixN[i + 1];
            }
        }

        System.out.println(ans);
    }




}


