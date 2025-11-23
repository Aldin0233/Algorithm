import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, C;
    static int[] weightInfo;
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        weightInfo = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weightInfo[i] = Integer.parseInt(st.nextToken());
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int mid = N / 2;
        generateSubset(0, mid, 0, left);
        generateSubset(mid, N, 0, right);
        Collections.sort(left);
        Collections.sort(right);

        int rIdx = right.size() - 1;
        ans = 0;
        for(Integer i : left) {
            while(rIdx >= 0 && i + right.get(rIdx) > C) rIdx--;
            if(rIdx < 0) break;
            ans += (rIdx + 1);
        }
        System.out.println(ans);
    }

    private static void generateSubset(int idx, int end, int sum, List<Integer> list) {
        if(sum > C) return;
        if(idx == end) {
            list.add(sum);
            return;
        }
        generateSubset(idx + 1, end, sum + weightInfo[idx], list);
        generateSubset(idx + 1, end, sum, list);
    }


}

