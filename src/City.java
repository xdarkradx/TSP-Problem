public class City {
	double x;
	double y;
	int id;

	// Construct a city
	public City(int id, double x, double y) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	// Get X coordinate
	public double getX() {
		return this.x;
	}

	// Get Y coordinate
	public double getY() {
		return this.y;
	}

	// Get City ID
	public int getID() {
		return this.id;
	}

	// Calculate the distance to next city
	public double distanceTo(City city) {
		int distance = (int) Math.round(Math.sqrt(
				(getX() - city.getX()) * (getX() - city.getX()) + (getY() - city.getY()) * (getY() - city.getY())));

		return distance;
	}

	@Override
	public String toString() {
		// return getX() + ", " + getY();
		return Integer.toString(getID());
	}
}
