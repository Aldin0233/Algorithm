#include <stdio.h>
#include <stdlib.h>

//19939 박 터트리기
//바구니마다 모두 다르게
//가장 적게와 가장 많이 담긴 바구니의 공의 개수 차이 계산 
// -> 최대한 골고루 가급적 +1씩 차이 나게
//N이 1~K의 합 이상인지 계산, 안되면 나눠담을 수 없음

int main() {
    int N, K;
    scanf("%d %d", &N, &K);
    int minimumNeedBall = K * (K + 1) / 2;
    int remain = N - minimumNeedBall;
    if(remain < 0) {
        printf("-1");
        return 0;
    }
    if(remain % K == 0) printf("%d", K - 1);
    else printf("%d", K);
    return 0;
}
