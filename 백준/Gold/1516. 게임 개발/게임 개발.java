import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] timeCost;
    static Map<Integer, List<Integer>> techTreeBuildings;
    static Map<Integer, Set<Integer>> needBuildings;
    static int[] finalBuildTime;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        techTreeBuildings = new HashMap<>();
        needBuildings = new HashMap<>();
        timeCost = new int[N + 1];
        finalBuildTime = new int[N + 1];
        for(int i = 1 ; i <= N; i++) {
            techTreeBuildings.put(i, new ArrayList<>());
            needBuildings.put(i, new HashSet<>());
        }
        for(int i = 1 ; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int buildTime = Integer.parseInt(st.nextToken());
            timeCost[i] = buildTime;
            int needTechTree = Integer.parseInt(st.nextToken());
            while(needTechTree != -1) {
                needBuildings.get(i).add(needTechTree);
                techTreeBuildings.get(needTechTree).add(i);
                needTechTree = Integer.parseInt(st.nextToken());
            }
        }
        PriorityQueue<Build> buildingQueue = new PriorityQueue<>();
        for(Integer building : needBuildings.keySet()) {
            if(needBuildings.get(building).isEmpty()) {
                buildingQueue.add(new Build(building, timeCost[building]));
            }
        }
        while(!buildingQueue.isEmpty()) {
            Build build = buildingQueue.poll();
            int buildingId = build.buildingId;
            int nowTime = build.time;
            finalBuildTime[buildingId] = nowTime;
            List<Integer> list = techTreeBuildings.get(buildingId);
            for(int building : list) {
                needBuildings.get(building).remove(buildingId);
                if(needBuildings.get(building).isEmpty()) {
                    buildingQueue.add(new Build(building, nowTime + timeCost[building]));
                }
            }
        }
        for(int i = 1; i <= N; i++) {
            ans.append(finalBuildTime[i]).append("\n");
        }
        System.out.println(ans);
    }

}

class Build implements Comparable<Build> {
    int buildingId;
    int time;
    public Build(int buildingId, int time) {
        this.buildingId = buildingId;
        this.time = time;
    }

    public int compareTo(Build build) {
        return time - build.time;
    }
}