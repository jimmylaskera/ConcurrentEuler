import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedSolution {
	/**
	 * Version that utilizes a fixed thread pool to calculate the Euler number.
	 * Please inform at the command line the number of precision of decimal cases of the resulting number,
	 * as well as the number of threads that will be used to calculate the number.
	 * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
		System.out.println("Concurrent calculation of an approximation for e\n");
		if (args.length != 2) {
			System.err.println("Invalid number of terms.");
			System.err.println("The program will be finished.");
			System.exit(1);
		}
		
		int precision = Integer.parseInt(args[0]);
		int nThreads = Integer.parseInt(args[1]);
        
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		List<Future<BigDecimal>> results = new ArrayList<>();

		long start = System.nanoTime();
		System.out.println("Calculating the value of e with " + precision + " terms...");
		
		for (int i = 0; i < precision; i++) {
			Callable<BigDecimal> calculator = new TermCalculator(i);
			Future<BigDecimal> term = executor.submit(calculator);
			results.add(term);
		}

		List<BigDecimal> terms = new ArrayList<>();
		for (Future<BigDecimal> result: results) try {
			terms.add(result.get());
		} catch (InterruptedException | ExecutionException e1) {
			e1.getMessage();
		}

		SumAggregator aggregator = new SumAggregator(terms);
		Future<BigDecimal> e = executor.submit(aggregator);
		try {
			System.out.println(e.get());
		} catch (InterruptedException | ExecutionException e1) {
			e1.getMessage();
		} finally {
			executor.shutdown();
		}

		long finish = System.nanoTime();
		long timeElapsed = finish - start;
		System.out.println((timeElapsed / 1000000) + " milliseconds");
		
	}
}
