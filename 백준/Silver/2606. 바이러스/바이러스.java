import java.io.*;
import java.util.*;

public class Main {

    static int computerCnt, networkCnt;
    static int[] P;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        computerCnt = Integer.parseInt(br.readLine());
        networkCnt = Integer.parseInt(br.readLine());
        make(computerCnt);
        for (int i = 0; i < networkCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int refA = find(a);
            int refB = find(b);
            union(refA, refB);
        }
        int ans = 0;
        for(int i = 2; i <= computerCnt; i++) {
            if(find(i) == 1) ans++;
        }
        System.out.println(ans);

    }

    private static void make(int computerCnt) {
        P = new int[computerCnt + 1];
        for (int i = 1; i <= computerCnt; i++) {
            P[i] = i;
        }
    }

    private static int find(int x) {
        if(P[x] == x) return x;
        else return P[x] = find(P[x]);
    }

    private static void union(int x, int y) {
        if(x == y) return;
        P[x] = P[y] = Math.min(x, y);
    }



}


