import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

public class Tour {

	// Create a list to store all cities
	public static ArrayList ListOfCity = new ArrayList<City>();

	// Create a tour by storing all the city in a list
	public ArrayList tour = new ArrayList<City>();
	// Cache
	public double fitness = 0;
	public int distance = 0;

	public Tour() {
		for (int i = 0; i < ListOfCity.size(); i++) {
			tour.add(null);
		}
	}

	public Tour(ArrayList tour) {
		this.tour = tour;
	}

	// Generate initial solution
	public void initialSolution() {
		// Add the cities to the tour list while setting the index
		for (int i = 0; i < ListOfCity.size(); i++) {
			setCity(i, (City) ListOfCity.get(i));
		}
		// Randomly arrange the city in the list
		Collections.shuffle(tour);
	}

	// Get a city from the tour
	public City getCity(int tourPosition) {
		return (City) tour.get(tourPosition);
	}

	// Insert the city into the tour
	public void setCity(int tourPosition, City city) {
		tour.set(tourPosition, city);
		fitness = 0;
		distance = 0;
	}

	public double getFitness() {
		return (1 / getDistance());
	}

	// Get the total distance of the tour
	public int getDistance() {
		if (distance == 0) {
			// Temporary distance of the tour
			int temptDistance = 0;

			// Counter to count how many city was assigned as the current city

			int counter = 1;
			// Loop through the cities
			for (int i = 0; i < tour.size(); i++) {

				// Assign current city as the start point
				City start = getCity(i);

				// Create the end city
				City end;

				// If the counter is less than the total city then the end city
				// is the next one in list
				if (counter < tour.size()) {
					end = getCity(i + 1);
				}
				// If counter hits the number of city in total, then the current
				// city is the last city
				// Assign the first city in the list as the end city to
				// calculate distance
				else {
					end = getCity(0);
				}

				// Calculate the distance between start and end city
				temptDistance += start.distanceTo(end);
				// Increase the counter after each loop
				counter++;
			}
			distance = temptDistance;
		}
		return distance;
	}

	// Print the tour
	@Override
	public String toString() {
		// String Solution = "Total number of cities: " + tour.size() + "\n";

		// Print the solution to the console while total number of city is less
		// than 50
		if (tour.size() < 50) {
			String Solution = getDistance() + "\n";
			for (int i = 0; i < tour.size(); i++) {
				Solution += getCity(i) + "\n";
			}
			return Solution;
		}

		// When the total number of city is larger than 50
		// Write the solution to the Output text file instead of displaying them
		else {
			String Solution = getDistance() + "\n";
			for (int i = 0; i < tour.size(); i++) {
				Solution += getCity(i) + " " + "\n";
			}
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Output.txt")))) {
				writer.write(Solution);
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
			return "Result is too large to display\nStored in Output.txt";
		}

	}
}
