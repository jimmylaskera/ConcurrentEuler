import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class CachedSolution {
	/**
	 * Version that utilizes a cached thread pool to calculate the Euler number.
	 * Please inform at the command line the number of precision of decimal cases of the resulting number.
	 * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
		System.out.println("Concurrent calculation of an approximation for e\n");
		if (args.length != 1) {
			System.err.println("Invalid number of terms.");
			System.err.println("The program will be finished.");
			System.exit(1);
		}
		
		int precision = Integer.parseInt(args[0]);
        
		ExecutorService executor = Executors.newCachedThreadPool();
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
			ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
			System.out.println("There were " + pool.getActiveCount() + " active threads in the pool.");
			executor.shutdown();
		}

		long finish = System.nanoTime();
		long timeElapsed = finish - start;
		System.out.println("Elapsed time: " + (timeElapsed / 1000000) + " milliseconds");
		
	}
}
