#include <kernels.h>
#include <stdio.h>
#include <omp.h>

void impl_matrix_multiply_openmp( float* A
                                , float* B
                                , float* C
                                , unsigned int Am
                                , unsigned int An
                                , unsigned int Bn
                                ) {
    unsigned int i, j, k;

    int chunk = Am / 16;
    chunk = chunk == 0 ? 1 : chunk;

    #pragma omp parallel shared(A,B,C,chunk) private(i,j,k)
    {
        #pragma omp for schedule (static, chunk)
        for(i=0; i < Am; ++i) {
            for(j=0; j < Bn; ++j) {
                C[i * Bn + j] = 0;
                for(k = 0; k < An; ++k) {
                    C[i * Bn + j] += A[i * An + k] * B[k * Bn + j];
                }
            }
        }
    }
}

void impl_matrix_countzero_openmp( float* M
                                 , unsigned int Mm
                                 , unsigned int Mn
                                 , unsigned int* Z
                                 ) {

    unsigned int z = 0;

    #pragma omp parallel for reduction(+:z)
    for(unsigned int i=0; i < Mm*Mn; ++i) {
        if(M[i] == 0.0f) z++;
    }

    *Z = z;
}

#define swap(a,b) do { \
  float tmp = a; \
  a = b; \
  b = tmp; \
} while(0)

void impl_vector_sort_openmp( float* V
                            , unsigned int Vn
                            ) {

    int notSorted = 1;
    while(notSorted) {
        notSorted = 0;

        #pragma omp parallel for reduction(+:notSorted)
        for(unsigned int i = 1; i < Vn-1; i += 2) {
            if(V[i] > V[i+1]) {
                swap(V[i], V[i+1]);
                notSorted = 1;
            }
        }

        #pragma omp parallel for reduction(+:notSorted)
        for(unsigned int i = 0; i < Vn-1; i += 2) {
            if(V[i] > V[i+1]) {
                swap(V[i], V[i+1]);
                notSorted = 1;
            }
        }
    }

}

