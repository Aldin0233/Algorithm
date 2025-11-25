import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int G, P;
    static int[] gates;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        ans = 0;
        makeGates();
        for (int i = 0; i < P; i++) {
            int airplaneOrder = Integer.parseInt(br.readLine());
            int gate = findCanUse(airplaneOrder);
            if(gate > 0) {
                ans++;
            } else {
                break;
            }
        }
        System.out.println(ans);
    }

    private static int findCanUse(int n) {
        if(n <= 0) return -1;
        if(n == gates[n]) {
            gates[n]--;
            return n;
        }
        return gates[n] = findCanUse(gates[n]);
    }

    private static void makeGates() {
        gates = new int[G + 1];
        for(int i = 1; i <= G; i++) {
            gates[i] = i;
        }
    }


}

