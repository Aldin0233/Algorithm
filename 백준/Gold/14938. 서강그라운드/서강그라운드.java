

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R;
    static int[] items;
    static int[][] edges;

    static final int INF = 100000;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        items = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            items[i] = Integer.parseInt(st.nextToken());

        edges = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(edges[i], INF);
            edges[i][i] = 0;
        }

        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int i = Integer.parseInt(st.nextToken());
            edges[a][b] = Math.min(edges[a][b], i);
            edges[b][a] = Math.min(edges[b][a], i);
        }

        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if (edges[i][j] > edges[i][k] + edges[k][j]) {
                        edges[i][j] = edges[i][k] + edges[k][j];
                    }
                }
            }
        }
        ans = 0;
        for(int i = 0; i < N; i++) {
            int itemSum = 0;
            for(int j = 0; j < N; j++) {
                if(edges[i][j] <= M) {
                    itemSum += items[j];
                }
            }
            ans = Math.max(ans, itemSum);
        }


        System.out.println(ans);
    }

}


