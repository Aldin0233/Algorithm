
import java.awt.Point;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);

	static int N, K;
	static boolean[] visited = new boolean[100001];
	static PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
		public int compare(Point o1, Point o2) {
			return o1.y - o2.y;
		};
	});

	static int result;

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() {
		N = sc.nextInt();
		K = sc.nextInt();
		pq.add(new Point(N, 0));
	}

	private static void testProcess() {
		if (N >= K) {
			result = N - K;
			return;
		}
		while (!pq.isEmpty()) {
			Point np = pq.poll();
			int x = np.x;
			int y = np.y;
			if (x == K) {
				result = y;
				return;
			}
			if (visited[x])
				continue;
			visited[x] = true;
			if (x * 2 <= 100000) {
				pq.add(new Point(x * 2, y));
			}
			if (x > 0) {
				pq.add(new Point(x - 1, y + 1));
			}
			if (x < 100000) {
				pq.add(new Point(x + 1, y + 1));
			}

		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
