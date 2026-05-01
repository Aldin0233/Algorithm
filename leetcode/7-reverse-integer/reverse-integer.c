int reverse(int x){
    long long num = x;
    long long result = 0;
    int symbol = x < 0 ? -1 : 1;
    num *= symbol;
    while(num > 0) {
        result *= 10;
        result += num % 10;
        num /= 10; 
    }
    result *= symbol;
    if(result < -(1LL << 31) || result > ((1LL << 31) - 1LL)) return 0; 
    return (int)result;
}