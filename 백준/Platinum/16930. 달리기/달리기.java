
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static String[] map; //맵
    static boolean[][] visited;
    static Queue<int[]> q;
    static Map<Integer, IntervalTree> checkedRowIntervals, checkedColIntervals;
    static int x2, y2; //목적지
    static int ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }
        st = new StringTokenizer(br.readLine());
        q = new LinkedList<>();
        q.add(new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1});
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;
        visited = new boolean[N][M];
        visited[q.peek()[0]][q.peek()[1]] = true;
        bfs();

        System.out.println(ans);
    }

    //BFS 차례차례 탐색
    private static void bfs() {
        makeIntervals();
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int[] cur = q.poll();
                if(isDestination(cur[0], cur[1])) {
                    ans = cnt;
                    return;
                }
                int row = cur[0];
                int col = cur[1];
                int minL = col, maxR = col;
                FindIntervalResponse rowList = findIntervals(row, true, col);
                if(rowList.left != null) minL = processIntervals(true, row, rowList.left, -1, col);
                if(rowList.right != null) maxR = processIntervals(true, row, rowList.right, 1, col);
                addIntervals(row, true, minL, maxR);
                minL = maxR = row;
                FindIntervalResponse colList = findIntervals(col, false, row);
                if(colList.left != null) minL = processIntervals(false, col, colList.left, -1, row);
                if(colList.right != null) maxR = processIntervals(false, col, colList.right, 1, row);
                addIntervals(col, false, minL, maxR);
            }
            cnt++;
        }
        ans = -1;
    }

    //구간 진행 //(행 열 여부, 고정 값, 구간, 방향)
    private static int processIntervals(boolean isRow, int fixed, Interval iv, int delta, int now) {
        int start = delta == -1 ? iv.r : iv.l;
        int end = delta == -1 ? iv.l : iv.r;

        int lastReachable = now;

        for (int i = start; delta == -1 ? i >= end : i <= end; i += delta) {
            int r = isRow ? fixed : i;
            int c = isRow ? i : fixed;

            if (map[r].charAt(c) == '#') {
                break; // 벽을 만나면 더 이상 진행 불가
            }

            lastReachable = i; // 방문 여부 상관없이 마지막 위치

            if (!visited[r][c]) {
                visited[r][c] = true;
                q.add(new int[]{r, c});
            }
        }

        return lastReachable;
    }

    //구간 목록 초기화
    private static void makeIntervals() {
        checkedRowIntervals = new HashMap<>();
        checkedColIntervals = new HashMap<>();
        for(int i = 0; i < N; i++) {
            checkedRowIntervals.put(i, new IntervalTree());
        }
        for(int i = 0; i < M; i++) {
            checkedColIntervals.put(i, new IntervalTree());
        }
    }

    private static FindIntervalResponse findIntervals(int line, boolean isRow, int pos) {
        IntervalTree intervalTree = isRow ? checkedRowIntervals.get(line) : checkedColIntervals.get(line);
        int maxPos = isRow ? M - 1 : N - 1;
        return intervalTree.queryDiff(pos, K, maxPos);
    }

    private static void addIntervals(int pos, boolean isRow, int minL, int maxR) {
        IntervalTree intervalTree = isRow ? checkedRowIntervals.get(pos) : checkedColIntervals.get(pos);
        intervalTree.addAndMerge(new Interval(minL, maxR));
    }

    private static boolean isDestination(int r, int c) {
        return r == x2 && c == y2;
    }


}


//한 행 혹은 한 열 단위로 구간 탐색을 진행했다고 가정해서 탐색 안된 구간을 찾는 메서드
class IntervalTree {
    List<Interval> intervals;

    public IntervalTree() {
        intervals = new ArrayList<>();
    }

    public FindIntervalResponse queryDiff(int now, int K, int maxPos) {
        FindIntervalResponse res = new FindIntervalResponse();
        int leftLimit = Math.max(0, now - K);
        int rightLimit = Math.min(maxPos, now + K);

        int idx = Collections.binarySearch(intervals,
                new Interval(now, now),
                Comparator.comparingInt(a -> a.l));
        Interval inside = null, leftIv = null, rightIv = null;

        if (idx >= 0) {
            inside = intervals.get(idx);
            if (idx > 0) leftIv = intervals.get(idx - 1);
            if (idx + 1 < intervals.size()) rightIv = intervals.get(idx + 1);
        } else {
            int insertPoint = -idx - 1;
            if (insertPoint > 0) {
                Interval tmp = intervals.get(insertPoint - 1);
                if (tmp.l <= now && tmp.r >= now) {
                    inside = tmp;
                    if (insertPoint - 1 > 0) leftIv = intervals.get(insertPoint - 2);
                } else {
                    leftIv = tmp;
                }
            }
            // <-- FIX: use insertPoint (first interval whose l > now), not insertPoint + 1
            if (insertPoint < intervals.size()) {
                rightIv = intervals.get(insertPoint);
            }
        }

        if (leftIv != null) leftLimit = Math.max(leftLimit, leftIv.r + 1);
        int leftEnd = (inside != null ? inside.l - 1 : now);
        if (leftLimit <= leftEnd) {
            res.setLeft(new Interval(leftLimit, leftEnd));
        }

        if (rightIv != null) rightLimit = Math.min(rightLimit, rightIv.l - 1);
        int rightStart = (inside != null ? inside.r + 1 : now);
        if (rightStart <= rightLimit) {
            res.setRight(new Interval(rightStart, rightLimit));
        }

        return res;
    }

    //구간 추가 및 정렬
    public void addAndMerge(Interval newInterval) {
        if(newInterval.l > newInterval.r) return;

        if(intervals.isEmpty()) {
            intervals.add(newInterval);
            return;
        }

        List<Interval> newList = new ArrayList<>();
        int insertPos = 0;
        int nl = newInterval.l;
        int nr = newInterval.r;

        for(Interval interval : intervals) {
            if(interval.r < nl - 1) { //한칸 이상 차이나야 떨어진 것임
                newList.add(interval);
                insertPos++; //삽입 위치 조정
            } else if(interval.l > nr + 1) {
                newList.add(interval);
            } else {
                nl = Math.min(nl, interval.l);
                nr = Math.max(nr, interval.r);
            }
        }

        //이미 정렬된 리스트니 바로 해당 위치 삽입
        newList.add(insertPos, new Interval(nl, nr));
        intervals = newList;
    }

}

//반환 객체
class FindIntervalResponse {
    Interval left, right;
    FindIntervalResponse() {

    }

    public void setLeft(Interval left) {
        this.left = left;
    }

    public void setRight(Interval right) {
        this.right = right;
    }
}

//구간
class Interval {
    int l, r;

    public Interval(int l, int r) {
        this.l = l;
        this.r = r;
    }

}