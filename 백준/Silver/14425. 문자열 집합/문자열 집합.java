import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Set<String> S = new HashSet<>();
	static StringTokenizer st;
	static int result;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N; i++) {
			S.add(br.readLine());
		}
		for(int i = 0; i<M; i++) {
			if(S.contains(br.readLine()))
				result++;
		}
		System.out.println(result);
	}
}