import java.io.*;

public class Main {

    static String S, T;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        dfs(0, T.length() - 1, false);
        System.out.println(ans);
    }

    private static void dfs(int left, int right, boolean side) {
        if(right - left + 1 == S.length()) {
            check(left, right, side);
            return;
        }

        int nowPoint = side ? left : right;
        int otherSidePoint = side ? right : left;
        if(T.charAt(nowPoint) == 'A') {
            dfs(side ? left + 1 : left, side ? right : right - 1, side);
            if(ans == 1) return;
        }
        if(T.charAt(otherSidePoint) == 'B') {
            dfs(side ? left : left + 1, side ? right - 1 : right, !side);
            if(ans == 1) return;
        }
    }

    private static void check(int left, int right, boolean side) {
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) != T.charAt(side ? right - i : left + i)) return;
        }
        ans = 1;
    }

}




