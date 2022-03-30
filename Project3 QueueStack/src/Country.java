/**
 * The Country class stores information about
 * a country and provides methods to
 * get the data, set the data, and compare
 * countries by several fields.
 * 
 * @author Lucas Rendell
 * @version 10/25/2020
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
	}//end getName
	
	public void setName(String name) {
		this.name = name;
	}//end setName
	
	public String getCapitol() {
		return capitol;
	}//end getCapitol
	
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}//end setCapitol
	
	public int getPopulation() {
		return population;
	}//end getPopulation
	
	public void setPopulation(int population) {
		this.population = population;
	}//end setPopulation
	
	public String getGDP() {
		return GDP;
	}//end getGDP
	
	public void setGDP(String gDP) {
		GDP = gDP;
	}//end setGDP
	
	public int getCases() {
		return cases;
	}//end getCases
	
	public void setCases(int cases) {
		this.cases = cases;
	}//end setCases
	
	public int getDeaths() {
		return deaths;
	}//end getDeaths
	
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}//end setDeaths
	
	public double getCFR() {
		return (double)this.deaths/(double)this.cases;
	}//end getCFR

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
	}//end compared
}
