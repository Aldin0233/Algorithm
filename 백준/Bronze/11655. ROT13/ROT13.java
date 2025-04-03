import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] A, B;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c >= 'a' && c <= 'z') {
                int cToInsert = (c - 'a' + 13) % 26;
                ans.append((char)(cToInsert + 'a'));
            } else if(c >= 'A' && c <= 'Z') {
                int cToInsert = (c - 'A' + 13) % 26;
                ans.append((char)(cToInsert + 'A'));
            } else {
                ans.append(c);
            }
        }

        System.out.println(ans);
    }

}