import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static Map<Integer, Set<Integer>> edges = new HashMap<>();
    static boolean[] active;
    static int pSize;
    static int[] P;
    static int[] query;
    static final String CONNECT = "CONNECT\n";
    static final String DISCONNECT = "DISCONNECT\n";
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= N; i++) {
            edges.put(i, new HashSet<>());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        //N개가 순서대로 처리됨으로 역순으로 추가해가면서 판단
        query = new int[N];
        for(int i = N - 1; i >= 0; i--) {
            query[i] = Integer.parseInt(br.readLine());
        }
        make();
        active = new boolean[N + 1];
        boolean[] result = new boolean[N];
        pSize = 0;
        for(int i = 0; i < N; i++) {
            //P 배열 내 집합의 갯수
            pSize++;
            active[query[i]] = true;
            for(int node: edges.get(query[i])) {
                if(active[node]) {
                    int a = find(node);
                    int b = find(query[i]);
                    //다를때 합치면서 그룹 갯수 --
                    if(a != b) {
                        pSize--;
                        union(a, b);
                    }
                }
            }
            if(pSize == 1) {
                result[i] = true;
            }
        }

        for(int i = N - 1; i >= 0; i--) {
            ans.append(result[i] ? CONNECT : DISCONNECT);
        }
        ans.append("DISCONNECT");


        System.out.println(ans);
    }

    private static void make() {
        P = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            P[i] = i;
        }
    }

    private static int find(int x) {
        if(P[x] == x) return x;
        return P[x] = find(P[x]);
    }

    private static void union(int a, int b) {
        P[find(a)] = find(b);
    }



}


