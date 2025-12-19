import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, R;
    static Map<Integer, Integer> map;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        R = Integer.parseInt(br.readLine());
        nextR: for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            StringBuilder prescription = new StringBuilder();
            for(int j = 0; j < L; j++) {
                int s = Integer.parseInt(st.nextToken());
                if(map.containsKey(s)) {
                    prescription.append(map.get(s)).append(" ");
                }
                else {
                    ans.append("YOU DIED\n");
                    continue nextR;
                }
            }
            ans.append(prescription.toString().trim()).append("\n");
        }
        System.out.println(ans);

    }


}



