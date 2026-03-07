import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int A, B, C, D;
    static int P, M, N;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ans.append(attack(P)).append("\n");
        ans.append(attack(M)).append("\n");
        ans.append(attack(N)).append("\n");
        System.out.println(ans);
    }

    static int attack(int time) {
        int attackDog = 0;
        int oneDogCycle = A + B;
        int oneDogRemain = time % oneDogCycle;
        if(oneDogRemain > 0 && oneDogRemain <= A) {
            attackDog++;
        }
        int otherDogCycle = C + D;
        int otherDogRemain = time % otherDogCycle;
        if(otherDogRemain > 0 && otherDogRemain <= C) {
            attackDog++;
        }
        return attackDog;
    }


}

