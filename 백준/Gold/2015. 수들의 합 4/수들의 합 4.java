import java.util.*;

import java.io.*;

public class Main {

    static int N, K;

    static Map<Long, Integer> prefixSum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(st.nextToken());

        prefixSum = new HashMap<>();

        long sum = 0;

        prefixSum.put(0L, 1); // 합이 K인 부분합을 위해 초기값

        long ans = 0;

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {

            sum += Long.parseLong(st.nextToken());

            if (prefixSum.containsKey(sum - K)) {

                ans += prefixSum.get(sum - K);

            }

            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);

        }

        System.out.println(ans);

    }

}           