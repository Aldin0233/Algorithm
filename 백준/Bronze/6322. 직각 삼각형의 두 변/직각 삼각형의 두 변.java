import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int a, b, c;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;
        newTriangle(new StringTokenizer(br.readLine()));
        while(check()) {
            ans.append(String.format("Triangle #%d\n", tc++));
            calSide();
            newTriangle(new StringTokenizer(br.readLine()));
        }
        ans.setLength(ans.length()-2);
        System.out.println(ans);
    }

    private static void calSide() {
        if(c != -1 && (c <= a || c <= b)) {
            ans.append("Impossible.\n\n");
            return;
        }
        if(a == -1) {
            ans.append(String.format("a = %.3f\n\n", sub(c, b)));
        } else if(b == -1) {
            ans.append(String.format("b = %.3f\n\n", sub(c, a)));
        } else if(c == -1) {
            ans.append(String.format("c = %.3f\n\n", add()));
        }
    }

    private static double add() {
        return Math.sqrt(a * a + b * b);
    }

    private static double sub(int c, int other) {
        return Math.sqrt(c * c - other * other);
    }

    private static void newTriangle(StringTokenizer st) {
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    private static boolean check() {
        return !(a == 0 && b == 0 && c == 0);
    }


}




