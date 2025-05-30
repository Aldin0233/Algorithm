import java.io.*;
import java.util.*;

public class Main {

    static int T, N, M, printCnt;
    static Queue<Print> printQ;
    static int[] cntArr;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T  = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            cntArr = new int[10];
            printQ = new LinkedList<>();
            printCnt = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                int a = Integer.parseInt(st.nextToken());
                printQ.add(new Print(a, i));
                cntArr[a]++;
            }

            print: while(!printQ.isEmpty()) {
                Print cur = printQ.poll();
                if(canPrint(cur.priority)) {
                    printCnt++;
                    cntArr[cur.priority]--;
                    if(cur.firstIdx == M) {
                        ans.append(printCnt).append("\n");
                        break print;
                    }
                } else {
                    printQ.add(cur);
                }
            }

        }

        System.out.println(ans);
    }

    private static boolean canPrint(int priority) {
        for(int i = priority + 1; i < 10; i++) {
            if(cntArr[i] > 0) return false;
        }
        return true;
    }

}

class Print{
    int priority;
    int firstIdx;
    public Print(int priority, int firstIdx) {
        this.priority = priority;
        this.firstIdx = firstIdx;
    }
}
