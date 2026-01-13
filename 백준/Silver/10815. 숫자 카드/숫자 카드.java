import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st; 
	static boolean[] Narr = new boolean[20000001];
	static int[] Marr;
	static int N, M;
	static StringBuilder result = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		test();
	}
	private static void test() throws NumberFormatException, IOException {
		testInput();
		testProcess();
		testOutput();
	}
	private static void testInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		st= new StringTokenizer(br.readLine());
		for(int i = 0 ; i <N; i++) {
			Narr[Integer.parseInt(st.nextToken())+10000000]=true;
		}
		M = Integer.parseInt(br.readLine());
		st= new StringTokenizer(br.readLine());
		Marr= new int[M];
		for(int i = 0 ; i<M; i++) {
			Marr[i] = Integer.parseInt(st.nextToken())+10000000;
		}
	}
	private static void testProcess() {
		for(int i = 0; i<M; i++) {
			if(Narr[Marr[i]]) {
				result.append("1 ");
			} else {
				result.append("0 ");
			}
		}
	}
	private static void testOutput() {
		System.out.println(result.toString());
	}
}
