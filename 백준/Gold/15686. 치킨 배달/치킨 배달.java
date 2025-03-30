
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int result = Integer.MAX_VALUE;
    static List<int[]> chicken, home;
    static int[][] homeToChicken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chicken = new ArrayList<>();
        home = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if(x == 1) home.add(new int[]{i, j});
                else if(x == 2) chicken.add(new int[]{i, j});
            }
        }
        int homeCnt = home.size();
        int chickenCnt = chicken.size();
        homeToChicken = new int[homeCnt][chickenCnt];
        for(int i = 0; i < homeCnt; i++) {
            for(int j = 0; j < chickenCnt; j++) {
                homeToChicken[i][j] = calDist(home.get(i), chicken.get(j));
            }
        }

        selectDfs(new ArrayList<>(), 0);
        System.out.println(result);
    }

    private static void selectDfs(List<Integer> chickenList, int idx) {
        if(chickenList.size() == M) {
            result = Math.min(result, calculateCityChickenDist(chickenList));
            return;
        }

        for(int i = idx; i < chicken.size(); i++) {
            chickenList.add(i);
            selectDfs(chickenList, i + 1);
            chickenList.remove(chickenList.size() - 1);
        }
    }

    private static int calculateCityChickenDist(List<Integer> chickenList) {
        int sum = 0;
        for(int i = 0; i < home.size(); i++) {
            int min = Integer.MAX_VALUE;
            for(int c : chickenList) {
                min = Math.min(min, homeToChicken[i][c]);
            }
            sum += min;
        }
        return sum;
    }

    private static int calDist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

}
