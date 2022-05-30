package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.JobsPageObjects;
import pageObjects.LoginPage;
import reusableComponents.ActionEngine;
import reusableComponents.JsonOperations;
import testBase.TestBase;

public class ATS_JobsTest extends TestBase {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	JobsPageObjects jobs = new JobsPageObjects();
	//ActionEngine ae = new ActionEngine();
	JsonOperations jo;
	
	@Test(enabled=false)
	public void jobCreation() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		//login as Account Manager
		login.amManagerLogin();
		
		//open HRIS
		home.openATS();

		home.clickOnMenu("Jobs");
		jobs.clickCreateNewJob();;
		
		//sign out
		home.signout();
	}
	
	
	@Test
	public void updateJob() {
		jo = new JsonOperations("C:\\Users\\Mani\\eclipse-workspace\\Advanced_Selenium_Test_Automation_Framework-master.zip_expanded\\Advanced_Selenium_Test_Automation_Framework-master\\src\\test\\resources\\json\\update_job.json");

		//login as Account Manager
		login.amManagerLogin();
		//open HRIS
		home.openATS();
		//Open Jobs
		home.clickOnMenu("Jobs");
		jobs.updateJob(jo.get("Search Job"));
		refresh();
		jobs.openJob(jo.get("Search Job"));
		boolean res = jobs.verifycomment();
	
		Assert.assertTrue(res);
	}
	
	@Test()
	public void linkCandidate() {
		jo = new JsonOperations("C:\\Users\\Mani\\eclipse-workspace\\Advanced_Selenium_Test_Automation_Framework-master.zip_expanded\\Advanced_Selenium_Test_Automation_Framework-master\\src\\test\\resources\\json\\update_job.json");

		//login as Account Manager
		login.amManagerLogin();
		//open HRIS
		home.openATS();
		//Open Jobs
		home.clickOnMenu("Jobs");
		
		jobs.linkCandidate(jo.get("Search Job"));
		
		clickOnlink(jo.get("link candidate"));
		String parentid = get_parentWindow();
		String childid = get_childWindow();
		switchWindow(childid);
		clickOnText("Linked Jobs");
		
		boolean res = jobs.verifyLinkedJob("Linked Jobs","Job Ref No",jo.get("Search Job"));
		closewindow();
		switchWindow(parentid);
		
		System.out.println(res);Assert.assertTrue(res);
		String col = "Candidate\r\n"
				+ "Sortable column, activate to sort ascending";
		
		jobs.unlinkCandidate("Linked Candidates",col,jo.get("link candidate"));
	}
}
