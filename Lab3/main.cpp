
#include <iostream>
#include <iomanip>
#include <fstream> //for file reading
#include <sstream>
#include <string>
#include <vector>
#include "math_functions.h" //header file


void get_function(std::vector<int>& data, std::ofstream& outfile);


int main()
{
	bool flag = false; //set err flag to false

	//open input file 
	std::ifstream file("data.txt");
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
		std::vector<int> line_data; //create vector to hold line data

		//open output file
		std::ofstream outfile("results.txt");
		if (!outfile) {
			std::cerr << "Unable to open output file" << std::endl;
			return 1; //exit
		}

		//read file line by line
		while (std::getline(file, line)) {
			std::stringstream ss(line);
			std::string temp;

			//split line on commas
			while (std::getline(ss, temp, ',')) {
				line_data.push_back(std::stoi(temp)); //convert to int
			};
			
			get_function(line_data, outfile);//call get_function for each line 

			line_data.clear(); //clear vector so it can hold next line
		};
		file.close(); //close input file
		outfile.close(); //close output file


	};
};


void get_function(std::vector<int>& data, std::ofstream& outfile) {
	int operation = data[0]; //get the first number of the line 
	data.erase(data.begin());
	switch (operation) { //use switch to match the number to the wanted operation 
	case 1: //if first number is 1, call arithmetic mean function
		outfile << "Arithmetic Mean: " << arithmeticMean(data)<<std::endl; 
		break;
	case 2: 
		outfile<< "Geometric Mean: " << geometricMean(data) << std::endl;
		break;
	case 3: 
		outfile << "Harmonic Mean: " << harmonicMean(data) << std::endl;
		break;
	case 4:
		outfile << "Standard Deviation: " << standardDeviation(data) << std::endl;
		break;
	case 5: 
		outfile << "Median: " << median(data) << std::endl;
		break;
	case 6: 
		outfile << "Mode: " << mode(data)<< std::endl;
		break;
	case 7: 
		outfile << "Variance: " << variance(data) << std::endl;
		break;
	case 8: 
		outfile << "Maximum Number: " << maximumNumber(data) << std::endl;
		break;
	case 9:
		outfile << "Minimum Number: " << minimumNumber(data) << std::endl;
		break;
	default:
		outfile << "Invalid Operation Wanted" << std::endl;
	};
	
};