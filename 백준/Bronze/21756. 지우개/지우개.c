#include <stdio.h>
#include <stdlib.h>

//21756 지우개
//처음 수에서 지워지는 수를 보면 항상 있는 수 중 홀수 번째가 지워진다. 
//즉 마지막에 남는 하나의 수는 2의 거듭제곱 중 하나다.
//그렇다면 N 이하에서 가장 큰 2의 거듭제곱이 가장 뒤에 있는 2의 거듭제곱이기 때문에 마지막에 남는다.

int main() {
    int N;
    scanf("%d", &N);
    int powerOfTwo = 1;
    while(powerOfTwo <= N / 2) {
        powerOfTwo *= 2;
    }
    printf("%d", powerOfTwo);
    return 0;
}
