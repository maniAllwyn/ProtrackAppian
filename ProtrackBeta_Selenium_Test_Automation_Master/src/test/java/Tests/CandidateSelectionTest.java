package Tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CandidateSelectionObjects;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import reusableComponents.ExcelOperations;
import testBase.TestBase;

public class CandidateSelectionTest extends TestBase {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	CandidateSelectionObjects candidate = new CandidateSelectionObjects();
	ExcelOperations excel = new ExcelOperations("sheet2");

	@Test(dataProvider = "CandidateData")
	public void Candidate_creation(Object obj1) throws Throwable {
		// @SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		System.out.println("Test data is" + testData);
		login.hrManagerLogin();
		Thread.sleep(10000);
		home.clickOnCandidateSelection();
		candidate.createCandidate(testData);
		Assert.assertTrue(candidate.error(testData.get("Error Message")));

	}

	// Data Provider
	@DataProvider(name = "CandidateData")
	public Object[][] testDataSupplier() throws Exception {
		Object[][] obj = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i - 1][0] = testData;
		}
		return obj;
	}
}
