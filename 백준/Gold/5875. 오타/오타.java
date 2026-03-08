import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String s;
    static int sLen;
    static int[] prefixSum, prefixMin, suffixMin;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        sLen = s.length();
        prefixSum = new int[sLen + 1];
        for(int i = 1; i <= s.length(); i++) {
            prefixSum[i] = prefixSum[i - 1] + getBracketValue(s.charAt(i - 1));
        }
        if(prefixSum[sLen] == 0) {
            System.out.println(0);
            return;
        }
        buildPrefixAndSuffixMin();
        int count = 0;
        if(prefixSum[sLen] == 2) {
            for(int i = 1; i <= sLen; i++) {
                if(s.charAt(i - 1) != '(') continue;
                if(prefixMin[i - 1] >= 0 && suffixMin[i] >= 2) {
                    count++;
                }
            }
        } else {
            for(int i = 1; i <= sLen; i++) {
                if(s.charAt(i - 1) != ')') continue;
                if(prefixMin[i - 1] >= 0 && suffixMin[i] >= -2) {
                    count++;
                }
            }

        }

        System.out.println(count);
    }

    static int getBracketValue(char c) {
        return c == '(' ? 1 : -1;
    }

    static void buildPrefixAndSuffixMin() {
        prefixMin = new int[sLen + 1];
        suffixMin = new int[sLen + 1];
        prefixMin[0] = 0;
        suffixMin[sLen] = prefixSum[sLen];
        for(int i = 1; i <= sLen; i++) {
            prefixMin[i] = Math.min(prefixMin[i - 1], prefixSum[i]);
        }
        for(int i = sLen - 1; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], prefixSum[i]);
        }
    }






}

