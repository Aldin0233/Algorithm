import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        String[] arr = new String[2];
        arr[0] = br.readLine();
        arr[1] = br.readLine();
        int count = 0;
        int last = -1; // 0은 0행, 1은 1행
        int serial = 0;
        for(int i = 0 ; i < M; i++) {
            if(arr[0].charAt(i) == arr[1].charAt(i)) {
                // 둘다 하얀색임 (경로가 존재하지 않는 경우는 없다)
                serial++;
            } else if(arr[0].charAt(i) == '.'){
                if(last == -1 || last == 0) {
                    count += serial;
                } else {
                    count += (serial -1);
                }
                serial = 0;
                last = 0;
            } else {
                if(last == -1 || last == 1) {
                    count += serial;
                } else {
                    count += (serial -1);
                }
                serial = 0;
                last = 1;
            }
        }
        count += serial;
        System.out.println(count);

    }

}