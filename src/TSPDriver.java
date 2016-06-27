import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TSPDriver {
	public static void main(String[] args) throws IOException {

		// Default value for number of generations
		int numberOfGeneration = 50;
		// Default value for file name
		String filename = "dj38.tsp";

		// Pass the argument from command line
		try {
			filename = args[0];
			numberOfGeneration = Integer.parseInt(args[1]);
		} catch (Exception e) {

		}

		// Read the file name from the command line
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = br.readLine();

			// Loop through the input
			while ((line != null)) {
				// If line starts with Comment
				// Move to next line
				if (line.contains("COMMENT")) {
					line = br.readLine();
				}
				// If line starts with Name
				// Move to next line
				else if (line.contains("NAME")) {
					line = br.readLine();
				}
				// If sentence starts with Type
				// Move to next line
				else if (line.contains("TYPE")) {
					line = br.readLine();
				}
				// If line starts with Dimension
				// Move to next line
				else if (line.contains("DIMENSION")) {
					line = br.readLine();
				}
				// If line starts with Edge type
				// Move to next line
				else if (line.contains("EDGE_WEIGHT_TYPE")) {
					line = br.readLine();
				}
				// If line starts with Node
				// Move to next line
				else if (line.contains("NODE_COORD_SECTION")) {
					line = br.readLine();
				}
				// If the data has EOF line at the end
				// Move to next line
				else if (line.contains("EOF")) {
					line = br.readLine();
				}
				// If line have the x and y coordinate of the city
				else {
					// Split and store the values into an array
					String[] details = line.split("\\s");
					int id = Integer.parseInt(details[0]);
					double xCoor = Double.parseDouble(details[1]);
					double yCoor = Double.parseDouble(details[2]);
					// Create new city with the value from input
					City city = new City(id, xCoor, yCoor);
					Tour.ListOfCity.add(city);
					// Move to next line
					line = br.readLine();
				}
			} // End while loop for parsing input
		}

		// Initialize a new population
		Population pop = new Population(50, true);

		// Create a new population
		pop = GeneticAlgorithm.newGeneration(pop);

		// Evolve the population
		for (int i = 0; i < numberOfGeneration; i++) {
			pop = GeneticAlgorithm.newGeneration(pop);
		}

		// Display the result
		System.out.println(pop.getFittest());
	}

}
