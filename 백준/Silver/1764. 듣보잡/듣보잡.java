
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static StringBuilder sb= new StringBuilder();
	static String tmp;
	static String[] Narr, Marr, resultarr;
	public static void main(String[] args) throws IOException {
		test();
	}
	private static void test() throws IOException {
		testInput();
		testProcess();
		testOutput();
	}
	private static void testInput() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Narr = new String[N]; Marr = new String[M];
		for(int i = 0 ; i< N; i++) {
			Narr[i] = br.readLine();
		}
		for(int i = 0 ; i < M; i++) {
			Marr[i] = br.readLine();
		}
		resultarr = new String[Math.min(N, M)];
	}
	private static void testProcess() throws IOException {
		int a = 0, b = 0, idx = 0;
		Arrays.sort(Narr);
		Arrays.sort(Marr);
		while(a<N && b<M) {
			if(Narr[a].equals(Marr[b])) {
				resultarr[idx++] = Narr[a];
				a++;
				b++;
			} else if(Narr[a].compareTo(Marr[b])>0){
				b++;
			} else {
				a++;
			}
		}
		
		if(idx>0) {			
			sb.append(idx+"\n");
			for(int i = 0 ; i<idx; i++) {
				sb.append(resultarr[i]+"\n");
			}
		} else {
			sb.append(0);			
		}
	}
	
	private static void testOutput() {
		System.out.println(sb.toString());
	}
}
