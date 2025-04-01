import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long[] alphaWeight = new long[10];
        boolean[] isFirst = new boolean[10];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            isFirst[word.charAt(0) - 'A'] = true;
            long weight = 1;

            for (int j = word.length() - 1; j >= 0; j--) {
                alphaWeight[word.charAt(j) - 'A'] += weight;
                weight *= 10;
            }
        }

        List<Integer> letters = new ArrayList<>();
        for (int i = 0; i < 10; i++) letters.add(i);

        letters.sort((a, b) -> Long.compare(alphaWeight[b], alphaWeight[a]));

        int[] mapping = new int[10];
        Arrays.fill(mapping, -1);

        for (int i = 9; i >= 0; i--) {
            int idx = letters.get(i);
            if (!isFirst[idx]) {
                mapping[idx] = 0;
                letters.remove(i);
                break;
            }
        }

        int num = 9;
        for (int idx : letters) {
            mapping[idx] = num--;
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += alphaWeight[i] * mapping[i];
        }

        System.out.println(sum);
    }
}
