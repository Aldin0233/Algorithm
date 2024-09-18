import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, result;
	static Set<String> set = new HashSet<>();
	static String chat;
	static boolean checkOn;
	public static void main(String[] args) throws IOException {
		test();
	}
	private static void test() throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i<N;i++) {
			chat = br.readLine();
			if(chat.equals("ENTER")) {
				result += set.size();
				set.clear();
			} else {
				set.add(chat);
			}
		}
		result += set.size();
		System.out.println(result);
	}
	
}