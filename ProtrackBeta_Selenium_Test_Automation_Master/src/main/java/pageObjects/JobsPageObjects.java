package pageObjects;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import reusableComponents.ActionEngine;
import reusableComponents.JsonOperations;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;

public class JobsPageObjects extends TestBase {

	JsonOperations jo;
	ActionEngine ae = new ActionEngine();

	By createNewJob = By.xpath("//button[contains(text(),'Create New Job')]");
	
	public void createJob(HashMap<String, String> testData) {
		ExtentFactory.getInstance().getExtent().info(testData.get("Scenario"));
	}
	
	
	
	public void clickCreateNewJob() throws InterruptedException  {
		jo = new JsonOperations("C:\\Users\\Mani\\eclipse-workspace\\Advanced_Selenium_Test_Automation_Framework-master.zip_expanded\\Advanced_Selenium_Test_Automation_Framework-master\\src\\test\\resources\\json\\create_job.json");
		click_custom(DriverFactory.getInstance().getDriver().findElement(createNewJob),"Create New Job button");
		ExtentFactory.getInstance().getExtent().info(jo.get("Scenario"));
		selectDropdownByText("Priority", jo.get("Priority"));
enterTextInRichtextEditor(2,"Required Qualifications","test data");
		
		Thread.sleep(3000);
		enterText("Target Rate", jo.get("Target Rate"));
		enterText("Due Date", jo.get("Due Date"));
		selectDropdownByText("Account Manager", jo.get("Account Manager"));
		selectDropdownByText("Recruiter",jo.get("Recruiter"));
		enterText("Client Manager", jo.get("Client Manager"));
		selectDropdownByText("Client",jo.get("Client"));
		selectDropdownByText("Job Title",jo.get("Job Title"));
		selectDropdownByText("Job Type",jo.get("Job Type"));
		selectDropdownByText("Required Experience (In years)",jo.get("Experience"));
		selectDropdownByText("Skills",jo.get("Skills"));
		

	}
	
	public void updateJob(String job) {
		jo = new JsonOperations("C:\\Users\\Mani\\eclipse-workspace\\Advanced_Selenium_Test_Automation_Framework-master.zip_expanded\\Advanced_Selenium_Test_Automation_Framework-master\\src\\test\\resources\\json\\update_job.json");
		openJob(job);
		enterComments("Additional Information", "test1"+getDate());
		clickOnButton("Submit");
		clickOnButton("OK");
			
		
		
	}
	
	public void openJob(String job) {
		searchRecord(job);
		clickOnAction("Edit");
		verifyPageTitle("Edit Job "+job);
		
		System.out.println(verifyUpdates("Text", "Target Rate", "500"));
		System.out.println(verifyUpdates("CommentBox", "Additional Information", "test1"+getDate()));
		System.out.println(verifyUpdates("Dropdown", "Priority", "High"));
		System.out.println(verifyUpdates("Dropdown", "Account Manager", ""));
	}
	
	public void linkCandidate(String job) {
		ae.searchRecord(job);
		clickOnAction("Link Candidate");
		selectCandidate("Recommended Candidates","Candidate Name","Dhanaraj Javvadi");		
		refresh();
		searchRecord(job);
		clickOnAction("Link Candidate");
			
		
	}
	
	public boolean verifyLinkedJob(String table, String column_label, String job) {
		ArrayList<String> list = new ArrayList<String>();
		list = ae.getFieldValue_grid(table, column_label);
		boolean res = false;
		for(String s : list) {
			if (s.equals(job)) {
				res=true;
				break;
			}
		}return res;
		
	}
	
	public void selectCandidate(String table, String field_label, String field_name) {
		int row_number;
		try {
			row_number = 	ae.getRowNumber(table, field_label, field_name );
			if(row_number==0)row_number=1;
		} catch (Exception e) {
			row_number = 1;
		}
	
	//int column_number = getfield_grid(field_label);
	String checkbox_xpath ="//div[@title='"+table+"']//parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+row_number+"]/td[1]//div[@role='group']";
	click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(checkbox_xpath)),field_name+" check box");
	 clickOnButton("Link Candidates");
	 WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath("//a[contains(text(),'"+field_name+"')]"));
	 new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
		until(ExpectedConditions.elementToBeClickable(element));
	 element.getText();
	}
	
	public void unlinkCandidate(String table, String field_label, String field_name) {
		int row_number = 	ae.getRowNumber(table, field_label, field_name );
		deleteRowInTable(table,row_number);
	}
	public boolean verifycomment() {
		String actual = getComment("Additional Information");
		String expected = "test1"+getDate();
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Actual comment : " + actual+ " expected comment : " + expected);
		return actual.equals(expected);
	}
	
	public boolean verifyUpdates(String fieldType, String fieldName, String Value) {
		//validate the updated field value 
		boolean result = false;
		if(fieldType=="Text") {
			String actual =  getText(fieldName);
			String expected = Value;
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Actual value : " + actual+ " expected value : " + expected);
			result = actual.equals(expected);System.out.println(actual);
			
		}else if (fieldType=="CommentBox") {
			String actual = getComment(fieldName);
			String expected = Value;
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Actual value : " + actual+ " expected value : " + expected);
			result = actual.equals(expected);System.out.println(actual);
			
		}else if (fieldType=="Dropdown") {
			String actual = getDropdownValue(fieldName);
			String expected = Value;
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Actual value : " + actual+ " expected value : " + expected);
			result = actual.equals(expected);System.out.println(actual);
			
		}
		
		return result;
	}
	
	
	
	
}
