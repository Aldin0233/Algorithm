
import java.io.*;
import java.util.*;

public class Main {

    static int member;
    static Map<Integer, List<Integer>> map;
    static int candidateScore, candidate;
    static StringBuilder candidates = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        member = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        for(int i = 0 ; i < member ; i++) {
            map.put(i, new ArrayList<>());
        }
        String str = br.readLine();
        while(!("-1 -1".equals(str))) {
            StringTokenizer st = new StringTokenizer(str, " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map.get(a).add(b);
            map.get(b).add(a);
            str = br.readLine();
        }
        int[] scores = new int[member];
        for(int i = 0; i < member; i++) {
            scores[i] = search(i);
        }
        candidateScore = Integer.MAX_VALUE;
        candidate = 0;
        for(int i = 0 ; i < member ; i++) {
            if(scores[i] < candidateScore) {
                candidateScore = scores[i];
                candidate = 1;
                candidates.setLength(0);
                candidates.append(i + 1).append(" ");
            } else if(scores[i] == candidateScore) {
                candidate++;
                candidates.append(i + 1).append(" ");
            }
        }
        System.out.printf("%d %d\n", candidateScore, candidate);
        System.out.println(candidates);
    }

    private static int search(int idx) {
        int[] distance = new int[member];
        boolean[] visited = new boolean[member];
        Queue<Integer> q = new LinkedList<>();

        q.offer(idx);
        visited[idx] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : map.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    distance[next] = distance[cur] + 1;
                    q.offer(next);
                }
            }
        }

        int max = 0;
        for (int d : distance) {
            max = Math.max(max, d);
        }
        return max;
    }


}
