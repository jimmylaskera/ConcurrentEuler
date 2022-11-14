import java.math.BigDecimal;
import java.util.concurrent.Callable;

/**
 * Concurrent calculation of Euler's number formula terms, within a thread pool
 * @author Gerson David da Silva Santos
 */
public class TermCalculator implements Callable<BigDecimal> {
	/** Term to be calculated */
	private int i;

    /**
	 * Parametrized constructor for the class
     * @param i Term to be calculated
     */
    public TermCalculator(int i) {
		this.i = i;
	}

	/**
	 * @param number The number to be calculated
	 * @return The factorial to he given number
	 */
	private double factorial(int number) {
		if (number <= 1) {
			return 1;
		} else {
			return number * factorial(number-1);
		}
	}

    /* (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
	 * @return One term of the calculation of the Euler number
     */
    @Override
    public BigDecimal call() throws Exception {
		BigDecimal result = BigDecimal.valueOf(1 / factorial(i));
        return result;
    }
}
