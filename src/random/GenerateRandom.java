package random;

/**
 * Class used to generate random values
 * @author Raphaël Baudry
 */
public class GenerateRandom {
	/**
	 * Cannot be instantiated
	 */
	private GenerateRandom() {
		
	}
	
	/**
	 * Getter of a Random Integer with a upper range
	 * @param upper : the upper range
	 * @return the random Integer
	 */
	public static int getRandom(int upper) {
		return (int)(1 + Math.random() * upper);
	}
	
	/**
	 * Getter of a Random Integer with a lower and an upper range
	 * @param lower : the lower range
	 * @param upper : the upper range
	 * @return the random Integer
	 */
	public static int getRandom(int lower, int upper) {
		return (int)(lower + Math.random() * upper);
	}
}
