#include <stdio.h>
#include <stdlib.h>

#define MAX 10000

static inline int min(int a, int b) { return a < b ? a : b; }

int main () {
    int ansOdd = MAX;
    int ansSum = 0;
    int num;
    for(int i = 0; i < 7; i++) {
        scanf("%d", &num);
        if(num & 1) {
            ansOdd = min(ansOdd, num);
            ansSum += num;
        }
    }
    if(ansOdd == MAX) {
        printf("%d", -1);
    } else {
        printf("%d %d", ansSum, ansOdd);
    }
    return 0;
}

