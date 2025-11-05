import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] mapInfo;
    static int[][] roomInfo;
    static List<Integer> roomSizeInfo;
    static final int[] dr = {0, -1, 0, 1};
    static final int[] dc = {-1, 0, 1, 0};
    static int maxRoomSize, maxConnectedRoomSize;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mapInfo = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mapInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxRoomSize = 0;
        roomInfo = new int[M][N];
        roomSizeInfo = new ArrayList<>();
        for(int[] row : roomInfo) Arrays.fill(row, -1);
        int nowIdx = 0;
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(roomInfo[i][j] == -1) {
                    bfs(i, j, nowIdx++);
                }
            }
        }
        //방 갯수
        ans.append(nowIdx).append("\n");
        //가장 넓은 단일 방
        ans.append(maxRoomSize).append("\n");
        maxConnectedRoomSize = 0;
        findConnectedRooms();
        ans.append(maxConnectedRoomSize);
        System.out.println(ans);
    }

    private static void bfs(int i, int j, int roomIdx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        int size = 1;
        roomInfo[i][j] = roomIdx;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int wallInfo = mapInfo[cur[0]][cur[1]];
            for(int d = 0; d < 4; d++) {
                //방향별 벽이 없을 때
                if((wallInfo & (1 << d)) == 0) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(roomInfo[nr][nc] == -1) {
                        size++;
                        roomInfo[nr][nc] = roomIdx;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        maxRoomSize = Math.max(maxRoomSize, size);
        roomSizeInfo.add(size);
    }

    private static void findConnectedRooms() {
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                int curRoom = roomInfo[i][j];
                int wallInfo = mapInfo[i][j];
                for(int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(!inValid(nr, nc)) continue;
                    int nextRoom = roomInfo[nr][nc];
                    if(curRoom != nextRoom) {
                        maxConnectedRoomSize = Math.max(maxConnectedRoomSize, roomSizeInfo.get(curRoom) + roomSizeInfo.get(nextRoom));
                    }

                }
            }
        }
    }

    private static boolean inValid(int r, int c) {
        return r >= 0 && r < M && c >= 0 && c < N;
    }

}