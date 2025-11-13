import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int sLen;
    static Queue<State> q;
    static Set<State> visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> lists = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            String str = br.readLine();
            for(int j = 0; j < 5; j++) {
                if(str.charAt(j) == '*') {
                    lists.add(i * 5 + j);
                }
            }
        }
        q = new LinkedList<>();
        visited = new HashSet<>();
        q.add(new State(lists.stream().mapToInt(i -> i).toArray()));
        visited.add(q.peek());
        ans = 0;
        sLen = q.peek().state.length;
        search: while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                State state = q.poll();
                if(isConnected(state)) break search;
                for(int i = 0; i < sLen; i++) {
                    if(state.state[i] >= 5) {
                        int[] next = Arrays.copyOf(state.state, sLen);
                        next[i] -= 5;
                        tryNextState(next);
                    }
                    if(state.state[i] < 20) {
                        int[] next = Arrays.copyOf(state.state, sLen);
                        next[i] += 5;
                        tryNextState(next);
                    }
                    if(state.state[i] % 5 > 0) {
                        int[] next = Arrays.copyOf(state.state, sLen);
                        next[i]--;
                        tryNextState(next);
                    }
                    if(state.state[i] % 5 < 4) {
                        int[] next = Arrays.copyOf(state.state, sLen);
                        next[i]++;
                        tryNextState(next);
                    }

                }
            }
            ans++;
        }

        System.out.println(ans);
    }

    private static void tryNextState(int[] next) {
        if(Arrays.stream(next).distinct().count() == sLen) {
            Arrays.sort(next);
            State nextState = new State(next);
            if(!visited.contains(nextState)) {
                q.add(nextState);
                visited.add(nextState);
            }
        }
    }

    private static boolean isConnected(State state) {
        int[] pos = state.state;
        boolean[] vis = new boolean[pos.length];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        while(!q.isEmpty()) {
            int x = q.poll();
            int r = pos[x] / 5;
            int c = pos[x] % 5;

            for(int y = 1; y < pos.length; y++) {
                if(!vis[y]) {
                    int r2 = pos[y] / 5;
                    int c2 = pos[y] % 5;
                    if(Math.abs(r2 - r) + Math.abs(c2 - c) == 1) {
                        vis[y] = true;
                        q.add(y);
                    }
                }
            }
        }
        for(boolean v : vis) if(!v) return false;
        return true;
    }


}

class State {
    int[] state;
    State(int... state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State other = (State) o;
        return Arrays.equals(this.state, other.state);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.state);
    }
}

