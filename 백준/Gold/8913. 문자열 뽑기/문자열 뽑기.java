import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            String s = br.readLine();
            ans.append(dfs(s, new HashSet<>()) ? "1\n": "0\n");
        }
        System.out.println(ans);
    }

    private static boolean dfs(String curStr, Set<String> visited) {
        if(curStr.isEmpty()) return true;
        if(visited.contains(curStr)) return false;
        visited.add(curStr);
        int n = curStr.length();
        for(int i = 0; i < n; ) {
            int j = i;
            while(j < n && curStr.charAt(j) == curStr.charAt(i)) j++;
            int len = j - i;
            if(len >= 2) {
                String next = curStr.substring(0, i) + curStr.substring(j);
                if(dfs(next, visited)) return true;
            }
            i = j;
        }
        return false;
    }

}


