import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int N;
    static int[] selectOperator;
    static final char[] OP = {' ', '+', '-'};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        selectOperator = new int[10];
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            dfs(0, N);
            ans.append("\n");
        }

        System.out.println(ans);
    }

    static void dfs(int idx, int N) {
        if (idx >= N - 1) {
            check();
            return;
        }
        for(int i = 0; i < 3; i++) {
            selectOperator[idx] = i;
            dfs(idx + 1, N);
        }
    }

    static void check() {
        int result = 0;
        int lastOperator = 1;
        int curNum = 1;
        for(int i = 0; i < N - 1; i++) {
            if(selectOperator[i] != 0) {
                result += lastOperator == 1 ? curNum : -curNum;
                curNum = i + 2;
                lastOperator = selectOperator[i];
            } else {
                curNum *= 10;
                curNum += i + 2;
            }
        }
        result += lastOperator == 1 ? curNum: -curNum;
        if(result == 0) {
            StringBuilder rs = new StringBuilder();
            for(int i = 0; i < N - 1; i++) {
                rs.append(i + 1);
                rs.append(OP[selectOperator[i]]);
            }
            rs.append(N);
            ans.append(rs).append("\n");
        }
    }

}



