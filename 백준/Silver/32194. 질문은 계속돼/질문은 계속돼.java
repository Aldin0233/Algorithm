
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<int[]> prefixSum = new ArrayList<>();
    static final int YES = 1;
    static final int NO = 2;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prefixSum.add(new int[]{0, 0});
        prefixSum.add(new int[]{1, 0});
        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int answer = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken());
            int[] last = prefixSum.get(i);
            if(answer == YES) {
                boolean isTrue = prefixSum.get(x)[1] == prefixSum.get(y)[1];
                ans.append(isTrue ? "Yes\n" : "No\n"); //No가 같으면 전부 Yes;
                prefixSum.add(addAnswerState(last, isTrue));
            } else {
                boolean isTrue = prefixSum.get(x)[0] == prefixSum.get(y)[0];
                ans.append(isTrue ? "Yes\n" : "No\n");
                prefixSum.add(addAnswerState(last, isTrue));
            }
        }
        System.out.println(ans);
    }

    private static int[] addAnswerState(int[] last, boolean isTrue) {
        return new int[] {isTrue ? last[0] + 1 : last[0], !isTrue ? last[1] + 1 : last[1]};
    }




}








