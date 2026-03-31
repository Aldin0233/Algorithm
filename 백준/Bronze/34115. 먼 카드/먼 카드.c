#include <stdio.h>
#include <stdlib.h>

static inline int max(int a, int b) {
    return a > b ? a : b;
}

int main() {
    int N;
    scanf("%d", &N);
    int* arr = calloc(N, sizeof(int));
    int curMax = 0;
    for(int i = 1; i <= N * 2; i++) {
        int tmp;
        scanf("%d", &tmp);
        if(arr[tmp - 1]) {
            curMax = max(curMax, i - arr[tmp - 1] - 1);
        } else {
            arr[tmp - 1] = i;
        }
    }

    printf("%d", curMax);
    free(arr);
    return 0;
}