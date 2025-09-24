
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, Q;
    static Map<String, Folder> folders;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        folders = new HashMap<>();
        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            folders.putIfAbsent(parent, new Folder());
            String child = st.nextToken();
            boolean isFolder = Integer.parseInt(st.nextToken()) == 1;
            if(isFolder) {
                folders.putIfAbsent(child, new Folder());
                folders.get(parent).addChild(folders.get(child));
            } else {
                folders.get(parent).file.addFile(child);
            }
        }
        Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), "/");
            String now = null;
            while(st.hasMoreTokens()) {
                now = st.nextToken();
            }
            ans.append(folders.get(now).checkFolder()).append("\n");
        }

        System.out.println(ans);
    }

}

class Folder {
    List<Folder> children;
    File file;
    boolean isDfsCheck = false;

    Folder() {
        children = new ArrayList<>();
        file = new File();
    }

    void addChild(Folder child) {
        children.add(child);
    }

    File checkFolder() {
        if(isDfsCheck) {
            return file;
        }
        isDfsCheck = true;
        for(Folder child : children) {
            File childFile = child.checkFolder();
            file.files.addAll(childFile.files);
            file.totalFileCnt += childFile.totalFileCnt;
        }
        return file;
    }


}

class File {
    Set<String> files = new HashSet<>();
    int totalFileCnt = 0;

    void addFile(String fileName) {
        files.add(fileName);
        totalFileCnt++;
    }

    @Override
    public String toString() {
        return files.size() + " " + totalFileCnt;
    }

}









