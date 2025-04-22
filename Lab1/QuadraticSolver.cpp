#include <iostream>
#include <cmath>
#include <iomanip>

int main() {
    // initialize values 
    int a = 0;
    int b = 0;
    int c = 0;
    bool flag = false; //flag for stopping after solution 
    char stopChar = 'n'; //input var for flag

    while (flag != true) { //while user has not entered stop character 
        //ask for inputs
        std::cout << "Enter the value of a: ";
        std::cin >> a;
        std::cout << "Enter the value of b: ";
        std::cin >> b;
        std::cout << "Enter the value of c: ";
        std::cin >> c;

        //validate a is not 0 
        if (a == 0) {
            std::cout << "This is not a quadratic equation. 'a' must be non-zero. \n";
            continue; //skip to next iteration, start loop over 
        };

        //initialize discriminant function
        double discriminant(double a, double b, double c);
        //set var discrim to return of discriminant function + execute function
        double discrim = discriminant(a, b, c);

        //initialize roots function
        void findRoots(double a, double b, double c, double discrim);

        //execute function to find roots
        findRoots(a, b, c, discrim);

        //ask user if they want to continue
        std::cout << "Enter y to continue, enter n to stop: ";
        std::cin >> stopChar;
        //if user does not enter yes, program stops
        if (stopChar == 'y') {
            flag = false;
        }
        else {
            flag = true;
        };
    };


    


};

//discriminant function
double discriminant(double a, double b, double c) {
    double discrim = (b * b) - (4 * a * c);
    return discrim; //returns var to be used later 
};


//finding roots function, uses var from discriminant function
void findRoots(double a, double b, double c, double discrim) {
    if (discrim > 0) {
        double roots1 = (-b - sqrt(discrim)) / (2 * a);
        double roots2 = (-b + sqrt(discrim)) / (2 * a);
        std::cout << "The roots of the equation are: " << std::setprecision(2) << roots1 << " and " << std::setprecision(2) << roots2 <<"\n";
    }
    else if (discrim == 0) {
        double roots1 = (-b / (2 * a));
        std::cout << "The root of the equation is: " << std::setprecision(2) << roots1<<"\n";
    }
    else {
        double real = -b / (2 * a);
        double imaginary = sqrt(-discrim) / (2 * a);
        std::cout << "The roots of the equation are: " << std::setprecision(2) << real << "+" << std::setprecision(2) << imaginary << "i and " << std::setprecision(2) << real << "-" << std::setprecision(2) << imaginary << "i\n";

    };
}