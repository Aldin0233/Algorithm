import java.io.*;
import java.util.*;

public class Main {

    private static int[][] nArr= new int[10001][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Test = Integer.parseInt(br.readLine());
        makeNArr();
        for(int t = 1; t <= Test; t++) {
            System.out.println(getMakeMethod(Integer.parseInt(br.readLine())));
        }
    }

    private static void makeNArr() {
        nArr[1][0] = 1;
        nArr[2][0] = 1;
        nArr[2][1] = 1;
        nArr[3][0] = 1;
        nArr[3][1] = 1;
        nArr[3][2] = 1;
        for(int i = 4; i < nArr.length; i++) {
            nArr[i][0] = nArr[i-1][0];
            nArr[i][1] = nArr[i-2][0] + nArr[i-2][1];
            nArr[i][2] = nArr[i-3][0] + nArr[i-3][1] + nArr[i-3][2];
        }
    }

    private static int getMakeMethod(int n) {
        return nArr[n][0] + nArr[n][1] + nArr[n][2];
    }
}
