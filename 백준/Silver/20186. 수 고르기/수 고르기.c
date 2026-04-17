#include <stdio.h>
#include <stdlib.h>

//20186 수 고르기
//왼쪽에 있는 수 중 선택된 수의 갯수를 뺀 값은 항상 같다
//0 ~ K - 1의 합
//즉 우리는 선택된 수의 합만 최대로 하면 된다.
//최대 누적합은 5억이니 int 사용 가능

int intCompare(const void *a, const void *b) {
    int x = *(const int*)a;
    int y = *(const int*)b;
    return y - x;
}

static inline int calcZeroToK(int K) {
    return K * (K - 1) / 2;
}

int main() {
    int N, K;
    scanf("%d %d", &N, &K);
    int maxScore = 0;
    int arr[N];
    for(int i = 0; i < N; i++) {
        scanf("%d", &arr[i]);
    }

    qsort(arr, N, sizeof(int), intCompare);

    for(int i = 0; i < K; i++) {
        maxScore += arr[i];
    }
    int zeroToK = calcZeroToK(K);
    maxScore -= zeroToK;
    printf("%d", maxScore);
    return 0;
}
