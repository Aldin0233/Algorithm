import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static boolean[] playerVisited;
    static Map<Integer, List<Player>> posMap;
    static int dfsResult;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(T-- > 0) {
            posMap = new HashMap<>();
            for(int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++) {
                    int posStat = Integer.parseInt(st.nextToken());
                    if(posStat == 0) continue;
                    posMap.computeIfAbsent(j, k -> new ArrayList<>()).add(new Player(i, posStat));
                }
            }
            playerVisited = new boolean[11];
            dfsResult = 0;
            dfs(0, 0);
            ans.append(dfsResult).append("\n");
        }

        System.out.println(ans);
    }

    private static void dfs(int posIdx, int sum) {
        if(posIdx >= 11) {
            dfsResult = Math.max(dfsResult, sum);
            return;
        }
        for(Player p : posMap.get(posIdx)) {
            if(playerVisited[p.pNo]) continue;
            playerVisited[p.pNo] = true;
            dfs(posIdx + 1, sum + p.posStat);
            playerVisited[p.pNo] = false;
        }
    }

}

class Player {
    int pNo;
    int posStat;
    public Player(int pNo, int posStat) {
        this.pNo = pNo;
        this.posStat = posStat;
    }
}


