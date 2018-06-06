//  CP block7 coins

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#ifdef __APPLE__
#  include "OpenCL/opencl.h"
#else
#  include "CL/cl.h"
#endif

#define NR_COINS 6
#define AMOUNT 9
#define SOLUTION_SIZE ((NR_COINS + 1) * (AMOUNT + 1))

char* storeInMemory(char *filename, size_t *size) {
    // open program file
    FILE *f = fopen(filename, "r");
    
    if (!f) {
        fprintf(stderr, "Could not find the kernel file\n");
        exit(EXIT_FAILURE);
    }
    
    // find the size of the program
    fseek(f, 0, SEEK_END);
    size_t program_size = ftell(f);
    rewind(f);
    
    // read program file, place content into the buffer
    char *buffer = (char*)malloc(program_size + 1);
    *size = fread(buffer, 1, program_size, f);
    fclose(f);
    
    return buffer;
}

int main(int argc, const char * argv[]) {    
    int err;
    
    // get platform information
    cl_platform_id platform;
    err = clGetPlatformIDs(1, &platform, NULL);
    
    // get device information
    cl_device_id device;
    err = clGetDeviceIDs(platform, CL_DEVICE_TYPE_GPU, 1, &device, NULL);
    
    // create an OpenCL context
    cl_context context = clCreateContext(NULL, 1, &device, NULL, NULL, &err);
    
    // create a command queue for our device
    cl_command_queue queue = clCreateCommandQueue(context, device, 0, &err);
    
    // copy the program in memory
    size_t programsize;
    char *source = storeInMemory("coins.cl", &programsize);
    
    // create the kernel program from source
    cl_program program = clCreateProgramWithSource(context, 1, (const char **)&source, (const size_t *)&programsize, &err);
    
    // build the kernel program (for the device)
    err = clBuildProgram(program, 1, &device, NULL, NULL, NULL);
    
    // create the kernel (named 'hello')
    cl_kernel kernel = clCreateKernel(program, "vectoradd", &err);
    
    // create the input and output arrays in device memory
    cl_mem buffer_coins = clCreateBuffer(context, CL_MEM_READ_ONLY, NR_COINS * sizeof(int), NULL, &err);
    cl_mem buffer_solution = clCreateBuffer(context, CL_MEM_READ_WRITE, SOLUTION_SIZE * sizeof(int), NULL, &err);
    
    // set the kernel parameters
    int nr_coins = NR_COINS + 1;
    int amount = AMOUNT + 1;
    err = clSetKernelArg(kernel, 0, sizeof(cl_mem), &buffer_coins);
    err |= clSetKernelArg(kernel, 1, sizeof(cl_mem), &buffer_solution);
    err |= clSetKernelArg(kernel, 2, sizeof(int), &nr_coins);
    err |= clSetKernelArg(kernel, 3, sizeof(int), &amount);
    
    // create our data set
    int coins[NR_COINS] = { 1, 1, 2, 2, 3, 5 };
    int solution[SOLUTION_SIZE];
    memset(solution, 0, SOLUTION_SIZE * sizeof(int));
    
    // write our data set into the input array on device memory
    err = clEnqueueWriteBuffer(queue, buffer_coins, CL_TRUE, 0, NR_COINS * sizeof(int), coins, 0, NULL, NULL);
    
    // execute the kernel over the entire data set
    size_t global_size = AMOUNT + 1;
    err = clEnqueueNDRangeKernel(queue, kernel, 1, NULL, &global_size, NULL, 0, NULL, NULL);
    
    // copy the result from the memory buffer
    err = clEnqueueReadBuffer(queue, buffer_solution, CL_TRUE, 0, SOLUTION_SIZE * sizeof(int), solution, 0, NULL, NULL);
    
    // output the resulting vector
    printf("printing result: \n");
    for (int x = 0; x < AMOUNT + 1; x++) {
        for (int y = 0; y < NR_COINS + 1; y++) {
            printf("solution[%i,%i] = %i\n", x, y, solution[y * (AMOUNT + 1) + x]);
        }
    }
    
    return 0;
}
