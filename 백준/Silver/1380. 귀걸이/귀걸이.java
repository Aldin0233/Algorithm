import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Map<String, Integer> nameIdx = new HashMap<>();
    static Map<Integer, String> nameMap = new HashMap<>();
    static boolean[] returnedEarRing;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int scenarios = 1;
        do {
            nameIdx.clear();
            nameMap.clear();
            for(int i = 0; i < N; i++) {
                getId(br.readLine());
            }
            returnedEarRing = new boolean[N + 1];
            for(int i = 0; i < N * 2 - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                returnedEarRing[idx] = !returnedEarRing[idx];
            }
            for(int i = 1; i <= N; i++) {
                if(returnedEarRing[i]) {
                    ans.append(scenarios++).append(" ").append(nameMap.get(i)).append("\n");
                }
            }
            N = Integer.parseInt(br.readLine());
        } while(N != 0);
        System.out.println(ans);
    }

    private static int getId(String name) {
        if(!nameIdx.containsKey(name)) {
            nameIdx.put(name, nameIdx.size() + 1);
            nameMap.put(nameIdx.get(name), name);
        }
        return nameIdx.get(name);
    }

}



