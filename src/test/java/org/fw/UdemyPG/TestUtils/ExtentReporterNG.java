package org.fw.UdemyPG.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG {
	
	
	static ExtentReports extent;
	
	public static ExtentReports getReporterObject() {
		
		// System.getProperty("user.dir"): this will give the default project path till ExtentReports.
		String reportPath = System.getProperty("user.dir")+"\\reports\\index.html";  
		// ExtentSparkReporter is responsible for creating the report file in the desired directory "reportPath". also will do all configurations for the report.
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		// Setting report configurations
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//ExtentReports class is responsible for creating and consolidating all test executions.
		extent = new ExtentReports();
		// Attach created report with ExtentReports.
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "PG");   // if tester name need to be setup in the report.
		
		return extent;
	}
}
