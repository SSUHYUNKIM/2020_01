#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <conio.h>

#define FALSE 0
#define TRUE 1
#define N 2

int turn;
int interested[N];

void EnterRegion(int process)
{
	_asm mfence;
	int other;

	other = 1 - process;
	interested[process] = TRUE;
	turn = process;
	while (turn == process && interested[other] == TRUE) {}
	_asm mfence;
}

void LeaveRegion(int process)
{
	_asm mfence;
	interested[process] = FALSE;
	_asm mfence;
}

DWORD WINAPI thread_func_1(LPVOID lpParam);
volatile double	shared_var = 0.0;
volatile int	job_complete[2] = { 0, 0 };


int main(void)
{
	DWORD dwThreadId_1, dwThrdParam_1 = 1;
	HANDLE hThread_1;
	int		i, j;

	// Create Thread 1
	hThread_1 = CreateThread(
		NULL,                        // default security attributes 
		0,                           // use default stack size  
		thread_func_1,                  // thread function 
		&dwThrdParam_1,                // argument to thread function 
		0,                           // use default creation flags 
		&dwThreadId_1);                // returns the thread identifier 

   // Check the return value for success. 

	if (hThread_1 == NULL)
	{
		printf("Thread 1 creation error\n");
		exit(0);
	}
	else
	{
		CloseHandle(hThread_1);
	}

	/* I am main thread */
	/* Now Main Thread and Thread 1 runs concurrently */

	for (i = 0; i < 10000; i++) {
		for (j = 0; j < 10000; j++) {
			EnterRegion(0);
			shared_var++;
			LeaveRegion(0);
		}
	}

	printf("Main Thread completed\n");
	job_complete[0] = 1;
	while (job_complete[1] == 0);

	printf("%f\n", shared_var);
	printf("이름: 김수현, 학번: %d", 2018920010);
	_getch();
	ExitProcess(0);
}


DWORD WINAPI thread_func_1(LPVOID lpParam)
{
	int		i, j;

	for (i = 0; i < 10000; i++) {
		for (j = 0; j < 10000; j++) {
			EnterRegion(0);
			shared_var++;
			LeaveRegion(1);
		}
	}
	printf("Thread_1 completed\n");
	job_complete[1] = 1;
	ExitThread(0);
}
