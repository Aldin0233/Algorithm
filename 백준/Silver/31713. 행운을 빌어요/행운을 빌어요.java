import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int A, B;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            int minNeedStem = (B + 3) / 4;
            int minNeedLeaf = A * 3;
            boolean needStem = minNeedStem > A;
            boolean needLeaf = minNeedLeaf > B;
            if(needStem) {
                int needStemCnt = minNeedStem - A;
                int needLeafCnt = 0;
                if(minNeedStem * 3 > B) needLeafCnt = minNeedStem * 3 - B;
                ans.append(needStemCnt + needLeafCnt);
            } else if(needLeaf) {
                ans.append(minNeedLeaf - B);
            } else {
                ans.append(0);
            }
            ans.append("\n");
        }

        System.out.println(ans);
    }


}

