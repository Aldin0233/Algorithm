import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static boolean[] visited;
    static int ansCount = 0;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            if(!visited[i]){
                boolean[] tmpVisited = new boolean[N + 1];
                dfs(i, i, tmpVisited);
            }
        }
        for(int i = 1; i <= N; i++){
            if(visited[i]) {
                ansCount++;
                ans.append(i).append("\n");
            }
        }
        System.out.println(ansCount);
        System.out.println(ans);
    }

    private static boolean dfs(int i, int start, boolean[] tmp) {
        if(tmp[i]) return false;
        tmp[i] = true;
        if(visited[i]) return false;
        if(arr[i] == start) return visited[i] = true;
        else return visited[i] = dfs(arr[i], start, tmp);
    }

}
