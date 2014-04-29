//============================================================================
// Name        : 004.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : C++ Experiments, for the brave.
//============================================================================

#include <iostream>
#include <string>
using namespace std;

typedef struct newStruct_s
{
	int test;
	string teste;
	newStruct_s * next;
} newStruct;

int main(int argc, const char* argv[])
{
	cout << "!!!Hello World!!!" << endl; // prints !!!Hello World!!!

	int x = 0, y = 0;
	int* z;
	newStruct* instance = (newStruct_s *) calloc(1, sizeof(newStruct));
	newStruct* myFirstStruct = (newStruct_s *) calloc(1, sizeof(newStruct));
	myFirstStruct->next = instance;

	z = (int*) calloc(20, sizeof(int));

	for (int i=0; i<10; i++)
	{
		x = 1;
		y = y + x;
		z[y] = y * y;
		instance->test = z[y] + instance->test;
		instance->next = new newStruct;
		instance->teste = "muh int is " + to_string(instance->test) + " and next ptr is " + to_string((long) instance->next);
		instance = instance->next;
	}

	instance=myFirstStruct->next;

	for (int i = 0; i < 20; i++)
	{
		cout << "z[i] = " << z[i];
		instance->teste != "" ? cout << " and " << instance->teste << endl : cout << endl;
		instance->next ? instance=instance->next : instance = new newStruct;
	}

	cout << "instance.test is " << instance->test << endl;

	return 0;
}
