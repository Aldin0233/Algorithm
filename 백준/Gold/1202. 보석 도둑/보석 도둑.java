import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        List<Integer> bags = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(weight, price));
        }

        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(jewels);
        Collections.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long maxPrice = 0;
        int jewelIndex = 0;

        for (int bag : bags) {
            while (jewelIndex < N && jewels.get(jewelIndex).weight <= bag) {
                pq.add(jewels.get(jewelIndex).price);
                jewelIndex++;
            }

            if (!pq.isEmpty()) {
                maxPrice += pq.poll();
            }
        }

        System.out.println(maxPrice);
    }
}

class Jewel implements Comparable<Jewel> {
    int weight, price;

    public Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int compareTo(Jewel o) {
        return Integer.compare(this.weight, o.weight);
    }
}