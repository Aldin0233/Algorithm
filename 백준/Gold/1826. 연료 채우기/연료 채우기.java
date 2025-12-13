import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<int[]> fuelInfos;
    static int L, P;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        fuelInfos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int fuelAmount = Integer.parseInt(st.nextToken());
            fuelInfos.add(new int[] { pos, fuelAmount });
        }

        Collections.sort(fuelInfos, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        int curFuel = P;
        int idx = 0;
        ans = 0;

        while(curFuel < L) {
            while(idx < N && fuelInfos.get(idx)[0] <= curFuel) {
                pq.add(fuelInfos.get(idx)[1]);
                idx++;
            }

            if(pq.isEmpty()) {
                System.out.println(-1);
                return;
            }

            curFuel += pq.poll();
            ans++;
        }

        System.out.println(ans);
    }



}


