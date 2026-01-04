import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //2453
    static int N, M;
    static int[] P;
    static int[] size;
    static List<int[]> rivalEdges;
    static Set<Integer>[] compressedRival;
    static List<int[]> parts;
    static int result;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t = 1; t <= 5; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            settingUnion();
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int order = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(order == 1) {
                    friend(a, b);
                } else {
                    rival(a, b);
                }
            }
            if(rivalEdgeCompress() && canKOI()) {
                ans.append(findBestKOI());
            }
            else ans.append("-1");
            ans.append("\n");
        }

        System.out.println(ans);
    }

    static int findBestKOI() {
        boolean[] dp = new boolean[N + 1];
        dp[0] = true;
        for(int[] p : parts) {
            int A = p[0];
            int B = p[1];
            boolean[] next = new boolean[N + 1];
            for(int s = 0; s <= N; s++) {
                if(!dp[s]) continue;
                if(s + A <= N) next[s + A] = true;
                if(s + B <= N) next[s + B] = true;
            }
            dp = next;
        }
        int best = N;
        for(int s = 0; s <= N; s++) {
            if(dp[s]) best = Math.min(best, Math.abs(N - 2 * s));
        }
        return best;
    }

    static boolean rivalEdgeCompress() {
        compressedRival = new HashSet[N + 1];
        for(int i = 1; i <= N; i++) compressedRival[i] = new HashSet<>();
        for (int[] rivalEdge : rivalEdges) {
            int u = rivalEdge[0];
            int v = rivalEdge[1];
            int ru = findRef(u);
            int rv = findRef(v);
            if (ru == rv) return false;
            compressedRival[ru].add(rv);
            compressedRival[rv].add(ru);
        }
        return true;
    }

    static boolean canKOI() {
        int[] color = new int[N + 1];
        Arrays.fill(color, -1);
        parts = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(findRef(i) != i) continue;
            if(color[i] != -1) continue;
            int A = 0, B = 0;
            Queue<Integer> q = new LinkedList<>();
            color[i] = 0;
            q.add(i);
            while(!q.isEmpty()) {
                int cur = q.poll();
                if(color[cur] == 0) A += size[cur];
                else B += size[cur];

                for(int next : compressedRival[cur]) {
                    if(color[next] == -1) {
                        color[next] = color[cur]^1;
                        q.add(next);
                    } else if(color[next] == color[cur]) {
                        return false;
                    }
                }
            }
            parts.add(new int[]{A, B});
        }
        return true;
    }

    static void settingUnion() {
        P = new int[N + 1];
        size = new int[N + 1];
        rivalEdges = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            P[i] = i;
        }
        Arrays.fill(size, 1);
    }

    static int findRef(int x) {
        if(x == P[x]) return x;
        return P[x] = findRef(P[x]);
    }

    static void friend(int x, int y) {
        x = findRef(x);
        y = findRef(y);
        if(x == y) return;
        if(size[x] < size[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        P[y] = x;
        size[x] += size[y];
    }

    static void rival(int x, int y) {
        rivalEdges.add(new int[]{x, y});
    }

}




