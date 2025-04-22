
#ifndef math_functions_h

#define math_functions_h

#include <vector> //for std::vector 

//all functions in math_functions.cpp
double arithmeticMean(const std::vector<int>& numbers);
double geometricMean(const std::vector<int>& numbers);
double harmonicMean(const std::vector<int>& numbers);
double standardDeviation(const std::vector<int>& numbers);
double variance(const std::vector<int>& numbers);
double median(std::vector<int> numbers);
int mode(const std::vector<int>& numbers);
int maximumNumber(const std::vector<int>& numbers);
int minimumNumber(const std::vector<int>& numbers);




#endif
