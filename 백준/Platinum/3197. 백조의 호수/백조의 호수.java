import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static int swan1 = -1, swan2 = -1; //백조 그룹명
    static List<Integer> group = new ArrayList<>();
    static int[][] map;
    static Set<Integer> touchedIce = new HashSet<>();
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력 받고 맵 전처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j) == '.' ? -2 : str.charAt(j) == 'L' ? -3 : -1;
            }
            //초기 입력은 그냥 물은 -2, 백조는 -3;
        }
        //각 그룹 별로 숫자를 새겨 집합을 관리한다.
        //녹을 얼음들을 찾아둔다.
        preMapProcessing();
        int ans = 0;
        while(true) {
            //매 턴 백조가 있는 그룹들을 기록하고 있다가, 같은 그룹이라 판명되면 종료
            int findSwan1Ref = findRef(swan1);
            int findSwan2Ref = findRef(swan2);
            if(findSwan1Ref == findSwan2Ref) {
                break;
            }
            Set<Integer> newTouchedIce = new HashSet<>(); //새롭게 녹을 얼음
            //접촉 얼음이 녹을 때, 새로운 주변 얼음을 찾는데,
            //이때 얼음이 지워지고 나서 옆에 물이 있을 때 그룹을 합친다.
            for(int ice : touchedIce) {
                int[] icePos = intToIcePos(ice);
                List<Integer> nearbyWater = new ArrayList<>();
                for(int d = 0 ; d < 4 ; d++) {
                    int nr = icePos[0] + dr[d];
                    int nc = icePos[1] + dc[d];
                    if(isValid(nr, nc)) {
                        if(map[nr][nc] == -1) {
                            map[nr][nc] = -4;
                            newTouchedIce.add(icePosToInt(nr, nc));
                        }
                        else if(map[nr][nc] >= 0) nearbyWater.add(map[nr][nc]);
                    }
                }
                int firstWater = findRef(nearbyWater.get(0)); //최소한 하나는 물임
                for(int idx = 1; idx < nearbyWater.size(); idx++) {
                    union(firstWater, nearbyWater.get(idx)); //근처에 있는 모든 물 병합
                }
                map[icePos[0]][icePos[1]] = findRef(firstWater); //얼음에 그룹명 부여 //미리 처리해야 짝수 거리도 병함됨
            }
            touchedIce = newTouchedIce; //다음 접촉 얼음 갱신
            ans++; //시간 증가
        }
        System.out.println(ans);
    }

    private static void preMapProcessing() {
        //그룹화
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] < -1) { //방문하지 않은 얼음이 아닌 장소
                    int ref = group.size(); //현재 사이즈를 대표로 설정하고 삽입
                    group.add(ref); //삽입
                    groupMakeBfs(ref, i, j);
                }
            }
        }
        //접촉 얼음 찾기
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == -1) { //미접촉 얼음
                    for(int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(isValid(nr, nc) && map[nr][nc] >= 0) { //주위가 물일때
                            map[i][j] = -4; //접촉한 얼음으로 표시 변경
                            touchedIce.add(icePosToInt(i, j));
                        }
                    }
                }
            }
        }
    }

    private static int icePosToInt(int r, int c) {
        return (r * C) + c;
    }

    private static int[] intToIcePos(int pos) {
        int r = pos / C;
        int c = pos - (r * C);
        return new int[]{r, c};
    }

    //그룹으로 만들기
    private static void groupMakeBfs(int ref, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        checkSwan(ref, i, j);
        map[i][j] = ref;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(isValid(nr, nc) && map[nr][nc] < -1) {
                    checkSwan(ref, nr, nc);
                    map[nr][nc] = ref;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    //맵 안에 있는지 확인
    private static boolean isValid(int i, int j) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }

    //백조인지 확인
    private static void checkSwan(int ref, int i, int j) {
        if(map[i][j] == -3) {
            if(swan1 == -1) swan1 = ref;
            else swan2 = ref;
        }
    }

    //속한 그룹 찾기
    private static int findRef(int X) {
        if(group.get(X) == X) return X;
        group.set(X, findRef(group.get(X)));
        return findRef(group.get(X));
    }

    //그룹 병합
    private static void union(int A, int B) {
        int ref1 = findRef(A);
        int ref2 = findRef(B);
        group.set(ref1, Math.min(ref1, ref2));
        group.set(ref2, Math.min(ref1, ref2));
    }

}



