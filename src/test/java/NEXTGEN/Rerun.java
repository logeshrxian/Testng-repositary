package NEXTGEN;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Rerun implements IRetryAnalyzer{
	int retryCount=0;
	int maxretryCount=1;
	

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount<maxretryCount) {
			retryCount++;
			System.out.println("retrying failed test:"+result.getName());
			return true;

}
		return false;
	}
}
