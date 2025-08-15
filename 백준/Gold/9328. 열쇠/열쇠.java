
import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static int h, w, findDocs;
    static String[] map;
    static boolean[][] docsVisited;
    static boolean[][] visited;
    static int keyCount;
    static boolean[] hasKey; //더해지기만 함 (가장자리 왔다갔다 하는 것에 대한 제약 X
    static boolean keyUpdate;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            testCaseInput(br);
            findDocs = 0;
            search();
            ans.append(findDocs).append("\n");
        }
        System.out.println(ans);
    }

    private static void testCaseInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new String[h];
        for(int i = 0; i < h; i++) {
            map[i] = br.readLine();
        }
        preKeySetting(br.readLine());
    }

    private static void preKeySetting(String line) {
        hasKey = new boolean[26];
        keyCount = 0;
        if(line.equals("0")) return;
        for(int i = 0; i < line.length(); i++) {
            hasKey[line.charAt(i) - 'a'] = true;
        }
        addKey();
    }

    private static void addKey() {
        int curLength = keyCount;
        int newLength = 0;
        for(int i = 0; i < 26; i++) {
            if(hasKey[i]) newLength++;
        }
        if(curLength != newLength) {
            keyCount = newLength;
            keyUpdate = true;
        }
    }

    private static void search() {
        docsVisited = new boolean[h][w];
        Set<int[]> entries = findAllEntry();
        keyUpdate = true; //처음엔 무조건 키 업데이트
        while(keyUpdate) {
            keyUpdate = false;
            visited = new boolean[h][w];
            for(int[] entry : entries) {
                bfs(entry);
                if(keyUpdate) break;
            }
        }
    }

    private static Set<int[]> findAllEntry() { //모든 입구 탐색
        Set<int[]> entries = new HashSet<>();
        int rightEnd = w - 1;
        int downEnd = h - 1;
        for(int i = 0; i < h; i++) {
            if(map[i].charAt(0) != '*') entries.add(new int[]{i, 0});
            if(map[i].charAt(rightEnd) != '*') entries.add(new int[]{i, rightEnd});
        }
        for(int i = 1; i < w - 1; i++) {
            if(map[0].charAt(i) != '*') entries.add(new int[]{0, i});
            if(map[downEnd].charAt(i) != '*') entries.add(new int[]{downEnd, i});
        }
        return entries;
    }

    private static void bfs(int[] entry) {
        if(!canGo(entry[0], entry[1])) {
            return;
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(entry);
        findDocs(entry[0], entry[1]);
        checkKey(entry[0], entry[1]);
        if(keyUpdate) return;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(canGo(nr, nc)) {
                    checkKey(nr, nc);
                    findDocs(nr, nc);
                    if(keyUpdate) return; //새 키로 다시 돌아보겠다
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

    }

    private static boolean canGo(int r, int c) { //방문 가능한지 확인
        if(r < 0 || r >= h || c < 0 || c >= w) return false;
        if(visited[r][c] || map[r].charAt(c) == '*') return false;
        if(map[r].charAt(c) >= 'A' && map[r].charAt(c) <= 'Z') {
            return hasKey[map[r].charAt(c) - 'A'];
        }
        return true;
    }

    private static void checkKey(int r, int c) { //키 확인
        if(map[r].charAt(c) >= 'a' && map[r].charAt(c) <= 'z') {
            hasKey[map[r].charAt(c) - 'a'] = true;
            addKey();
        }
    }

    private static void findDocs(int r, int c) { //문서 탐색, 전역으로 한번씩만 카운트
        if(map[r].charAt(c) == '$' && !docsVisited[r][c]) {
            findDocs++;
            docsVisited[r][c] = true;
        }
    }

}



