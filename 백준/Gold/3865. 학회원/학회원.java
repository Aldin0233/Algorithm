import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Institute firstInstitute;
    static Map<String, Institute> map = new HashMap<>();
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while(N != 0) {
            map.clear();
            firstInstitute = parseInstitute(br.readLine());
            map.put(firstInstitute.name, firstInstitute);
            for(int i = 1; i < N; i++) {
                Institute inst = parseInstitute(br.readLine());
                map.put(inst.name, inst);
            }
            ans.append(firstInstitute.getMemberSet().size()).append("\n");
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(ans);
    }

    static Institute parseInstitute(String line) {
        StringTokenizer st = new StringTokenizer(line, ":");
        String institute = st.nextToken();
        String list = st.nextToken();
        st = new StringTokenizer(list, ",");
        List<String> memberAndInstitutes = new ArrayList<>();
        while (st.hasMoreTokens()) {
            memberAndInstitutes.add(st.nextToken());
        }
        //마지막에 . 제거
        String lastStr = memberAndInstitutes.get(memberAndInstitutes.size() - 1);
        lastStr = lastStr.substring(0, lastStr.length() - 1);
        memberAndInstitutes.set(memberAndInstitutes.size() - 1, lastStr);
        return new Institute(institute, memberAndInstitutes);
    }

    static class Institute {
        String name;
        boolean visited;
        List<String> memberAndInstituteList;
        Set<String> memberSet;
        public Institute(String name, List<String> memberAndInstituteList) {
            this.name = name;
            this.visited = false;
            this.memberAndInstituteList = memberAndInstituteList;
            this.memberSet = new HashSet<>();
        }

        Set<String> check() {
            for(String s : memberAndInstituteList) {
                if(map.containsKey(s)) {
                    Set<String> otherInstituteMember = map.get(s).getMemberSet();
                    memberSet.addAll(otherInstituteMember);
                } else {
                    memberSet.add(s);
                }
            }
            return memberSet;
        }

        Set<String> getMemberSet() {
            if(visited) return this.memberSet;
            visited = true;
            return check();
        }

    }

}
