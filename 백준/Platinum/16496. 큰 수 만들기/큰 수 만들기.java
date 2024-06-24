import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			String tmp = o1 + o2;
			String tmp2 = o2 + o1;
			return tmp2.compareTo(tmp);
		}
	});
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
	}

	private static void testProcess() {
		for(int i = 0 ; i<N; i++) {
			pq.add(st.nextToken());
		}
		
		if(pq.peek().equals("0")) { result.append(0); return; }
		
		while(!pq.isEmpty()) {
			result.append(pq.poll());
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}