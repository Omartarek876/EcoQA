package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retries a failed test until it reaches the maximum number of attempts.
 * The maximum is read from the maxTestAttempts property (execution.properties,
 * overridable with -DmaxTestAttempts) and defaults to 2 attempts in total.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount;

    @Override
    public boolean retry(ITestResult result) {
        int maxAttempts = Integer.parseInt(System.getProperty("maxTestAttempts", "2"));
        if (retryCount < maxAttempts - 1) {
            retryCount++;
            return true;
        }
        return false;
    }
}
