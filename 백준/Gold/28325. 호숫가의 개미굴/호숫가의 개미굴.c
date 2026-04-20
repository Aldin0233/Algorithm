#include <stdio.h>
#include <stdlib.h>

//28325 호숫가의 개미굴
//각 방에 대해서 쪽방이나 방 둘중 한 곳에는 무조건 개미가 산다
//만약 방에 개미가 살지 않으면 쪽방이 1 이상일 때 무조건 사는게 이득, 
//반대로 쪽방에 개미가 살지 않을 이유는 방에 개미가 있어서밖에 없다., 
//따라서 각 방에 대해서 쪽방에서 살지, 방에서 살지로 DP를 돌린다. 
//방끼리는 영향을 주지만 쪽방끼리는 영향을 주지 않는다.
//단 시작 점에 대해서 원형으로 연결되어 있는 것에 주의해야 한다.
//시작점에 방에 살고 있는지 여부가 반대쪽 끝 N-1 방에 영향을 준다. 따라서 두번 나눠서 검사

#define MAXN 250001
#define NEG -1000000000000000000LL

long long dp[MAXN][2];
long long ants[MAXN];

static inline long long max(long long a, long long b) {
    return a >= b ? a : b;
}

int main() {
    int N;
    scanf("%d", &N);
    for(int i = 0; i < N; i++) {
        scanf("%lld", &ants[i]);
    }
    dp[0][0] = 1LL;
    dp[0][1] = NEG;
    for(int i = 1; i < N; i++) {
        dp[i][0] = dp[i - 1][1] + 1LL;
        dp[i][1] = max(dp[i - 1][0] + ants[i], dp[i - 1][1] + ants[i]);
    }
    //초기 첫 방이 차있던 경우 쪽방에 차있는 경우만 가능
    long long MAX = dp[N - 1][1];
    dp[0][0] = NEG;
    dp[0][1] = ants[0];
    for(int i = 1; i < N; i++) {
        dp[i][0] = dp[i - 1][1] + 1LL;
        dp[i][1] = max(dp[i - 1][0] + ants[i], dp[i - 1][1] + ants[i]);
    }
    //초기 첫 방은 비고 쪽방이 차있다 가정한 경우 N-1의 경우의 수에 대한 가정 제한 없음
    MAX = max(MAX, max(dp[N - 1][0] , dp[N - 1][1]));
    printf("%lld", MAX);
    return 0;
}

