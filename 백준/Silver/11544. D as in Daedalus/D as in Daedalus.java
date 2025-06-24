

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int originalScore, bestScore;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        originalScore = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int B = Integer.parseInt(st.nextToken());
            int Daedalus = Integer.parseInt(st.nextToken());
            int sum = 0;
            for(int j = 1; j < N; j++) {
                sum += Integer.parseInt(st.nextToken());
            }
            if(sum + Daedalus <= B) {
                originalScore += Daedalus;
            }
            pq.add(B - sum);
        }

        int idx = 4;
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            while (idx >= 0) {
                int cardScore = (int) Math.pow(10, idx);
                if (cardScore <= cur) {
                    bestScore += cardScore;
                    break;
                }
                idx--;
            }
            if(idx < 0) break;
        }

        ans = bestScore - originalScore;

        System.out.println(ans);
    }


}


