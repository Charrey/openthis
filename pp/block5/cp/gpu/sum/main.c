//  CP block7 sum

#include <stdio.h>
#include <stdlib.h>
#ifdef __APPLE__
#  include "OpenCL/opencl.h"
#else
#  include "CL/cl.h"
#endif

#define VECTOR_SIZE 16384

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
    cl_context context =
        clCreateContext(NULL, 1, &device, NULL, NULL, &err);
    
    // create a command queue for our device
    cl_command_queue queue =
        clCreateCommandQueue(context, device, 0, &err);
    
    // copy the program in memory
    size_t programsize;
    char *source = storeInMemory("sum.cl", &programsize);
    
    // create the kernel program from source
    cl_program program = clCreateProgramWithSource(context, 1, 
        (const char **)&source, (const size_t *)&programsize, &err);
    
    // build the kernel program (for the device)
    err = clBuildProgram(program, 1, &device, NULL, NULL, NULL);
    
    // create the kernel (named 'hello')
    cl_kernel kernel = clCreateKernel(program, "sum", &err);
    
    // create the input and output arrays in device memory
    cl_mem buffer = clCreateBuffer(context, CL_MEM_READ_WRITE, 
        VECTOR_SIZE * sizeof(float), NULL, &err);
    
    // set the kernel parameters
    int vectorsize = VECTOR_SIZE;
    err = clSetKernelArg(kernel, 0, sizeof(cl_mem), &buffer);
    err |= clSetKernelArg(kernel, 1, sizeof(int), &vectorsize);
    
    // create our data set
    float array[VECTOR_SIZE];
    printf("input array: \n");
    float sum = 0.0f;
    for (int i = 0; i < VECTOR_SIZE; i++) {
        array[i] = 1.0f * (i + 1);
		sum += array[i];
//        printf("%1.0f \n", array[i]);
    }
    printf("\nsum = %f\n", sum);
    
    // write our data set into the input array on device memory
    err = clEnqueueWriteBuffer(queue, buffer, CL_TRUE, 0, 
        VECTOR_SIZE * sizeof(float), array, 0, NULL, NULL);
    
    // execute the kernel over the entire data set
    size_t global_size = VECTOR_SIZE / 2; // total nr. of work items
    err = clEnqueueNDRangeKernel(queue, kernel, 1, NULL, &global_size, 
        NULL, 0, NULL, NULL);
    
    // copy the result from the memory buffer
    err = clEnqueueReadBuffer(queue, buffer, CL_TRUE, 0, 
        VECTOR_SIZE * sizeof(float), array, 0, NULL, NULL);
    
    // output the resulting vector
    printf("printing result: \n");
//    for (i = 0; i < VECTOR_SIZE; i++) {
        printf("%f \n", array[0]);
//    }
    printf("\n");
    
    return 0;
}
