/**
 * The Country class stores information about
 * a country and provides methods to
 * get the data, set the data, and compare
 * countries by several fields.
 * 
 * @author Lucas Rendell
 * @version 9/11/2020
 */
public class Country {

	private String name;
	private String capitol;
	private int population;
	private String GDP;
	private int cases;
	private int deaths;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCapitol() {
		return capitol;
	}
	
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public String getGDP() {
		return GDP;
	}
	
	public void setGDP(String gDP) {
		GDP = gDP;
	}
	
	public int getCases() {
		return cases;
	}
	
	public void setCases(int cases) {
		this.cases = cases;
	}
	
	public int getDeaths() {
		return deaths;
	}
	
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	/**
	 * Compare two countries' names alphabetically.
	 * 
	 * @param country
	 * @return int between 0-2
	 */
	public int compare(Country country) {
		if (this.name.compareTo(country.name) > 0)
			return 2;
		else if (this.name.compareTo(country.name) < 0)
			return 1;
		else
			return 0;
	}
	
	/**
	 * Prints out a report for a single country.
	 * 
	 * @return void
	 */
	public void print() {
		System.out.println("Name:             " + this.name);
		System.out.println("Capitol:          " + this.capitol);
		System.out.println("Population:       " + this.population);
		System.out.println("GDP:              " + this.GDP);
		System.out.println("COVID-19 Cases:   " + this.cases);
		System.out.println("COVID-19 Deaths:  " + this.deaths);
	}
	
	
}
