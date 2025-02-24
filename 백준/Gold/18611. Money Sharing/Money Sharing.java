import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static long balance;
    public static String[] result;
    public static PriorityQueue<Request> pq;
    public static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1) n, m 읽기
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 요청 개수
        m = Integer.parseInt(st.nextToken()); // 보충 개수

        int total = n + m;
        long[] x = new long[total];
        for (int i = 0; i < total; i++) {
            x[i] = Long.parseLong(br.readLine());
        }

        result = new String[total];

        pq = new PriorityQueue<>((a, b) -> b.amount - a.amount);

        balance = 0;

        for (int i = 0; i < total; i++) {
            long val = x[i];
            if (val > 0) {
                balance += val;
                result[i] = "resupplied";
            } else {
                int reqAmt = (int)(-val);

                result[i] = "approved";
                balance -= reqAmt;
                pq.add(new Request(reqAmt, i));

                if (balance < 0) {
                    Request largest = pq.poll();
                    result[largest.index] = "declined";
                    balance += largest.amount;
                }
            }
        }

        for (int i = 0; i < total; i++) {
            ans.append(result[i]).append("\n");
        }
        System.out.println(ans);
    }
}

class Request {
    int amount;
    int index;
    public Request(int amount, int index) {
        this.amount = amount;
        this.index = index;
    }
}