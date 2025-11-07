import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static Set<String> otherAnimalsSound;
    static String forestSound;
    static final String animalSoundInputEnd = "what does the fox say?";
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            forestSound = br.readLine();
            otherAnimalsSound = new HashSet<>();
            String animal = br.readLine();
            do {
                StringTokenizer st = new StringTokenizer(animal);
                st.nextToken(); st.nextToken();
                otherAnimalsSound.add(st.nextToken());
                animal = br.readLine();
            } while(!animal.equals(animalSoundInputEnd));
            StringTokenizer st = new StringTokenizer(forestSound);
            while(st.hasMoreTokens()) {
                String sound = st.nextToken();
                if(!otherAnimalsSound.contains(sound)) {
                    ans.append(sound).append(" ");
                }
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }

}