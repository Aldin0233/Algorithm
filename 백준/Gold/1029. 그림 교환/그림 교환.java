import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] buyPrice;
    static int[][] memo;
    static int maxOwner;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buyPrice = new int[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                buyPrice[i][j] = line.charAt(j) - '0';
            }
        }
        maxOwner = 1;
        //이 방문자 조합의 마지막 오너 최대 (2^15) * 15
        memo = new int[1 << N][N];
        for(int[] row : memo) Arrays.fill(row, 10);
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(1, 0, 0));
        while(!pq.isEmpty()) {
            State state = pq.poll();
            if(memo[state.visitBit][state.lastOwner] < state.curPrice) continue;
            memo[state.visitBit][state.lastOwner] = state.curPrice;
            maxOwner = Math.max(maxOwner, state.visitCnt);
            for(int i = 0; i < N; i++) {
                //이미 방문함
                if((state.visitBit & (1 << i)) != 0) continue;
                int newPrice = buyPrice[state.lastOwner][i];
                if(newPrice < state.curPrice) continue;
                int newMask = state.visitBit | (1 << i);
                if(newPrice >= memo[newMask][i]) continue;
                memo[newMask][i] = newPrice;
                pq.offer(new State(newMask, i, newPrice));
            }
        }

        System.out.println(maxOwner);
    }

    static class State implements Comparable<State> {
        int visitBit;
        int visitCnt;
        int lastOwner;
        int curPrice;
        State(int visitBit, int lastOwner, int curPrice) {
            this.visitBit = visitBit;
            this.visitCnt = Integer.bitCount(visitBit);
            this.lastOwner = lastOwner;
            this.curPrice = curPrice;
        }

        public int compareTo(State o) {
            if(this.visitCnt == o.visitCnt) return Integer.compare(curPrice, o.curPrice);
            return Integer.compare(visitCnt, o.visitCnt);
        }
    }





}

