import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, result;
	static int[] P;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInputSetting();
		testProcess();
		testOutput();
	}

	private static void testInputSetting() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		P = new int[N];
	}

	private static void testProcess() throws IOException {
		for(int i = 0 ; i< N; i++) {
			MakeSet(i);
		}
		for(int i = 1; i<=M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = FindRef(Integer.parseInt(st.nextToken()));
			int b = FindRef(Integer.parseInt(st.nextToken()));
			if(a == b) {
				result = i;
				return;
			}
			Union(a, b);
			
		}
		
	}
	static void MakeSet(int i) {
		P[i] = i;
	}
	static int FindRef(int i) {
		if(P[i] == i) {
			return i;
		}
		return P[i] = FindRef(P[i]);
	}
	static void Union(int i, int j) {
		P[j] = i;
	}

	private static void testOutput() {
		System.out.println(result);
	}
}