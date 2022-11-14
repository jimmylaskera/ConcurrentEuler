import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Execution of the Euler's number formula
 * @author Gerson David da Silva Santos
 */
public class SumAggregator implements Callable<BigDecimal> {
	/** List of terms of the Euler's number formula */
    private List<BigDecimal> termos;
	
	/**
     * Parametrized constructor for the class
	 * @param termos List of terms of the Euler's number formula
	 */
	public SumAggregator(List<BigDecimal> termos) {
		this.termos = termos;
	}

    /* (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     * @return The resulting Euler number
     */
    @Override
    public BigDecimal call() throws Exception {
        BigDecimal soma = BigDecimal.valueOf(0.0);
        System.out.println("Soma:");
		for (int i = 0; i < termos.size(); i++) {
			soma = soma.add(termos.get(i));
		}
		return soma;
    }
}