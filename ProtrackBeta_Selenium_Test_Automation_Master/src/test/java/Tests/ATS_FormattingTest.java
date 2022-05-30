package Tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import pageObjects.ATSCandidatePageObjects;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.DriverFactory;
import testBase.TestBase;

public class ATS_FormattingTest extends TestBase{
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	ATSCandidatePageObjects candidate = new ATSCandidatePageObjects();
	
	@Test
	public void sendForFormatting() throws InterruptedException {
		String candidateName = "Suman K";
		login.amManagerLogin();				//login as Account Manager
		home.openATS();						//open HRIS
		home.clickOnMenu("Candidates");	
		candidate.searchRecord(candidateName);
		candidate.clickOnAction("Send Formatting Request");
		selectDropdownByText("Unformatted Documents","Suman_K_35_43 - 6511");
		selectDropdownByText("Send To","Recruiter Protrack");
		clickOnButton("Submit");
		Thread.sleep(3000);
		login.logout();
		//login as formatter and submit the formatted resume
		login.recruiterLogin();				//login as formatter
		home.openATS();	
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//strong[contains(text(),'My Tasks')]")), "Home My Tasks");
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//a[contains(text(),'"+candidateName+"')]")), "Home My Tasks");
		//click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//button[contains(text(),'Upload') and @class='Button---btn Button---default_direction Button---secondary Button---small Button---minimize_width Button---inGridLayout']")), "Upload documents");
		candidate.attachResume();
		
		
		selectDropdownByTextDiv("Document Type","Formatted resume");
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//button[contains(text(),'Upload') and @class='Button---btn Button---default_direction appian-context-first-in-list appian-context-last-in-list']")), "Upload submit");
		Thread.sleep(3000);
	
	}
	public void selectDropdownByTextDiv(String label, String value)  {
		String dropdownXpath = "//div[contains(text(),'-- Select Document Type')]";
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(dropdownXpath)), label);
		selectValueFromList(value);
	}
}
