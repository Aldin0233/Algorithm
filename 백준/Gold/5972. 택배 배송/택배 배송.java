import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Edge implements Comparable<Edge>{
	int A, W;

	public Edge(int a, int w) {
		A = a;
		W = w;
	}

	@Override
	public int compareTo(Edge o) {
		return this.W - o.W;
	}
	
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static int[] cost;
	static boolean[] visited;	
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static Map<Integer, List<Edge>> edges = new HashMap<>();
	
	static final int INF = 987654321;	
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		cost = new int[N+1]; visited = new boolean[N+1];
		for(int i = 0 ; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			if(!edges.containsKey(A)) {
				edges.put(A, new ArrayList<>());
			}
			if(!edges.containsKey(B)) {
				edges.put(B, new ArrayList<>());
			}
			edges.get(A).add(new Edge(B, W));
			edges.get(B).add(new Edge(A, W));
		}
	}

	private static void testProcess() {
		Arrays.fill(cost, INF);
		cost[1] = 0;
		pq.add(new Edge(1, 0));
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(visited[edge.A]) continue;
			if(edge.A == N) {
				result = edge.W;
				return;
			}
			int A = edge.A;
			int W = edge.W;
			visited[A] = true;
			for(Edge e : edges.get(A)) {
				if(visited[e.A]) continue;
				int B = e.A;
				int nw = W + e.W;
				if(cost[B] > nw) {
					cost[B] = nw;
					pq.add(new Edge(B, nw));
				}
			}
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}