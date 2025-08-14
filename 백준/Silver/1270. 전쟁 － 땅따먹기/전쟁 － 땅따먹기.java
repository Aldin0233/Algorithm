
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Map<Long, Integer> map;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int Ti = Integer.parseInt(st.nextToken());
            map = new HashMap<>();
            for(int j = 0; j < Ti; j++) {
                Long a = Long.parseLong(st.nextToken());
                map.put(a, map.getOrDefault(a, 0) + 1);
            }
            Long maxArmyNum = -1L;
            int max = 0;
            int sum = 0;
            for(Long key : map.keySet()) {
                int army = map.get(key);
                if(army > max) {
                    max = army;
                    maxArmyNum = key;
                }
                sum += army;
            }
            if(max > sum / 2) {
                ans.append(maxArmyNum).append("\n");
            } else {
                ans.append("SYJKGW\n");
            }
        }


        System.out.println(ans);
    }

}



