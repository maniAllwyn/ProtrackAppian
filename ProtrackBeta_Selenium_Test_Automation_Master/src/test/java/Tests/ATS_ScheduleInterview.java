package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import pageObjects.ATSCandidatePageObjects;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ScheduleJobPageObjects;
import reusableComponents.JsonOperations;
import testBase.TestBase;

public class ATS_ScheduleInterview extends TestBase {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	ATSCandidatePageObjects candidate = new ATSCandidatePageObjects();
	ScheduleJobPageObjects schedule = new ScheduleJobPageObjects();
	JsonOperations jo;
	
	@Test()
	public void jobCreation() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		jo = new JsonOperations("C:\\Users\\Mani\\eclipse-workspace\\Advanced_Selenium_Test_Automation_Framework-master.zip_expanded\\Advanced_Selenium_Test_Automation_Framework-master\\src\\test\\resources\\json\\ATScandidate.json");
		String candidate = "Suman K";
		login.amManagerLogin();				//login as Account Manager
		home.openATS();						//open HRIS
		home.clickOnMenu("Candidates");		//Open Candidates Page
		schedule.scheduleInteview(candidate);
		schedule.verifyStatus("Technical screening scheduled");
		schedule.verifyScheduleInteview(candidate);
		schedule.verifyScheduleInterviewHome(candidate);
		schedule.removeInterview(candidate);
		
		
		
		
	}
}
