#include <stdio.h>
#include <stdlib.h>

int intCmp(const void *x, const void *y) {
    int a = *(const int *)x;
    int b = *(const int *)y;
    return a < b ? -1 : (a == b ? 0 : 1);
}

int main() {
    int N, K, C;
    scanf("%d %d %d", &N, &K, &C);

    int *arr = malloc(sizeof(int) * N);
    for(int i = 0; i < N; i++) {
        scanf("%d", &arr[i]);
    }
    qsort(arr, N, sizeof(int), intCmp);
    
    long long *prefixSum = calloc(N + 1, sizeof(long long));
    for(int i = 0 ; i < N; i++) {
        prefixSum[i + 1] = arr[i] + prefixSum[i];
    }
    
    int cnt = 0;
    for(int i = 1; i <= C; i++) {
        while(cnt < N && prefixSum[cnt + 1] <= i) cnt++;
        int take;
        if(N - cnt > K) {
            printf("%lld\n", prefixSum[K + cnt] - prefixSum[cnt]);
        } else {
            printf("%lld\n", prefixSum[N] - prefixSum[N - K]);
        }
        
    }

    free(arr);
    free(prefixSum);
    return 0;
}