import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M; //문제 설명의 M, N을 자주 사용하는 행열로 바꿔서 접근함
    static String[] field;
    static int trafficLightCnt = 0;
    static int[] A, B;
    static Map<Character, TrafficLight> trafficLight = new HashMap<>();
    static final int MAX = 1_000_000_000;
    static final int[] dr = new int[]{0, 0, -1, 1};
    static final int[] dc = new int[]{-1, 1, 0, 0};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while(N != 0 && M != 0) {
            field = new String[N];
            A = new int[2]; B = new int[2];
            trafficLightCnt = 0;
            for (int i = 0; i < N; i++) {
                field[i] = br.readLine();
                for(int j = 0; j < M; j++) {
                    if(field[i].charAt(j) == 'A') {
                        A[0] = i; A[1] = j;
                    } else if(field[i].charAt(j) == 'B') {
                        B[0] = i; B[1] = j;
                    } else if(field[i].charAt(j) >= '0' && field[i].charAt(j) <= '9') {
                        trafficLightCnt++;
                    }
                }
            }
            trafficLight.clear();
            for(int t = 0; t < trafficLightCnt; t++) {
                st = new StringTokenizer(br.readLine());
                char trafficLightNo = st.nextToken().charAt(0);
                boolean firstLightIsRow = st.nextToken().charAt(0) == '-';
                int rowTime = Integer.parseInt(st.nextToken());
                int colTime = Integer.parseInt(st.nextToken());
                trafficLight.put(trafficLightNo, new TrafficLight(firstLightIsRow, rowTime, colTime));
            }
            int result = MAX;
            int[][] visitedWithTime = new int[N][M];
            for(int[] row : visitedWithTime) {
                Arrays.fill(row, MAX);
            }
            PriorityQueue<MovementState> pq = new PriorityQueue<>();
            pq.add(new MovementState(A[0], A[1], 0));
            visitedWithTime[A[0]][A[1]] = 0;
            while(!pq.isEmpty()) {
                MovementState movementState = pq.poll();
                if(isLast(movementState.r, movementState.c)) {
                    result = Math.min(result, movementState.time);
                    continue;
                }
                for(int d = 0; d < 4; d++) {
                    int nr = movementState.r + dr[d];
                    int nc = movementState.c + dc[d];
                    if(inValid(nr, nc) && field[nr].charAt(nc) != '.') {
                        char next = field[nr].charAt(nc);
                        if(next >= '0' && next <= '9') {
                            int waitTime = trafficLight.get(next).calCanFirstTimeAfterWait(movementState.time, d < 2);
                            if(visitedWithTime[nr][nc] > waitTime) {
                                visitedWithTime[nr][nc] = waitTime;
                                pq.add(new MovementState(nr, nc, waitTime));
                            }
                        } else if(visitedWithTime[nr][nc] > movementState.time + 1) {
                            visitedWithTime[nr][nc] = movementState.time + 1;
                            pq.add(new MovementState(nr, nc, movementState.time + 1));
                        }

                    }
                }
            }
            ans.append(result == MAX ? "impossible\n" : result + "\n");
            br.readLine();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }

        System.out.println(ans);
    }

    private static boolean inValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static boolean isLast(int r, int c) {
        return B[0] == r && B[1] == c;
    }

}

class MovementState implements Comparable<MovementState> {
    int r, c;
    int time;

    MovementState(int r, int c, int time) {
        this.r = r;
        this.c = c;
        this.time = time;
    }

    public int compareTo(MovementState o) {
        return time - o.time;
    }
}

class TrafficLight {
    boolean firstLightIsRow;
    int rowTime;
    int colTime;

    TrafficLight(boolean firstLightIsRow, int rowTime, int colTime) {
        this.firstLightIsRow = firstLightIsRow;
        this.rowTime = rowTime;
        this.colTime = colTime;
    }

    int calCanFirstTimeAfterWait(int nowTime, boolean accessWayIsRow) {
        int period = rowTime + colTime;
        //신호등은 1 시간부터 작동 시작
        int remainWait = nowTime % period;
        int firstDur = firstLightIsRow ? rowTime : colTime;
        if(firstLightIsRow == accessWayIsRow) {
            return remainWait < firstDur ? nowTime + 1 : nowTime + (period - remainWait) + 1;
        } else {
            return remainWait >= firstDur ? nowTime + 1 : nowTime + (firstDur - remainWait) + 1;
        }
    }
}



