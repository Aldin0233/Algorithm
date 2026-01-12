import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] intervalPower;
    static String[] intervalName;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        intervalPower = new int[N];
        intervalName = new String[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            intervalPower[i] = power;
            intervalName[i] = name;
        }
        for(int i = 0; i < M; i++) {
            ans.append(findInterval(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.println(ans);
    }

    static String findInterval(int n) {
        return intervalName[findIdx(n)];
    }

    static int findIdx(int n) {
        int lo = 0, hi = N;
        while(lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (intervalPower[mid] >= n) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }




}




