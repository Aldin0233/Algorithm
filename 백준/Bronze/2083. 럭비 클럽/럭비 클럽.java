import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //2453
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ClubMember cm = new ClubMember(new StringTokenizer(br.readLine()));
        while(!cm.isEnd()) {
            ans.append(cm).append("\n");
            cm = new ClubMember(new StringTokenizer(br.readLine()));
        }
        System.out.println(ans);
    }

    static class ClubMember {
        String name;
        int age;
        int weight;

        ClubMember(StringTokenizer st) {
            name = st.nextToken();
            age = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
        }

        @Override
        public String toString() {
            return name + " " + (isJunior() ? "Junior" : "Senior");
        }

        boolean isJunior() {
            return age <= 17 && weight < 80;
        }

        boolean isEnd() {
            return name.equals("#") && age == 0 && weight == 0;
        }
    }

}




