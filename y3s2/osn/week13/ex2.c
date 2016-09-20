#include <stdio.h>
#include <time.h>

#define N 1000000

int main() {
	clock_t start_t, end_t;
	int a[N], i;
	int *b;
	
	for (i = 0; i < N; i++) a[i] = i;
	
	start_t = clock();
	
	b = (int *)malloc(sizeof(a));
	memcpy(b, a, sizeof(a));
	
	end_t = clock();
	
	printf("It took %d clocks to memcpy an array of length %d\n", end_t - start_t, N);

	free(b);

	return 0;
}
