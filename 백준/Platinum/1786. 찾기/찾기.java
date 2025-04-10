import java.io.*;
import java.util.*;

public class Main {

    static String T, P;
    static int ansCnt = 0;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = br.readLine();
        P = br.readLine();
        kmpSearch();
        System.out.println(ansCnt);
        System.out.print(ans);
    }

    private static void kmpSearch() {
        int[] lps = getLps();
        int textIdx = 0, patternIdx = 0;
        while(textIdx < T.length()) {
            if(T.charAt(textIdx) == P.charAt(patternIdx)) {
                textIdx++;
                patternIdx++;
                if(patternIdx == P.length()) {
                    ansCnt++;
                    ans.append(textIdx - patternIdx + 1).append('\n');
                    patternIdx = lps[patternIdx - 1];
                }
            } else {
                if(patternIdx != 0) patternIdx = lps[patternIdx - 1];
                else textIdx++;
            }
        }
    }

    private static int[] getLps() {
        int patternLen = P.length();
        int[] lps = new int[patternLen];
        int len = 0;
        int i = 1;
        while (i < patternLen) {
//            System.out.printf("%d %d, %c %c%n", i, len, P.charAt(i), P.charAt(len));
            if(P.charAt(i) == P.charAt(len)) {
                lps[i++] = ++len;
            } else {
                if(len != 0) len = lps[len - 1];
                else lps[i++] = 0;
            }
        }
//        System.out.println(Arrays.toString(lps));
        return lps;
    }




}
