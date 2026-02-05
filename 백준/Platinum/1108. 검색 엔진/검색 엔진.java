import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Map<String, Set<String>> out = new HashMap<>();
    static Map<String, Set<String>> in = new HashMap<>();
    static Map<String, Integer> idx = new HashMap<>();
    static List<String> nodes = new ArrayList<>();

    static Set<String> visited = new HashSet<>();
    static List<String> order = new ArrayList<>();
    static Map<String, Integer> comp = new HashMap<>();

    static long[] dp;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            if(!idx.containsKey(site)) {
                idx.put(site, nodes.size());
                nodes.add(site);
            }
            for(int j = 0; j < cnt; j++) {
                String otherSite = st.nextToken();
                addEdge(otherSite, site);
            }
        }

        String targetQuery = br.readLine();
        checkScc();

        dp = new long[nodes.size()];
        Arrays.fill(dp, -1);
        long result = calScore(targetQuery);

        System.out.println(result);
    }

    static long calScore(String site) {
        int siteIdx = idx.get(site);
        if(dp[siteIdx] != -1) return dp[siteIdx];
        long score = 1;
        int sccKey = comp.get(site);
        for(String u : in.getOrDefault(site, new HashSet<>())) {
            if(sccKey == comp.get(u)) continue;
            score += calScore(u);
        }
        return dp[siteIdx] = score;
    }

    static void checkScc() {
        for(String u : nodes) {
            if(!visited.contains(u)) dfs1(u);
        }
        int sccCnt = 0;
        for(int i = order.size() - 1; i >= 0; i--) {
            String u = order.get(i);
            if(!comp.containsKey(u)) dfs2(u, sccCnt++);
        }
    }

    static void dfs1(String u) {
        visited.add(u);
        for(String v : out.getOrDefault(u, Collections.emptySet())) {
            if(!visited.contains(v)) dfs1(v);
        }
        order.add(u);
    }

    static void dfs2(String u, int cid) {
        comp.put(u, cid);
        for(String v : in.getOrDefault(u, Collections.emptySet())) {
            if(!comp.containsKey(v)) dfs2(v, cid);
        }
    }

    static void addEdge(String u, String v) {
        out.computeIfAbsent(u, k -> new HashSet<>()).add(v);
        in.computeIfAbsent(v, k -> new HashSet<>()).add(u);
        if(!idx.containsKey(u)) {
            idx.put(u, nodes.size());
            nodes.add(u);
        }
    }



}




