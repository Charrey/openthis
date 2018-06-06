
int minExceptMinusOne(int a, int b) {
    if (a == -1) return b;
    else if (b == -1) return a;
    return a < b ? a : b;
}

int toindex(int x, int y, int amount) {
    return y * amount + x;
}

__kernel void vectoradd(__global const int* coins,
                        __global int* solution,
                        int nr_coins, int amount) {
    
    // get ID of thread/work-item
    int index = get_global_id(0);
    
    // initialiseer de eerste rij van de matrix
    solution[toindex(index, 0, amount)] = index == 0 ? 0 : -1;
    
    for (int i = 1; i < nr_coins; i++) {
        barrier(CLK_GLOBAL_MEM_FENCE);
        
        int coin = coins[i - 1];
        int result = 0;
        
        if (coin > index) {
            // not possible to use this coin
            result = solution[toindex(index, i - 1, amount)];
        } else {
            int useThisCoin = 
                solution[toindex(index - coin, i - 1, amount)] == -1 ? -1 :
                    solution[toindex(index - coin, i - 1, amount)] + 1;
            int dontUseThisCoin = solution[toindex(index, i - 1, amount)];
            result = minExceptMinusOne(useThisCoin, dontUseThisCoin);
        }
        
        solution[toindex(index, i, amount)] = result;
    }
}
