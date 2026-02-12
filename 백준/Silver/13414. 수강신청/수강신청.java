import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int K, L;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(L * 2);
        for (int i = 0; i < L; i++) {
            String id = br.readLine();
            if(map.containsKey(id)) {
                map.remove(id);
            }
            map.put(id, 1);
        }
        int cnt = 0;
        for(String id : map.keySet()) {
            ans.append(id).append("\n");
            cnt++;
            if(cnt == K) break;
        }

        System.out.println(ans);
    }





}

