import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int A, B, C;

	public Edge(int a, int b, int c) {
		A = a;
		B = b;
		C = c;
	}

	@Override
	public int compareTo(Edge o) {
		
		return this.C-o.C;
	}
	
	
}

public class Main {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int V, E;
	static PriorityQueue<Edge> edges;
	static int[] P;
	static int nowSel;
	
	static long result;
	
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
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = new int[V+1];
		edges = new PriorityQueue<>();
		for(int i = 1; i<=V; i++) {
			makeSet(i);
		}
		for(int i = 0; i<E; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, c));
		}
	}

	private static void testProcess() {
		while(!edges.isEmpty()) {
			Edge nowEdge = edges.poll();
			int a = findRef(nowEdge.A);
			int b = findRef(nowEdge.B);
			if(a==b) continue;
			Union(a, b);
			result += nowEdge.C;
			nowSel++;
			if(nowSel==V-1) {
				return;
			}
		}
	}
	
	static void makeSet(int x) {
		P[x] = x;
	}
	static int findRef(int x) {
		if(P[x] == x) {
			return x;
		}
		return P[x] = findRef(P[x]);
	}
	static void Union(int x, int y) {
		P[y] = x;
	}

	private static void testOutput() {
		System.out.println(result);
	}
}