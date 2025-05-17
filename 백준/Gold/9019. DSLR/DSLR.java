import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static int a, b;
    static Queue<State> q;
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            q = new LinkedList<>();
            q.add(new State(a, ""));
            boolean[] visited = new boolean[10000];
            visited[a] = true;
            while(!q.isEmpty()) {
                State cur = q.poll();
                if(cur.now == b) {
                    ans.append(cur.order).append("\n");
                    break;
                }
                int D = D(cur.now);
                if(!visited[D]) {
                    visited[D] = true;
                    q.offer(new State(D, cur.order + "D"));
                }
                int S = S(cur.now);
                if(!visited[S]) {
                    visited[S] = true;
                    q.offer(new State(S, cur.order + "S"));
                }
                int L = L(cur.now);
                if(!visited[L]) {
                    visited[L] = true;
                    q.offer(new State(L, cur.order + "L"));
                }
                int R = R(cur.now);
                if(!visited[R]) {
                    visited[R] = true;
                    q.offer(new State(R, cur.order + "R"));
                }
            }

        }
        System.out.println(ans);
    }

    private static int D(int now) {
        return (now << 1) % 10000;
    }

    private static int S(int now) {
        return (now + 9999) % 10000;
    }

    private static int L(int now) {
        return ((now * 10) + (now / 1000)) % 10000;
    }

    private static int R(int now) {
        return ((now / 10) + ((now % 10) * 1000));
    }




}

class State {
    int now;
    String order;

    public State(int now, String order) {
        this.now = now;
        this.order = order;
    }

}
