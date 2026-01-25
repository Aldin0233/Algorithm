import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int W, H, F, C, x1, y1, x2, y2;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        int rowDuplicateLength = Math.min(F, W - F);
        int colDuplicateTime = C + 1;
        long painted = 0;
        long colLen = y2 - y1;
        if(rowDuplicateLength > x1){
            painted += 2L * (Math.min(rowDuplicateLength, x2) - x1) * colDuplicateTime;
        }
        if(rowDuplicateLength < x2){
            painted += 1L * (x2 - Math.max(x1, rowDuplicateLength)) * colDuplicateTime;
        }
        painted *= colLen;
        long totalUnPainted = ((long) W * H) - painted;
        System.out.println(totalUnPainted);
    }







}




