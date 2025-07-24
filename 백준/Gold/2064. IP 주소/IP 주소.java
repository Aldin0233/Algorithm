import java.io.*;

public class Main {

    static int N;
    static long[] ipAddress;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ipAddress = new long[N];
        long minIp = Long.MAX_VALUE;
        long maxIp = Long.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            ipAddress[i] = ipAddressToLong(br.readLine());
            minIp = Math.min(minIp, ipAddress[i]);
            maxIp = Math.max(maxIp, ipAddress[i]);
        }

        long diff = maxIp ^ minIp; //가장 작은 값과 큰 값 xor
        int prefixLength = Long.numberOfLeadingZeros(diff) - 32; //long은 64비트

        long mask = (-1L << (32 - prefixLength)) & 0xFFFFFFFFL; //32비트로 서브넷 마스크 생성
        long networkAddress = minIp & mask; //현재 갖고 있는 IP 주소 중에 공통 부분 추출
        ans.append(longToIpAddress(networkAddress))
                .append("\n")
                .append(longToIpAddress(mask));

        System.out.println(ans);
    }

    private static long ipAddressToLong(String ip) {
        String[] parts = ip.split("\\.");
        long result = 0;
        for (int i = 0; i < 4; i++) {
            result <<= 8; //기존 8비트 왼쪽으로 밈
            result |= Integer.parseInt(parts[i]); //8비트 단위로 10진수 연산
        }
        return result;
    }

    private static String longToIpAddress(long ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.insert(0, ip & 0xFF); //255와 비트 연산
            if(i < 3) {
                sb.insert(0, '.');
            }
            ip >>= 8;
        }
        return sb.toString();
    }


}




