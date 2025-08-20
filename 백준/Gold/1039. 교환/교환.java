
import java.io.*;
import java.util.*;

public class Main {

    static int[] N;
    static int K;
    static Set<Integer>[] visited;

    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = strToIntArr(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new HashSet[K];
        for (int i = 0; i < K; i++) {
            visited[i] = new HashSet<>();
        }
        dfs(0);
        System.out.println(ans == Integer.MIN_VALUE ? -1 : ans);
    }

    private static void dfs(int k) {
        if(k == K) {
            ans = Math.max(ans, arrToInt());
            return;
        }
        for(int i = 0; i < N.length - 1; i++) {
            for(int j = i + 1; j < N.length; j++) {
                if(i == 0 && N[j] == 0) continue; //바꾼 수 앞이 0이 되지 않게
                int tmp = N[i];
                N[i] = N[j];
                N[j] = tmp;
                int nowNum = arrToInt();
                if(visited[k].add(nowNum)) {
                    dfs(k + 1);
                }
                N[j] = N[i];
                N[i] = tmp;
            }
        }
    }

    private static int arrToInt() {
        int num = 0;
        for (int i : N) {
            num *= 10;
            num += i;
        }
        return num;
    }

    private static int[] strToIntArr(String input) {
        int[] arr = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            arr[i] = input.charAt(i) - '0';
        }
        return arr;
    }




}