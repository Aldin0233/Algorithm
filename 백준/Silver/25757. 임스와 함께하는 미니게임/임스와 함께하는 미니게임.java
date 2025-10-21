import java.io.*;
import java.util.*;

public class Main {

    static final int Y = 2, F = 3, O = 4;
    static int N;
    static char game;
    static Set<String> name;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        game = st.nextToken().charAt(0);
        name = new HashSet<>();
        for (int i = 0; i < N; i++) {
            name.add(br.readLine());
        }
        //플레이 요구자 중 임스를 제외하고 판당 필요한 인원 수
        System.out.println(name.size() / (getGamePlayers(game) - 1));

    }

    private static int getGamePlayers(char c) {
        if (c == 'Y') {
            return Y;
        } else if (c == 'F') {
            return F;
        } else if (c == 'O') {
            return O;
        }
        return 0;
    }
}