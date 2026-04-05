#include <stdio.h>
#include <stdlib.h>

typedef struct Num {
    int odd;
    int exp;
} Num;

//bit 길이로 크기 비교하기
static inline int bitlen(int x) {
    int len = 0;
    while (x > 0) {
        x >>= 1;
        len++;
    }
    return len;
}

//곱하기는 2만 할 수 있으니까, 2를 따로 빼서 관리해보자.
static inline Num makeNum(int number) {
    Num res;
    res.exp = 0;
    res.odd = number;
    while(res.odd % 2 == 0) {
        res.odd /= 2;
        res.exp++;
    }
    return res;
}

static inline int lessAfterLevelMatch(int prevOdd, int curOdd, int diff) {
    //비트 맞춰졌을때 더 작으면 1 더할 수 있도록 0 혹은 1 반환
    if(diff < 0) {
        return curOdd < ((long long)prevOdd << (-diff));
    } else {
        return ((long long) curOdd << diff) < prevOdd;
    }
}

static inline int need(Num prev, Num cur) {
    int lprev = bitlen(prev.odd);
    int lcur = bitlen(cur.odd);
    int tlprev = lprev + prev.exp;
    int tlcur = lcur + cur.exp;
    if(tlprev < tlcur) {
        return 0;
    } 
    int defaultNeedDiff = tlprev - tlcur;
    //자리수 맞춰주기, 같을 경우 0;
    return defaultNeedDiff + 
    lessAfterLevelMatch(prev.odd, cur.odd, (cur.exp + defaultNeedDiff) - prev.exp);
}

int main() {
    int N;
    //기존에 학생이 시간 초과가 났던 이유 
    //들어오는 값들이 서로 커지는 소수라면 2배로 커지는 값이 기하급수적으로 늘어남 !
    
    scanf("%d", &N);
    long long ans = 0;
    int temp;
    Num prev, cur;
    scanf("%d", &temp);
    prev = makeNum(temp);
    for(int i = 1; i < N ; i++) {
        scanf("%d", &temp);
        cur = makeNum(temp);
        int needExp = need(prev, cur);
        cur.exp += needExp;
        ans += needExp;
        prev = cur;
    }

    printf("%lld", ans);
    return 0;
}