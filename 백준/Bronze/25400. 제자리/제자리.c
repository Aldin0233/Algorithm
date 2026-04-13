#include <stdio.h>
#include <stdlib.h>

//25400 제자리
//X번째부터 순서를 제자리를 찾으려면, 1부터 1부터 증가하는 자연수 오름차순을 가져야 한다. 
//따라서 가장 긴 1에서 시작하는 1 증가 오름차순을 찾도록 하겠다. 그외의 카드는 모두 제거되어야 한다.
//카드를 제거할때 카드를 제거하는 순서나 혹은 몇번째 있는지에 따라 추가적으로 필요한 건 없다.

int countMatchedFromOne(int N, int A[N]);

int main() {
    int N;
    scanf("%d", &N);
    int A[N];
    for(int i = 0; i < N; i++) {
        scanf("%d", &A[i]);
    }
    int maxLen = countMatchedFromOne(N, A);
    int needRemoveCards = N - maxLen;
    printf("%d", needRemoveCards);
    return 0;
}

int countMatchedFromOne(int N, int A[N]) {
    int expect = 1;
    for(int i = 0; i < N; i++) {
        if(A[i] == expect) expect++;
    }
    return expect - 1; //1부터 시작
}

