package pageObjects;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import reusableComponents.JsonOperations;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;

public class ScheduleJobPageObjects extends TestBase {
	ATSCandidatePageObjects candidate = new ATSCandidatePageObjects();
	HomePage home = new HomePage();
	JsonOperations jo;
	
	By scheduleDate = By.xpath("//input[@placeholder='mm/dd/yyyy']");
	By scheduletime = By.xpath("//input[@placeholder='hh:mm am']");
	By scheduleIntervivewHome = By.xpath("//strong[contains(text(),'Scheduled Interview')]");
	
	String interviewDate = "05/06/2023";
	public void enterscheduleDate() {
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(scheduleDate),"Schedule date",interviewDate);
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(scheduletime),"Schedule time","5:30 AM");
		}
	
	
	public void scheduleInteview(String candidateName) throws InterruptedException {
		
		candidate.searchRecord(candidateName);
		candidate.clickOnAction("Schedule Interview");
		enterscheduleDate();
		enterText("Interviewer Name","Mani");
		enterText("Interviewer Email","mani@allwyncorp.com");
		selectDropdownByText("Linked Jobs", "PBS-3 | Software Professional");
		clickOnButton("Submit");
	}
	
	public void verifyScheduleInteview(String candidateName) {
		
		candidate.searchRecord(candidateName);
		candidate.clickOnAction("Schedule Interview");
		try{
			DriverFactory.getInstance().getDriver().findElement(By.xpath("//p[contains(text(),'"+interviewDate+"')]")).isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, candidateName +"==> Interview Scheduled Successfully! ");
		}catch(Exception e) {
			e.printStackTrace();
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, candidateName +"==> Interview Scheduled Failed! ");
		}
	}
	
	public void removeInterview(String candidateName) {
		//refresh();
		home.clickOnMenu("Candidates");	
		candidate.searchRecord(candidateName);
		candidate.clickOnAction("Schedule Interview");
		int row_number = 	getRowNumber("Scheduled Interviews", "Candidate Name", candidateName );
		deleteRowInTable("Scheduled Interviews",row_number);
	}
	
	public void deleteRowInTable(String table, int rowIndex) {
		int lastColumncount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]")).findElements(By.tagName("th")).size()-1;
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+rowIndex+"]//td["+lastColumncount+"]//a")), rowIndex+"row deleted");
	}
	
	public void verifyStatus(String status) {
		try {
			DriverFactory.getInstance().getDriver().findElement(By.xpath("//p[contains(text(),'"+status+"')]"));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, status +"==> updated Successfully! ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Boolean verifyScheduleInterviewHome(String candidate) {
		Boolean flag =false;
		refresh();
		home.clickOnMenu("Home");
		click_custom(DriverFactory.getInstance().getDriver().findElement(scheduleIntervivewHome), "Home scheduled interview");
		
		List<WebElement> element =	DriverFactory.getInstance().getDriver().findElements(By.xpath("//div[@title='Scheduled Interviews']//parent::div/following-sibling::div/descendant::table[1]/tbody/tr/td[1]/div[1]/p[1]"));
		for(WebElement e : element ) {
			if (e.getText().equals(candidate)) {
				
				flag = true;
				break;
			}
		}
		return flag;
		
	}
}
