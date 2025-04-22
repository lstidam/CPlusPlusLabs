#include <iostream>
#include <cmath>
#include <iomanip>

//declare isPrime and printDivisors funct
bool isPrime(int);
void printDivisors(int);


int main() {
	//set flag var
	bool flag = false;
	char stopChar = 'n';
	//initialize num var
	int num = 0;

	while (flag != true) { //while user has not enter escape char

		//get user input
		std::cout << "Enter a positive integer: ";
		std::cin >> num;

		//check to make sure num is above 1
		if (num <= 1) {
			std::cout << "Invalid input. Please enter a positive integer greater than 1.\n";
			//skip to next iteration of loop
			continue;
		};

		
		//if number is not prime
		if (isPrime(num) == true) {
			std::cout << num<<" is not prime.\n";
			//display divisors
			std::cout << "Divisors of " << num << " are: ";
			printDivisors(num);
			std::cout << "\n";
		}
		else { //if number is prime
			std::cout << num <<" is prime.\n";
		};

		//ask user if they want to loop through again 
		std::cout << "\nEnter y to continue. Enter n to stop.";
		std::cin >> stopChar;
		if (stopChar != 'y') { //requires use to enter y to continue, anything else stops 
			flag = true;
		};
	};
};

//function check if prime number, returns true/false
bool isPrime(int num) {
	double rootOfNum = sqrt(num);
	//iterates through numbers up to square root of number
	for (int i = 2; i <= rootOfNum; i++) {
		if (num % i == 0) {
			return true;
		};
	};
	return false;
};

//function print divisors in single line seperated by a space
void printDivisors(int num) {
	//iterates through 2 to num-1
	for (int i = 2; i < num; i++) {
		if (num % i == 0) {
			std::cout << i << " ";
		};
	};
};