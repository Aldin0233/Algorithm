import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, W;
    static Event[] events;
    static int[][] costDp, choiceDp;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        events = new Event[W + 1];
        for(int i = 1; i <= W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            events[i] = new Event(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        costDp = new int[W + 1][W + 1];
        choiceDp = new int[W + 1][W + 1];

        for(int[] row: costDp) Arrays.fill(row, -1);
        ans.append(dfs(0, 0)).append("\n");
        trace();
        System.out.println(ans);
    }

    //dfs + dp
    private static int dfs(int policeLastPos1, int policeLastPos2) {
        int next = Math.max(policeLastPos1, policeLastPos2) + 1;
        if(next > W) return 0;
        if(costDp[policeLastPos1][policeLastPos2] != -1) return costDp[policeLastPos1][policeLastPos2];

        int d1 = dfs(next, policeLastPos2) + dist(policeLastPos1, next, true);
        int d2 = dfs(policeLastPos1, next) + dist(policeLastPos2, next, false);

        if(d1 < d2) {
            choiceDp[policeLastPos1][policeLastPos2] = 1; //선택한 경찰차
            return costDp[policeLastPos1][policeLastPos2] = d1; //이동 거리
        } else {
            choiceDp[policeLastPos1][policeLastPos2] = 2;
            return costDp[policeLastPos1][policeLastPos2] = d2;
        }
    }

    //추적
    static void trace() {
        int lastPos1 = 0, lastPos2 = 0;
        while(Math.max(lastPos1, lastPos2) < W) {
            int whoTreated = choiceDp[lastPos1][lastPos2];
            ans.append(whoTreated).append("\n");
            int next = Math.max(lastPos1, lastPos2) + 1;
            if(whoTreated == 1) lastPos1 = next;
            else lastPos2 = next;
        }
    }
    
    //거리 계산
    private static int dist(int from, int to, boolean isOne) {
        Event fromEvent, toEvent = events[to];
        if(from == 0) {
            fromEvent = isOne ? new Event(1, 1) : new Event(N, N);
        } else {
            fromEvent = events[from];
        }
        return fromEvent.calDist(toEvent);
    }

}

//사건 위치, 위치
class Event {
    int x, y;

    Event(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int calDist(Event o) {
        return Math.abs(x - o.x) + Math.abs(y - o.y);
    }
}



