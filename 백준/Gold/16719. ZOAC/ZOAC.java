import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String str;
    static boolean[] used;
    static StringBuilder ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        used = new boolean[str.length()];
        ans = new StringBuilder();
        searchMin(0, str.length() - 1);
        System.out.println(ans);
    }

    private static void searchMin(int l, int r) {
        if(l > r) return;
        int minIdx = l;
        for(int i = l; i <= r; i++) {
            if(str.charAt(i) < str.charAt(minIdx)) {
                minIdx = i;
            }
        }
        used[minIdx] = true;
        StringBuilder cur = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(used[i]) cur.append(str.charAt(i));
        }
        ans.append(cur).append("\n");
        searchMin(minIdx + 1, r);
        searchMin(l, minIdx - 1);
    }


}
