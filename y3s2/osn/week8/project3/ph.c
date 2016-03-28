#include <stdio.h>
#include <pthread.h>
#include <stdbool.h>

#define PH_COUNT 5
#define MAX_SLEEP 10

bool chopsticks[PH_COUNT];

void *philosopher(void *arg) {
	int *num=(int*)arg;
	printf("Hello from %i thread\n", num);
}

void take_fork(int index) {
}

void put_fork(int index) {
}

void think(int ph_num) {
	printf("%i is not thinking\n", ph_num);
	sleep(rand() % MAX_SLEEP);
	printf("%i is done thinking\n", ph_num);
}

void eat(int ph_num) {
	printf("%i is not eating\n", ph_num);
	sleep(rand() % MAX_SLEEP);
	printf("%i is done eating\n", ph_num);
}



int main() {
	pthread_t pth;
	int i;
	
	for (i = 0; i < PH_COUNT; i++) {
		pthread_create(&pth, NULL, philosopher, i);
	}
	
	for (i = 0; i < PH_COUNT; i++) {
		pthread_join(pth, NULL);
	}
	
	return 0;
}
