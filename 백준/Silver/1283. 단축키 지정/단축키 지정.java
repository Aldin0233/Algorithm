import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static boolean[] usedKey = new boolean[26];
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            ans.append(checkTheKey(br.readLine())).append("\n");
        }
        System.out.println(ans);
    }

    private static String checkTheKey(String input) {
        String[] strs = input.split(" ");
        for(int i = 0; i < strs.length; i++) {
            char c = strs[i].charAt(0);
            int cur = charToInt(c);
            if(usedKey[cur]) continue;
            usedKey[cur] = true;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < i; j++) {
                sb.append(strs[j]).append(" ");
            }
            sb.append(changeString(strs[i], 0)).append(" ");
            for(int j = i + 1; j < strs.length; j++) {
                sb.append(strs[j]).append(" ");
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int cur = charToInt(c);
            if(cur == -1 || usedKey[cur]) continue;
            usedKey[cur] = true;
            return changeString(input, i);
        }
        return input;
    }

    private static String changeString(String input, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(input.substring(0, i));
        sb.append(String.format("[%c]", input.charAt(i)));
        sb.append(input.substring(i + 1));
        return sb.toString();
    }

    private static int charToInt(char c) {
        if(c >= 'A' && c <= 'Z') {
            return c - 'A';
        } else if(c >= 'a' && c <= 'z') {
            return c - 'a';
        }
        return -1;
    }



}

