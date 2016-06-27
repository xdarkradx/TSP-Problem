
import java.util.LinkedList;
import java.util.Queue;

public class GeneticAlgorithm {

	public static double mutationRate = 0.3;

	// Evolves a population over one generation
	public static Population newGeneration(Population pop) {

		// Create new population for the next generation
		Population newPopulation = new Population(pop.tours.length, false);

		// Add the fittest tour from the previous population
		// at location 0
		newPopulation.addTour(0, pop.getFittest());

		// Loop through current population to find parent and create a new child
		// to add to the new population
		for (int i = 1; i < newPopulation.tours.length; i++) {

			// Pick random parent by using the index
			int randomParent1 = (int) (Math.random() * pop.tours.length);
			int randomParent2 = (int) (Math.random() * pop.tours.length);
			Tour parent1 = pop.getTour(randomParent1);
			Tour parent2 = pop.getTour(randomParent2);

			// Generate crossover children
			Tour child = crossover(parent1, parent2);

			// Add child to new population
			// Only add one child at a time until the population is full
			newPopulation.addTour(i, child);
		}

		// Mutate the tour in the population
		for (int i = 1; i < newPopulation.tours.length; i++) {
			mutate(newPopulation.getTour(i));
		}

		return newPopulation;
	}

	public static Tour crossover(Tour parent1, Tour parent2) {
		// Create new child tour
		Tour child = new Tour();

		// Queue store the elements in crossover region
		Queue<City> inCrossoverRegion = new LinkedList<City>();

		// Queue for the non crossover region elements
		Queue<City> queue1 = new LinkedList<City>();

		// Generate random crossover points
		int endPosition = (int) (Math.random() * parent1.tour.size());

		// Add crossover region of parent 2 to queue
		for (int i = 0; i < endPosition; i++) {
			inCrossoverRegion.add(parent2.getCity(i));
		}

		// Add elements that not in the crossover section to the queue
		for (int i = 0; i < parent1.tour.size(); i++) {
			if (!inCrossoverRegion.contains(parent1.getCity(i))) {
				queue1.add(parent1.getCity(i));
			}
		}

		// Add the cities to child
		for (int i = 0; i < parent1.tour.size(); i++) {
			// Add the crossover section to the child first
			if (i < endPosition) {
				child.setCity(i, inCrossoverRegion.poll());
			} else {
				// Then add the other cities that not in the crossover section
				child.setCity(i, queue1.poll());
			}
		}

		return child;
	}

	// Mutate a tour
	private static void mutate(Tour tour) {

		// Generate random number to determine whether the tour should mutate
		double randomMutation = (double) Math.random();

		// If the random number is less than the rate of mutation then the tour
		// mutates
		if (randomMutation < mutationRate) {
			// Get index of random city in the tour
			int randomCity1 = (int) (Math.random() * tour.tour.size());
			int randomCity2 = (int) (Math.random() * tour.tour.size());

			// Pick the cities
			City city1 = tour.getCity(randomCity1);
			City city2 = tour.getCity(randomCity2);

			// Swap the two cities together
			tour.setCity(randomCity2, city1);
			tour.setCity(randomCity1, city2);

		}
	}// End mutate

}
