package Tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ATSCandidatePageObjects;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import reusableComponents.JsonOperations;
import testBase.TestBase;

public class ATS_CandidatesTest extends TestBase {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	ATSCandidatePageObjects candidate = new ATSCandidatePageObjects();
	JsonOperations jo;
	
	@Test
	public void createCandidate() {
		login.amManagerLogin();  	//login as Account Manager
		home.openATS();				//Open ATS
		home.clickOnMenu("Candidates"); //Open Candidates Page
		candidate.clickAddCandidate();  //Open New candidate page
		candidate.createCandidate();
		
	}
	@Test(enabled = false)
	public void updateCandidate() {
		login.amManagerLogin();  	//login as Account Manager
		home.openATS();	
		home.clickOnMenu("Candidates"); 
		candidate.updateCandidate();
	}
	
	@Test(enabled = false)
	public void linkJobTOCandidate() {
		jo = new JsonOperations("C:\\Users\\Mani\\eclipse-workspace\\Advanced_Selenium_Test_Automation_Framework-master.zip_expanded\\Advanced_Selenium_Test_Automation_Framework-master\\src\\test\\resources\\json\\ATScandidate.json");
 System.out.println("test");
		login.amManagerLogin();  	//login as Account Manager
		home.openATS();	
		home.clickOnMenu("Candidates"); 
		candidate.linkJobToCandidate(jo.get("LinkCandidate"), jo.get("LinkJob"));
		boolean flag = candidate.verifyLinkedJob(jo.get("LinkCandidate"), jo.get("LinkJob"));
		Assert.assertTrue(flag);
	}
	
}
