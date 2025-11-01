import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static final int[] humansPower = {1, 2, 3, 3, 4, 10};
    static final int[] orcsPower = {1, 2, 2, 2, 3, 5, 10};
    static final String humanWin = "Good triumphs over Evil";
    static final String orcWin = "Evil eradicates all trace of Good";
    static final String noWin = "No victor on this battle field";
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int hmTotal = 0, orcsTotal = 0;
            for(int i = 0; i < 6; i++) {
                hmTotal += humansPower[i] * Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 7; i++) {
                orcsTotal += orcsPower[i] * Integer.parseInt(st.nextToken());
            }
            ans.append(String.format("Battle %d: ", t));
            if(hmTotal > orcsTotal) {
                ans.append(humanWin);
            } else if(orcsTotal > hmTotal) {
                ans.append(orcWin);
            } else {
                ans.append(noWin);
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }







}