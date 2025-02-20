import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static Map<String, Integer> methodIdx;
    public static Map<Integer, String> methodName;
    public static boolean[][] calledMap;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        methodIdx = new HashMap<>();
        methodName = new HashMap<>();
        calledMap = new boolean[N][N];
        visited = new boolean[N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String method = st.nextToken();
            int methodCaller = Integer.parseInt(st.nextToken());
            if(!methodIdx.containsKey(method)) putMap(method, idx++);
            int nowIdx = methodIdx.get(method);
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < methodCaller; j++) {
                String caller = st.nextToken();
                if(!methodIdx.containsKey(caller)) putMap(caller, idx++);
                int nowCaller = methodIdx.get(caller);
                calledMap[nowIdx][nowCaller] = true;
            }
        }
        for(String method : methodIdx.keySet()) {
            int nowidx = methodIdx.get(method);
            if(visited[nowidx]) continue;
            if(itsProgram(method)) dfs(method);
        }
        int unUsedCnt = 0;
        for(int i = 0 ; i < N; i++) {
            if(!visited[i]) unUsedCnt++;
        }
        System.out.println(unUsedCnt);
    }

    private static void putMap(String method, int idx) {
        methodIdx.put(method, idx);
        methodName.put(idx, method);
    }

    private static void dfs(String method) {
        int idx = methodIdx.get(method);
        if(visited[idx]) return;
        visited[idx] = true;
        for(int i = 0; i < N; i++) {
            if(calledMap[i][idx]) {
                dfs(methodName.get(i));
            }
        }
    }

    private static boolean itsProgram(String method) {
        StringTokenizer st = new StringTokenizer(method, "::");
        st.nextToken();
        return "PROGRAM".equals(st.nextToken());
    }

}





