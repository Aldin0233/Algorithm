import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static String W, S;
    static boolean[] used = new boolean[26];
    static int[] keys = new int[26];
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        W = br.readLine();
        S = br.readLine();
        int keyIdx = 0;
        Arrays.fill(keys, -1);
        for (int i = 0; i < W.length(); i++) {
            int nowChar = W.charAt(i) - 'A';
            if(used[nowChar]) continue;
            used[nowChar] = true;
            keys[keyIdx++] = nowChar;
        }
        int usedIdx = 0;
        for(int i = keyIdx; i < 26; i++) {
            if(keys[i] == -1) {
                while(used[usedIdx]) usedIdx++;
                keys[i] = usedIdx;
                used[usedIdx++] = true;
            }
        }
        for(int i = 0; i < S.length(); i++) {
            ans.append((char) (keys[S.charAt(i) - 'A'] + 'A'));
        }
        System.out.println(ans);
    }

}






