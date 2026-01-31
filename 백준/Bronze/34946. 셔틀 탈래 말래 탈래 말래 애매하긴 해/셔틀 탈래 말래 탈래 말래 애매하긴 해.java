import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int A, B, C, D;
    static boolean shuttle, walk;
    //    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A  = Integer.parseInt(st.nextToken());
        B  = Integer.parseInt(st.nextToken());
        C  = Integer.parseInt(st.nextToken());
        D  = Integer.parseInt(st.nextToken());
        shuttle = A + B <= D;
        walk = C <= D;
        if(shuttle && walk) System.out.println("~.~");
        else if(!(shuttle || walk)) System.out.println("T.T");
        else System.out.println(shuttle ? "Shuttle" : "Walk");

    }

}
