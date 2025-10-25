import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] alphabet = new int[26];
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            alphabet[s.charAt(0) - 'a']++;
        }

        for(int i = 0; i < 26; i++) {
            if(alphabet[i] >= 5) {
                ans.append((char) (i + 'a'));
            }
        }
        if(ans.length() == 0) ans.append("PREDAJA");
        System.out.println(ans);
    }




}