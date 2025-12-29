import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static boolean[] visited;
    static long ans;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        Queue<State> q = new LinkedList<>();
        q.add(new State(N, 0, N + ""));
        visited[N] = true;
        while (!q.isEmpty()) {
            State curState = q.poll();
            if(curState.cur == 1) {
                System.out.println(curState.tryCnt);
                System.out.println(curState.road);
                return;
            }
            if(curState.cur % 3 == 0) {
                int newNum = curState.cur / 3;
                if(!visited[newNum]) {
                    visited[newNum] = true;
                    q.add(new State(newNum, curState.tryCnt + 1, curState.road + " " + newNum));
                }
            }
            if(curState.cur % 2 == 0) {
                int newNum = curState.cur / 2;
                if(!visited[newNum]) {
                    visited[newNum] = true;
                    q.add(new State(newNum, curState.tryCnt + 1, curState.road + " " + newNum));
                }
            }
            int newNum = curState.cur - 1;
            if(!visited[newNum]) {
                visited[newNum] = true;
                q.add(new State(newNum, curState.tryCnt + 1, curState.road + " " + newNum));
            }
        }

        System.out.println(-1);
    }

    static class State {
        int cur;
        int tryCnt;
        String road;
        State(int cur, int tryCnt, String road) {
            this.cur = cur;
            this.tryCnt = tryCnt;
            this.road = road;
        }
    }
}



