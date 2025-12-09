import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] HI, ARC;
    static long hiWin = 0, arcWin = 0, draw = 0;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        HI = new int[N];
        ARC = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            HI[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            ARC[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(HI);
        Arrays.sort(ARC);
        int hiIdx = 0, arcIdx = 0;
        for(int h : HI) {
            int leftSide = lowerBoundByHI(h);
            int rightSide = upperBoundByHI(h);
            hiWin += leftSide;
            arcWin += M - rightSide;
            draw += rightSide - leftSide;
        }
        ans.append(String.format("%d %d %d", hiWin, arcWin, draw));
        System.out.println(ans);
    }

    private static int lowerBoundByHI(int target) {
        int l = 0, r = ARC.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if(ARC[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private static int upperBoundByHI(int target) {
        int l = 0, r = ARC.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if(ARC[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

}


