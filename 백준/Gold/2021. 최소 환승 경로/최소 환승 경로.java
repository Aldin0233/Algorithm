import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L;
    static Map<Integer, Set<Integer>> subwayMap;
    static Map<Integer, Set<Integer>> lineMap;
    static int start, end;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        subwayMap = new HashMap<>();
        lineMap = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            subwayMap.put(i, new HashSet<>());
        }
        for(int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine());
            lineMap.put(i, new HashSet<>());
            int station = Integer.parseInt(st.nextToken());
            while(station != -1) {
                subwayMap.get(station).add(i);
                lineMap.get(i).add(station);
                station = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        int tryCnt = 0;
        boolean[] stationVisited = new boolean[N + 1];
        boolean[] lineVisited = new boolean[L + 1];
        Queue<Integer> q = new LinkedList<>();
        stationVisited[start] = true;
        for(int line : subwayMap.get(start)) {
            lineVisited[line] = true;
            q.add(line);
        }
        ans = -1;
        search: while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int curLine = q.poll();
                if(isEndLine(curLine)) {
                    ans = tryCnt;
                    break search;
                }
                for(int station : lineMap.get(curLine)) {
                    if(!stationVisited[station]) {
                        stationVisited[station] = true;
                        for(int line : subwayMap.get(station)) {
                            if(!lineVisited[line]) {
                                lineVisited[line] = true;
                                q.add(line);
                            }
                        }
                    }
                }
            }
            tryCnt++;
        }
        System.out.println(ans);
    }

    private static boolean isEndLine(int line) {
        return subwayMap.get(end).contains(line);
    }

}
