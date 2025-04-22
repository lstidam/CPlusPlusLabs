#include <iostream>
#include <sstream>
#include <fstream>
#include <string>
#include <cmath>
#include <iomanip>
#include <algorithm> //std::sort


//declare all functions
void readData();
void indvAvg(int arr[], int, int, double averages[]);
void scoreDistribution(double averages[], int);
std::string singularOrPlural(int);
double classAverage(double averages[], int);
void standardDeviation(double averages[], int, double);
void medianScore(double averages[], int);


int main() {
	readData(); //execute readData function 
};

//function will read data and execute other functions 
void readData() {
	const int maxRows = 100; //max of 100 students
	double averages[maxRows] = {}; //array to store averages for each student 
	const int maxCols = 5; //max of 5 scores per student
	int scores_arr[maxRows][maxCols]; //initialize 2d array 
	int num_of_students = 0; //line in file reading (rows)
	int scores_count[maxRows] = {}; //number of scores per student
	double avg = 0.0; //var to store class average
	bool flag = false; //flag for if file can't be read

	//open file 
	std::ifstream file("scores.txt");
	if (!file) {
		std::cerr << "Unable to open file." << std::endl; //if file can not be opened, display error message
		flag = true;
	};

	// Check if the file is empty
	file.seekg(0, std::ios::end);  // Move to the end of the file
	if (file.tellg() == 0) {       // If the position is 0, the file is empty
		std::cerr << "File is empty." << std::endl;
		file.close();  // Close the file
		flag = true; //raise error flag 
	}
	file.seekg(0, std::ios::beg);  // Reset to the beginning of the file




	if (flag == false) { //if err was not encounted when opening file (flag has not been raised)
		std::string line;
		//read file line by line
		while (std::getline(file, line) && num_of_students < maxRows) {
			std::stringstream ss(line);
			std::string temp;
			int scores_per_student = 0; //value in current line (columns)

			//split line on commas
			while (std::getline(ss, temp, ',') && scores_per_student < maxCols) {
				scores_arr[num_of_students][scores_per_student] = std::stoi(temp); //convert to int
				scores_per_student++;//move to next score

			};
			scores_count[num_of_students] = scores_per_student; //store num of scores
			num_of_students++; //move to next row/student
		};
		file.close(); //close file



		//calculate indivual averages
		for (int i = 0; i < num_of_students; i++) { //loop through each row/student
			indvAvg(scores_arr[i], scores_count[i], i, averages); //execute function calculating indivual average for current student

		};

		std::cout << "\n";

		//execute score distribution function
		scoreDistribution(averages, num_of_students);

		std::cout << "\n";

		//execute class average function 
		avg = classAverage(averages, num_of_students);


		std::cout << "\n";

		//execute standard deviation function
		standardDeviation(averages, num_of_students, avg);

		std::cout << "\n";

		//execute median average score function
		medianScore(averages, num_of_students);

		std::cout << "\n";
	};
};

//calculate the individual average score for each student
void indvAvg(int arr[], int size, int count, double averages[]) { //count = to student number 
	//loop through and move lowest score to 1st position 
	int lowest_score = arr[0];
	for (int i = 1; i < size; i++) {
		if (arr[i] < lowest_score) {
			lowest_score = arr[i];
		};
	};
	int sum = 0;
	for (int i = 0; i < size; i++) {
		sum += arr[i];
	};
	sum = sum - lowest_score;
	double avg = (static_cast<double>(sum))/ (size-1); 
	averages[count] = avg; //store average at index 
	std::cout << "Student " << (count+1) << " average: " << std::fixed << std::setprecision(2) << avg << std::endl;
};

void scoreDistribution(double averages[], int num_of_students) {
	int r0_59 = 0;
	int r60_69 = 0;
	int r70_79 = 0;
	int r80_89 = 0;
	int r90_100 = 0;

	for (int i = 0; i < num_of_students; i++) {
		if (averages[i] >= 0 && averages[i] < 60) {
			r0_59 += 1;
		}
		else if (averages[i] < 70) {
			r60_69 += 1;
		}
		else if (averages[i] < 80) {
			r70_79 += 1;
		}
		else if (averages[i] < 90) {
			r80_89 += 1;
		}
		else {
			r90_100 += 1;
		};
	};

	std::cout << "Score Distribution: \n";
	std::cout << "0-59: " << r0_59<< " student"<<singularOrPlural(r0_59);
	std::cout << "\n60-69: " << r60_69 << " student" << singularOrPlural(r60_69);
	std::cout << "\n70-79: " << r70_79 << " student" << singularOrPlural(r70_79);
	std::cout << "\n80-89: " << r80_89 << " student" << singularOrPlural(r80_89);
	std::cout << "\n90-100: " << r90_100 << " student" << singularOrPlural(r90_100)<< std::endl;
};

double classAverage(double averages[], int num_of_students) {
	double class_total = 0.0;
	for (int i = 0; i < num_of_students; i++) {
		class_total += averages[i];
	};
	double class_average = class_total / num_of_students;
	std::cout << "Class average: " << std::fixed << std::setprecision(2) << class_average << std::endl;
	return class_average;
};

void standardDeviation(double averages[], int num_of_students, double avg ) {
	double mean = 0.0;
	double variance = 0.0;
	double deviation = 0.0;
	mean = avg;
	double sum_of_squared_dif = 0.0;
	for (int i = 0; i < num_of_students; i++) {
		sum_of_squared_dif += pow((averages[i] - mean),2);
	};
	variance = sum_of_squared_dif / (num_of_students);
	deviation = pow(variance, 0.5);
	std::cout << "Standard deviation: " << std::fixed << std::setprecision(2) <<deviation<<std::endl;
}

void medianScore(double averages[], int num_of_students) {
	// Sorting the array of averages in ascending order
	std::sort(averages, averages+ num_of_students);
	std::cout << "Median average score: ";
	//if size of array is odd, output middle element as median 
	if (num_of_students % 2 != 0) {
		std::cout << std::fixed<< std::setprecision(2) << averages[num_of_students / 2];
	}
	else {
		std::cout << std::fixed << std::setprecision(2) << (averages[num_of_students/2] + averages[num_of_students/2 - 1]) / 2;
	};
};



std::string singularOrPlural(int count) {
	if (count == 1) {
		return "";
	}
	else {
		return "s";
	};
};