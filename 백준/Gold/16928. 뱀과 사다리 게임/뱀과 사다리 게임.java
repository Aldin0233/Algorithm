import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static Map<Integer, Integer> move = new HashMap<>();
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            move.put(a, b);
        }
        boolean[] visited = new boolean[101];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        ans = 1;
        while(true) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int now = q.poll();
                for(int j = 1; j <= 6; j++) {
                    int next = now + j;
                    if(next > 100 || visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    if(move.containsKey(next)) {
                        int movedNext = move.get(next);
                        visited[movedNext] = true;
                        q.add(movedNext);
                    } else {
                        q.add(next);
                    }
                }
            }
            if(visited[100]) break; //이번 주사위 던지기에서 발견했음
            ans++; //다음 주사위 던지기 굴려봄
        }
        System.out.println(ans);
    }


}


