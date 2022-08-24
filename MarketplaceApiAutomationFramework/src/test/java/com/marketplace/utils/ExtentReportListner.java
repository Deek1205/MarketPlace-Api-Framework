package com.marketplace.utils;

import java.io.File;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.androjava.extent_report.ExtentManager;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListner implements ITestListener {
	protected static ExtentReports reports;
	protected static ExtentTest test;
	public ExtentHtmlReporter htmlReporter;

	private static String resultpath = getResultPath();

	public static void deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i].getName());
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
	}

	private static String getResultPath() {

		resultpath = "test";// new SimpleDateFormat("yyyy-MM-dd hh-mm.ss").format(new Date());
		if (!new File(resultpath).isDirectory()) {
			new File(resultpath);
		}
		return resultpath;
	}

	String ReportLocation = "test-output/Report/" + resultpath + "/";

	public void onTestStart(ITestResult result) {

		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName());
		System.out.println(result.getTestClass().getTestName());
		System.out.println(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "Test is pass");

	}

	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.FAIL, "Test is fail");
		test.log(LogStatus.FAIL, result.getThrowable());

	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Test is skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		System.out.println(ReportLocation + "  ReportLocation");

		reports = new ExtentReports(ReportLocation + "MarketPlace.html");

		reports.addSystemInfo("Operating System", "Windows 11");
		reports.addSystemInfo("Tested By", "Deeksha Prajapati");
		reports.addSystemInfo("Browser:", "chrome");

		test = reports.startTest("Market Place Api Test Cases");

	}

	public void onFinish(ITestContext context) {
		reports.endTest(test);
		reports.flush();

	}

}
