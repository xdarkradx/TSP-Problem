public class Population {

	// Array to store all the tours
	Tour[] tours;

	// Construct a population
	public Population(int populationSize, boolean initialise) {
		tours = new Tour[populationSize];
		// If we need to initialize a population of tours do so
		if (initialise == true) {
			// Loop through the population number
			for (int i = 0; i < tours.length; i++) {
				Tour newTour = new Tour();

				// Create new tour with randomly shuffle cities
				newTour.initialSolution();

				// Add the tour to the population
				addTour(i, newTour);
			}
		}
	}

	// Add the new tour to population
	public void addTour(int index, Tour tour) {
		tours[index] = tour;
	}

	// Get a tour from population
	public Tour getTour(int index) {
		return tours[index];
	}

	// Find the fittest tour
	public Tour getFittest() {
		// Assign the fittest tour to the first one
		Tour fittest = tours[0];
		// Loop through all the tours
		for (int i = 1; i < tours.length; i++) {
			// If the fitness of the fittest is lest than the current one
			// Assign the current one as the fittest
			if (fittest.getFitness() <= getTour(i).getFitness()) {
				fittest = getTour(i);
			}
		}
		return fittest;
	}

}
