import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, daSom;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	
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
		N = Integer.parseInt(br.readLine().trim()); 
		if(N==1) return;
		daSom = Integer.parseInt(br.readLine().trim());
		for(int i = 1; i<N; i++) {
			pq.add(Integer.parseInt(br.readLine().trim()));
		}
	}

	private static void testProcess() {
		if(N==1) return;
		while(daSom <= pq.peek()) {
			int rival = pq.poll();
			rival--;
			daSom++;
			result++;
			pq.add(rival);
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
	
}