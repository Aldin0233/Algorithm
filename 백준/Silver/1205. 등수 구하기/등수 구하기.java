import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, newScore, P;
    static int[] originalRankList;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        newScore = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        if(N == 0) {
            System.out.println(1);
            return;
        }
        originalRankList = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            originalRankList[i] = Integer.parseInt(st.nextToken());
        }
        if(N == P && newScore <= originalRankList[N - 1]) {
            System.out.println(-1);
            return;
        }
        int rank = 1;
        for(int i = 0; i < N; i++) {
            if(originalRankList[i] > newScore) rank++;
            else break;
        }
        System.out.println(rank);
    }









}




