import java.io.*;
import java.util.*;

public class Main {

    static int S, T, tSqrt;
    static final Set<Integer> visited = new HashSet<>();
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        if(S == T) {
            System.out.println(0);
            return;
        }
        tSqrt = (int)Math.sqrt(T); // * 연산에 계속 사용될 맥스값
        Queue<State> q = settingPrimaryQueue();
        while(!q.isEmpty()) {
            State curState = q.poll();
            int curNum = curState.cur;
            String curStateStr = curState.ans;
            if(curNum == T) {
                System.out.println(curStateStr);
                return;
            }
            //연산 순서대로 방문, 작아질 수가 없는 구조 T가 맥스값
            if(curNum <= tSqrt && visited.add(curNum * curNum)) {
                q.add(new State(curNum * curNum, curStateStr + "*"));
            }
            if(curNum + curNum <= T && visited.add(curNum + curNum)) {
                q.add(new State(curNum + curNum, curStateStr + "+"));
            }
        }
        System.out.print(-1);
    }

    private static Queue<State> settingPrimaryQueue() {
        Queue<State> q = new LinkedList<>();
        // -와 /는 처음 한번만 방문하면 됨 미리 해서 이후 IF 연산 절감
        if(S <= tSqrt) {
            visited.add(S * S);
            q.add(new State(S * S, "*"));
        }
        if(S + S <= T) {
            visited.add(S + S);
            q.add(new State(S + S, "+"));
        }
        visited.add(0);
        //S 는 1 이상
        visited.add(1);
        q.add(new State(0, "-"));
        q.add(new State(1, "/"));
        return q;
    }

}

class State {
    int cur;
    String ans;
    State(int cur, String ans) {
        this.cur = cur;
        this.ans = ans;
    }

}
