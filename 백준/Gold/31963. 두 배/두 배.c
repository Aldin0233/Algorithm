/*
문제 요약과 기존 핵심 아이디어는 다음 원장 선생님 링크 참조
https://www.acmicpc.net/group/board/view/24712/43821

핵심 개념
두배를 했을 때 이진수에서는 자릿수가 한자리 늘어남 
[예시] (십진수) 1 * 10 = 10;
      (이진수) 1 * 2 = 10;

핵심 아이디어 1~5번 까지 동일

핵심 아이디어
1. 왼쪽부터 보면서, arr[i]가 arr[i-1]보다 작지 않게만 만들기
2. arr[i-1]=10, arr[i]=3이면, 2번 2배를 하여 12로 만들어야 함
3. 하지만 이걸 매번 while문으로 직접 곱하면 느림
4. 진짜로 값을 키우지말고, 몇 번 2배를 해야하는지 따로 기록
5. 그리고 arr[0], arr[1], arr[2]가 있을 때,
   원래 arr[1] <= arr[2] 였는데,
   arr[0] <= arr[1]을 만족하려고 arr[1] 키우다보니,
   arr[1] > arr[2]가 될 수 있음
6. 그래서 진짜로 값을 키우지 않고, 원래의 수열에 얼마나 두배를 했는지,
    따로 기록해서 들고 다니며 수의 크기를 비교할 때 사용하기로 함.
    [예시] arr[5] = {3, 1, 3, 1, 5};
        exp[5] = {0, 2, 1, 3, 1};
7. 새로 들어온 숫자를 비교할 때 직접 두배가 된 값을 비교하는게 아니라,
    자릿수로 수의 크기를 비교하는 것을 활용하듯,
    이진수의 길이를 활용하여 비교를 시작함
8. 만약 새로 들어온 수가 이진수 길이가 더 길다면 굳이 두배할 필요 없이 더 큼
9. 만약 서로의 길이가 같거나 작다면 앞 부분의 숫자만 비교하면 되니,
    원래 배열 숫자의 이진수 길이를 맞춰주기로 함(작은 쪽에만 두배)
    앞부분이 더 크다면 추가로 두배를 진행하여 오름차순이 되도록 진행함
10. 이렇게 하면 각 위치마다 앞에서 커진 영향을 별도로 계산해주기 때문에
    숫자가 너무 커지는 일을 바지하고 최소로 연산할 수 있음.
*/

#include <stdio.h>
#include <stdlib.h>

//bit 길이 계산하는 메서드 (십진수 자릿수 세는 거 떠올려 보기!)
static inline int bitlen(int x) {
    int len = 0;
    while (x > 0) {
        x >>= 1;
        len++;
    }
    return len;
}

//
int calcNeedExp(int prevNum, int prevExp, int curNum) {
    int prevNumBitLen = bitlen(prevNum);
    int curNumBitLen = bitlen(curNum);
    //비교할 필요 없이 무조건 큰 수
    if(curNumBitLen > prevNumBitLen + prevExp) {
        return 0;
    }
    //이진수 길이가 같거나 작다면 이진수 길이를 맞춰서 최소로 필요한 2배의 갯수 비교
    int needLeastExp = (prevNumBitLen + prevExp) - curNumBitLen;
    //이진수 길이를 맞춰준 후 앞부분 비교를 위해 길이를 맞춰줌
    if(prevNumBitLen > curNumBitLen) {
        curNum <<= (prevNumBitLen - curNumBitLen);
    } else {
        prevNum <<= (curNumBitLen - prevNumBitLen);
    }
    //길이를 맞춰준 앞부분 비교해서 추가로 2배가 필요하면 추가
    if(prevNum > curNum) {
        needLeastExp++;
    }
    //필요한 두배 수 반환
    return needLeastExp;
}

int main() {
    int N;
    scanf("%d", &N);
    long long ans = 0;
    //새로 들어오는 수
    int curNumber;
    //기존 수, 기존에 두배 한 횟수
    int prevNumber, prevExp; 
    scanf("%d", &curNumber);
    prevNumber = curNumber;
    prevExp = 0;
    for(int i = 1; i < N ; i++) {
        scanf("%d", &curNumber);
        int curNeedExp = calcNeedExp(prevNumber, prevExp, curNumber);
        ans += curNeedExp;
        prevNumber = curNumber;
        prevExp = curNeedExp;
    }
    printf("%lld", ans);
    return 0;
}