# TSP-Problem

Solves TSP problems with genetic algorithm

Test data can be obtained here: http://www.math.uwaterloo.ca/tsp/data/index.html

The usage for the program should be like:

java TSPDriver [name of the data file] [number of generation]

For example: if we want to solve the "zi929.tsp" TSP problem with 100 generations, the command should be:

java TSPDriver zi929.tsp 100

However, if these parameters are not specified, the program will run with the default value. The default file name is "dj38.tsp" and the default number of generation is 50. So the program can be run just by:

java TSPDriver

with the "dj38.tsp" file is in the same folder as the class file.

The program can also be used with the data file with large number of cities. The output will be written to a text file named "Output.txt" if the number of city is higher than 100 cities instead of displaying it in the console.

