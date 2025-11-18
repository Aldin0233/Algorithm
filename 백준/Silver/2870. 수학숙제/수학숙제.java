import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<String> list = new ArrayList<>();
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            StringBuilder num = new StringBuilder();
            for(int j = 0; j < s.length(); j++){
                if(s.charAt(j) >= '0' &&  s.charAt(j) <= '9'){
                    num.append(s.charAt(j));
                } else {
                    if(num.length() == 0) continue;
                    list.add(parseInt(num.toString()));
                    num = new StringBuilder();
                }
            }
            if(num.length() == 0) continue;
            list.add(parseInt(num.toString()));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) return o1.compareTo(o2);
                return o1.length() - o2.length();
            }
        });
        for(int i = 0; i < list.size(); i++){
            ans.append(list.get(i)).append('\n');
        }
        System.out.println(ans);
    }

    private static String parseInt(String raw) {
        int idx = 0;
        while (idx < raw.length() - 1 && raw.charAt(idx) == '0') idx++;
        return raw.substring(idx);
    }

}


