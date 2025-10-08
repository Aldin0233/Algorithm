import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static String order;
    static int LCnt = 0, SCnt = 0;
    static int ans;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        order = br.readLine();
        ans = 0;
        for(int i = 0; i < N; i++) {
            char c = order.charAt(i);
            if(c >= '1' && c <= '9') {
                ans++;
            } else if(c == 'L' || c == 'S') {
                if(c == 'L') LCnt++;
                else SCnt++;
            } else {
                if(c == 'R') {
                    if(LCnt > 0) {
                        LCnt--;
                        ans++;
                    } else break;
                } else {
                    if(SCnt > 0) {
                        SCnt--;
                        ans++;
                    } else break;
                }
            }
        }
        
        System.out.println(ans);
    }
    
}