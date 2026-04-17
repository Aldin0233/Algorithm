#include <stdio.h>
#include <stdlib.h>

//21760 야구 시즌
//N * M 개의 팀
//같은 리그와 각각 A번의 경기, 다른 리그 팀과 각각 B번의 경기
//이때 A는 k * B를 만족해야 한다.
//중복 경기 제거
//각 리그에서 -> 같은 지역 리그 내 타 팀들에 대해서 -> A번의 경기
//A번의 총 경기 수 N * (M * (M - 1)) * A / 2 
//각 리그에서 -> 각 팀이 -> 타 리그의 각 팀에 대해서 -> B번의 경기
//B번의 총 경기 수 N * M * ((N - 1) * M) * B / 2;
//전체 경기 수가 D개 이하이면서 D에 가장 가까운 경기 수

long long calcLocalMatchesPlayed(int N, int M, long long A);

long long calcOtherMatchesPlayed(int N, int M, long long B);

long long calcMatchesPlayed(int N, int M, int K, long long bCandi);

static inline long long max(long long A, long long B) {
    return A > B ? A : B;
}

int main() {
    int T;
    int N, M, K;
    long long D;
    scanf("%d", &T);
    for(int t = 1; t <= T; t++) {
        scanf("%d %d %d %lld", &N, &M, &K, &D);
        long long maxMatches = -1L;
        int l = 1; //최소 1경기 이상
        int r = D;
        while(l < r) {
            int mid = (l + r) >> 1;
            long long totalExpectedMatches = calcMatchesPlayed(N, M, K, mid);
            if(totalExpectedMatches <= D) {
                maxMatches = max(maxMatches, totalExpectedMatches);
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        printf("%lld\n", maxMatches);
    }
    return 0;
}

//같은 지역 리그 내 경기 수
long long calcLocalMatchesPlayed(int N, int M, long long A) {
    return N * (M * (M - 1)) * A / 2;
}

//타 지역 리그 간 경기 수
long long calcOtherMatchesPlayed(int N, int M, long long B) {
    return N * M * ((N - 1) * M) * B / 2;
}

//총 경기 수
long long calcMatchesPlayed(int N, int M, int K, long long bCandi) {
    long long aCandi = bCandi * K;
    return calcLocalMatchesPlayed(N, M, aCandi) 
        + calcOtherMatchesPlayed(N, M, bCandi);
}