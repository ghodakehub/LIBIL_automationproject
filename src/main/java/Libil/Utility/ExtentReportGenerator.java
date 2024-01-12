package Libil.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportGenerator {

	public static ExtentReports generateReport() {
		String reportPath = "C:\\Users\\vaibh\\eclipse-workspace\\LibilWeb\\TestReports\\LibiTestCasesRun.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("Libil Web Application");
		reporter.config().setReportName("Libil Test Case Report");
		reporter.config().setTheme(Theme.DARK);

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Windows Operating System", "Libil Web Application");
		extent.setSystemInfo("QA", "Vaibhav Joshi");
		return extent;
	}
}
