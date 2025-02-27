import java.io.*;
import java.util.*;

public class Main {

    private static int MAX = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int N = Integer.parseInt(br.readLine());
        Word[] words = new Word[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = new Word(word, wordToChar(word));
        }
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                String temp = s.substring(j, i);
                int[] charArr = wordToChar(temp);
                for(int k = 0 ; k < N; k++) {
                    if(words[k].canEqual(charArr, temp.length())) {
                        dp[i] = Math.min(dp[i], dp[j] + words[k].different(temp));
                    }
                }
            }
        }
        int ans = dp[s.length()] == MAX ? -1 : dp[s.length()];
        System.out.println(ans);
    }

    private static int[] wordToChar(String word) {
        int[] arr = new int[26];
        for(int i = 0; i < word.length(); i++) {
            arr[word.charAt(i) - 'a']++;
        }
        return arr;
    }

}

class Word {
    int length;
    int[] charArr = new int[26];
    String word;
    public Word(String word, int[] charArr) {
        this.word = word;
        this.length = word.length();
        this.charArr = charArr;
    }

    public int different(String otherWord) {
        int cnt = 0;
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) != otherWord.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    public boolean canEqual(int[] arr, int otherLength) {
        return this.length == otherLength && Arrays.equals(charArr, arr);
    }
}
