#include <stdio.h>
#include <time.h>
#include <sys/wait.h>

#define N 200000

int calcPrimes() {
	int i, j, amount;
	
	amount = 0;
	
	for (i = 1; i <= N; i++) {
		amount++;
		for (j = 2; j < i; j++) {
			if (i % j == 0) {
				amount--;
				break;
			}
		}
	}
	
	return amount;
}


int main() {
	time_t start, end;
	pid_t pid_1, pid_2, pid_3, w_1, w_2, w_3;
	int status_1, status_2, status_3;

	start = time(NULL);
	printf("Calculated %d primes.\n", calcPrimes());
	end = time(NULL);
	printf("The machine calculated all prime numbers under %d 1 time in %d seconds.\n", N, end - start);


	start = time(NULL);
	
	pid_1 = fork();
	if (pid_1 == 0) {
		printf("Calculated %d primes.\n", calcPrimes());
		exit(0);
	} else {
		pid_2 = fork();
		if (pid_2 == 0) {
			printf("Calculated %d primes.\n", calcPrimes());
			exit(0);
		}
	}

	w_1 = waitpid(pid_1, &status_1, WUNTRACED | WCONTINUED);
	w_2 = waitpid(pid_2, &status_2, WUNTRACED | WCONTINUED);

	end = time(NULL);
	printf("The machine calculated all prime numbers under %d 2 times in %d seconds.\n", N, end - start);


	start = time(NULL);

	pid_1 = fork();
	if (pid_1 == 0) {
		printf("Calculated %d primes.\n", calcPrimes());
		exit(0);
	} else {
		pid_2 = fork();
		if (pid_2 == 0) {
			printf("Calculated %d primes.\n", calcPrimes());
			exit(0);
		} else {
			pid_3 = fork();
			if (pid_3 == 0) {
				printf("Calculated %d primes.\n", calcPrimes());
				exit(0);
			}
		}
	}

	w_1 = waitpid(pid_1, &status_1, WUNTRACED | WCONTINUED);
	w_2 = waitpid(pid_2, &status_2, WUNTRACED | WCONTINUED);
	w_3 = waitpid(pid_3, &status_3, WUNTRACED | WCONTINUED);

	end = time(NULL);
	printf("The machine calculated all prime numbers under %d 3 times in %d seconds.\n", N, end - start);

	return 0;
}
