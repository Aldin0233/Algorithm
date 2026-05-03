int myAtoi(char* s) {
    long long result = 0LL;
    int idx = 0;
    //처음 문자열 공백이나, 빈문자열인지 확인
    while(s[idx] != '\0' && s[idx] == ' ') {
        idx++;
    }
    int isMinus = 1;
    //첫 기호 확인
    if(s[idx] == '+' || s[idx] == '-') {
        if(s[idx] == '-') isMinus = -1;
        idx++;
    }
    //인티저 범위까지만 처리함
    long long INTMAX = (1LL << 31) - 1;
    long long INTMIN = -(1LL << 31);
    //나머지 문자열에 대해서
    for(int i = idx; s[i] != '\0'; i++) {
        if(s[i] >= '0' && s[i] <= '9') {
            result *= 10;
            result += (s[i] - '0');
            //INTMAX보다 커지면 바로 브레이크
            if(result > INTMAX) break;
        } else {
            break;
        }
    }
    result *= isMinus;
    if(result > INTMAX) return (int)INTMAX;
    else if(result < INTMIN) return (int)INTMIN;
    return (int) result;
}