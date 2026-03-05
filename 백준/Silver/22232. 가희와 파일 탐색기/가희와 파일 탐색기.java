import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static Set<String> osExtension;
    static File[] files;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        files = new File[N];
        for (int i = 0; i < N; i++) {
            files[i] = new File(new StringTokenizer(br.readLine(), "."));
        }
        osExtension = new HashSet<>();
        for(int i = 0; i < M; i++) {
            osExtension.add(br.readLine());
        }
        for(int i = 0; i < N; i++) {
            files[i].setCheckExtension();
        }
        Arrays.sort(files);
        for(int i = 0; i < N; i++) {
            ans.append(files[i]).append("\n");
        }
        System.out.println(ans);
    }

    static class File implements Comparable<File> {
        String fileName;
        String extension;
        boolean extensionExists;

        File(String fileName, String extension) {
            this.fileName = fileName;
            this.extension = extension;
        }

        File(StringTokenizer st) {
            fileName = st.nextToken();
            extension = st.nextToken();
        }

        void setCheckExtension() {
            this.extensionExists = osExtension.contains(extension);
        }

        public int compareTo(File o) {
            if(fileName.compareTo(o.fileName) == 0) {
                if(extensionExists ^ o.extensionExists) {
                    return Boolean.compare(o.extensionExists, extensionExists);
                } else {
                    return extension.compareTo(o.extension);
                }
            }
            return fileName.compareTo(o.fileName);
        }

        @Override
        public String toString() {
            return fileName + "." + extension;
        }


    }
}

