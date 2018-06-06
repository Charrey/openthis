__kernel void sum(__global float* arr, int size) {
    
    // get ID of thread/work-item
    int index = get_global_id(0);
    
    // perform the computation
    for (int split = size/2; split > 0; split >>= 1) {
        float value = arr[index + split];
        barrier(CLK_GLOBAL_MEM_FENCE);
        
        if (index < split) arr[index] += value;
        barrier(CLK_GLOBAL_MEM_FENCE);
    }
}
