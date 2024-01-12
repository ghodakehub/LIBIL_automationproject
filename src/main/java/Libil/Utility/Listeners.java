package Libil.Utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {

	// Libil.Utility.Listeners
	ExtentTest test;
	ExtentReports extent = ExtentReportGenerator.generateReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "**" + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test case Pass");
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test case Failed");
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "TC skipped");
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
