import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static boolean[] isUsedNum;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isUsedNum = new boolean[33554432 + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            if(!isUsedNum[n]) {
                isUsedNum[n] = true;
                ans.append(n).append(" ");
            }
        }
        System.out.println(ans);
    }



}

