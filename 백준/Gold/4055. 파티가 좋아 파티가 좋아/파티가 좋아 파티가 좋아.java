import java.io.*;
import java.util.*;

public class Main {

    private static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = 1;
        while (true) {
            int p = Integer.parseInt(br.readLine());
            if (p == 0) break;
            List<Party> parties = new ArrayList<>();

            for (int i = 0; i < p; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken()) - 1;
                if (start > end) continue;
                parties.add(new Party(start, end));
            }

            Collections.sort(parties);

            int count = 0;
            for (int hour = 8; hour < 24; hour++) {
                for (int half = 0; half < 2; half++) {
                    for (int i = 0; i < parties.size(); i++) {
                        Party current = parties.get(i);
                        if (current.start <= hour && current.end >= hour) {
                            count++;
                            parties.remove(i);
                            break;
                        }
                    }
                }
            }

            ans.append("On day ").append(testCase++)
                    .append(" Emma can attend as many as ")
                    .append(count).append(" parties.\n");
        }
        System.out.print(ans);
    }

}

class Party implements Comparable<Party> {
    int start, end;

    public Party(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Party other) {
        if (this.end == other.end)
            return other.start - this.start;
        return this.end - other.end;
    }
}
