import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String H, N;
    static int[] pattern;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        H = br.readLine();
        N = br.readLine();

        buildPattern();

        int count = 0;
        int j = 0;
        for(int i = 0; i < H.length(); i++) {
            char c = H.charAt(i);

            while(j > 0 && c != N.charAt(j)) {
                j = pattern[j - 1];
            }
            if(c == N.charAt(j)) {
                j++;
                if(j == N.length()) {
                    count++;
                    j = pattern[j - 1];
                }
            }
        }

        System.out.println(count);
    }

    static void buildPattern() {
        int targetLen = N.length();
        pattern = new int[targetLen];
        int j = 0;
        for(int i = 1; i < targetLen; i++) {
            while(j > 0 && N.charAt(i) != N.charAt(j)) {
                j = pattern[j - 1];
            }
            if(N.charAt(i) == N.charAt(j)) {
                pattern[i] = ++j;
            }
        }
    }


}
