#include <stdio.h>
#include <stdlib.h>


int main () {
    int N;
    scanf("%d", &N);
    long long arr[N];
    for(int i = 0; i < N; i++) {
        scanf("%lld", &arr[i]);
    }
    long long sum = 1;
    long long curSpeed = 1;
    for(int i = N - 2; i >= 0; i--) {
        curSpeed = (arr[i] <= curSpeed) ? arr[i] : curSpeed + 1;
        sum += curSpeed;
    }
    printf("%lld", sum);
    return 0;
}

