import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static Enemy[] enemyInfo;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        enemyInfo = new Enemy[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            enemyInfo[i] = new Enemy(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(enemyInfo, Comparator.comparingInt(a -> a.wiz));
        ans = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int enemyStrCutOff = enemyInfo[i].str;
                int enemyDexCutOff = enemyInfo[j].dex;

                List<Integer> tmp = new ArrayList<>();
                for(Enemy e : enemyInfo) {
                    if(e.str <= enemyStrCutOff && e.dex <= enemyDexCutOff) {
                        tmp.add(e.wiz);
                    }
                }
                if(tmp.size() < K) continue;
                //지능 기준 정렬되어 있음
                int wizCutOff = tmp.get(K - 1);
                int cost = enemyStrCutOff + enemyDexCutOff + wizCutOff;
                ans = Math.min(ans, cost);
            }
        }
        System.out.println(ans);
    }

}

class Enemy {
    int str, dex, wiz;
    Enemy(int str, int dex, int wiz) {
        this.str = str;
        this.dex = dex;
        this.wiz = wiz;
    }
}