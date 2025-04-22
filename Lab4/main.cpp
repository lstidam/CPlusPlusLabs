#include <iostream>
#include <limits>  // For std::numeric_limits
#include <string> // for string
#include <exception>

using namespace std;


//creating custom errors 
class DivideByZero : public runtime_error { //thrown when trying to divide by 0
public:
	DivideByZero() : runtime_error("Error: Division by zero is not allowed.") {}
};

class NotAnInt : public runtime_error { //thrown when user inters something besides an int
public:
	NotAnInt() : runtime_error("Error: Input was not an integer. Please enter valid integer(s).") {}
};


//function to find the value of an element at index
int find_elem(int a, int arr[], int size) {
	if (a >= 0 && a < size) {
		return arr[a];
	}
	else { //throws exception is index is not in range
		throw "Index is out of range.";
	}
}



int main() {
	//division
	int x;
	int y;
	bool flag = false;

	// Loop until valid integers are provided
	while (!flag) {
		cout << "Enter two integers separated by a space: ";
		try {
			if (cin >> x >> y) { // Check if both inputs are valid integers
				if (y == 0) { // Check for division by zero
					throw DivideByZero();
				}
				else {
					// output result
					cout << x << " divided by " << y << " is: " << static_cast<double>(x) / y << endl;
					flag = true; // exit loop
				}
			}
			else {
				// throws err for invalid input
				throw NotAnInt();
			}
		}
		catch (const DivideByZero& e) { //catch divide by zero exception
			cerr << e.what() << endl;
			cin.clear(); // Clear the input error flag
			cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Discard invalid input
		}
		catch (const NotAnInt& e) { //catch thrown exception
			cerr << e.what() << endl;
			cin.clear(); // Clear the input error flag
			cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Discard invalid input
		}
		catch (const exception& e) { //if another err is thrown
			cerr << "An error occurred: " << e.what() << endl;
		}
	}
	cout << endl;
	//array
	int a;
	int z;
	bool arr_flag = false;
	int arr[5] = { 1,2,3,4,5 };
	while (!arr_flag) { //loop until valid index is entered
		cout << "Enter an index to access array (0-4): ";
		try {
			if (cin >> a) { // Check if input is valid int
				try {
					z = find_elem(a, arr, 5); //calls function
					cout << "The element at index "<< a<< " is: "<<z << endl;
					arr_flag = true;
				}
				catch (const char* msg) { //catches exc thrown in function
						cerr << msg << endl;
				}
	
			}
			else {
				// Handle invalid input
				throw NotAnInt();
			}
		}
		catch (const NotAnInt& e) {
			cerr << e.what() << endl;
			cin.clear(); // Clear the input error flag
			cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Discard invalid input
		}
		catch (const exception& e) { //if another err is thrown
			cerr << "An error occurred: " << e.what() << endl;
		}
	}
	cout << endl;

	//string
	string s;
	float value;
	bool float_flag = false;
	while (!float_flag) { //loop until input is valid
		try {
			cout << "Enter a floating point number: ";
			cin >> s;
			value = stof(s); //try and convert from string to float
			cout << "You entered: "<<value<<endl;
			float_flag = true;
		}
		catch (const invalid_argument& e) { //if invalid argument is thrown
			cerr << "Error: Invalid input - "<<e.what()<<". Please enter a floating - point number." << endl;
		}
		catch (const exception& e) { //if another err is thrown
			cerr << "An error occurred: " << e.what() << endl;
		}
	}

	cout << endl;





}