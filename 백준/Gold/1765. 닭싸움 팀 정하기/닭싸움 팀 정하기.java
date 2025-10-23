import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    //union find로 친구 탐색
    static int[] P;
    //원수는 단 한 친구 그룹만 대표로 원수로 치면 됨. //모순 없음이 보장되기 때문
    static Map<Integer, Integer> enemy;

    static Set<Integer> teams = new HashSet<>();

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        make();

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(c == 'E') settingEnemy(a, b);
            else union(a, b);
        }

        for(int i = 1; i <= N; i++) {
            teams.add(find(i));
        }

        System.out.println(teams.size());

    }

    private static void make() {
        P = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            P[i] = i;
        }
        enemy = new HashMap<>();
    }

    private static int find(int x) {
        if(P[x] == x) return x;
        return P[x] = find(P[x]);
    }

    private static void union(int a, int b) {
        P[find(a)] = find(b);
    }

    private static void settingEnemy(int a, int b) {
        boolean aContains = enemy.containsKey(a);
        boolean bContains = enemy.containsKey(b);
        if(aContains || bContains) { //한쪽이라도 이미 원수 관계가 있다면 그쪽으로 종속
            //원수의 원수는 친구다
            if(aContains && bContains) {
                union(a, enemy.get(b));
                union(b, enemy.get(a));
            } else if(aContains){
                union(b, enemy.get(a));
            } else {
                union(a, enemy.get(b));
            }
        }
        if(!aContains) enemy.put(a, b);
        if(!bContains) enemy.put(b, a);
    }



}