import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static Square[] squares;
    static Set<Integer> xComp, yComp;
    static int[][] compSquare;
    static StringBuilder ans = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        squares = new Square[N + 1];
        xComp = new HashSet<>();
        yComp = new HashSet<>();
        for(int i = 1; i <= N; i++) {
            squares[i] = new Square(new StringTokenizer(br.readLine()));
            xComp.add(squares[i].x1);
            xComp.add(squares[i].x2);
            yComp.add(squares[i].y1);
            yComp.add(squares[i].y2);
        }
        
        //좌표로 압축
        List<Integer> xList = new ArrayList<>(xComp);
        List<Integer> yList = new ArrayList<>(yComp);
        Collections.sort(xList);
        Collections.sort(yList);
        Map<Integer, Integer> xIdx = new HashMap<>();
        Map<Integer, Integer> yIdx = new HashMap<>();
        for(int i = 0; i < xList.size(); i++) xIdx.put(xList.get(i), i);
        for(int i = 0; i < yList.size(); i++) yIdx.put(yList.get(i), i);

        //구간
        int[][] top = new int[xList.size() - 1][yList.size() - 1];

        for(int i = 1; i <= N; i++) {
            int x1 = xIdx.get(squares[i].x1);
            int y1 = yIdx.get(squares[i].y1);
            int x2 = xIdx.get(squares[i].x2);
            int y2 = yIdx.get(squares[i].y2);

            for(int xi = x1; xi < x2; xi++) {
                for(int yi = y1; yi < y2; yi++) {
                    top[xi][yi] = i;
                }
            }
        }

        long[] trueAreaList = new long[N + 1];
        for(int i = 0; i < xList.size() - 1; i++) {
            long dx = (long) xList.get(i + 1) - xList.get(i);
            for(int j = 0; j < yList.size() - 1; j++) {
                int sqIdx = top[i][j];
                if(sqIdx == 0) continue;
                long dy = (long) yList.get(j + 1) - yList.get(j);
                trueAreaList[sqIdx] += dx * dy;
            }
        }

        List<CompareState> compareStates = new ArrayList<>();
        for(int i = 1; i <= N; i++) compareStates.add(new CompareState(i, trueAreaList[i]));
        Collections.sort(compareStates);

        int[] pick = new int[K];
        for(int i = 0; i < K; i++) pick[i] = compareStates.get(i).idx;
        Arrays.sort(pick);

        for(int p : pick) ans.append(p).append(' ');

        System.out.println(ans);

    }

    static class Square {
        int x1, y1, x2, y2;
        public Square(StringTokenizer st) {
            this.x1 = Integer.parseInt(st.nextToken());
            this.y1 = Integer.parseInt(st.nextToken());
            this.x2 = Integer.parseInt(st.nextToken());
            this.y2 = Integer.parseInt(st.nextToken());
        }
    }

    static class CompareState implements Comparable<CompareState> {
        int idx;
        long areaSize;

        CompareState(int idx, long areaSize) {
            this.idx = idx;
            this.areaSize = areaSize;
        }

        public int compareTo(CompareState o) {
            if(this.areaSize == o.areaSize) return Integer.compare(this.idx, o.idx);
            return Long.compare(o.areaSize, this.areaSize);
        }
    }






}

