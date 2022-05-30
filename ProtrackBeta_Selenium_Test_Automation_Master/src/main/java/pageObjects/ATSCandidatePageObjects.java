package pageObjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import reusableComponents.JsonOperations;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;

public class ATSCandidatePageObjects extends TestBase{
	JsonOperations jo;

	By addCandidate = By.xpath("//button[contains(text(),'Add Candidate')]");

	public void clickAddCandidate() {
	click_custom(DriverFactory.getInstance().getDriver().findElement(addCandidate),"Add Candidate");
	}
	
	public void createCandidate() {
		jo = new JsonOperations("C:\\Users\\Mani\\eclipse-workspace\\Advanced_Selenium_Test_Automation_Framework-master.zip_expanded\\Advanced_Selenium_Test_Automation_Framework-master\\src\\test\\resources\\json\\ATScandidate.json");
		String email = jo.get("Email")+randomString()+"@mailinator.com";
		enterText("First Name", jo.get("First Name"));
		enterText("Last Name", jo.get("Last Name"));
		enterText("Email", email);
		phone(jo.get("Phone"));
		selectDropdownByText("Country", jo.get("Country"));
		selectDropdownByText("State", jo.get("State"));
		enterText("City", jo.get("City"));
		selectDropdownByText("Work Authorization", jo.get("Work Authorization"));
		selectDropdownByText("Employment Type", jo.get("Employment Type"));
		enterText("Hourly Rate", jo.get("Hourly Rate"));
		enterText("Submission Rate", jo.get("Submission Rate"));
		selectDropdownByText("Referral Source", jo.get("Referral Source"));
		attachResume();
		enterText("Experience (in years)", jo.get("Experience (in years)"));
		selectDropdownByText("Skills", jo.get("Skills"));
		clickOnButton("Submit");
		clickOnButton("OK");
		waitToloadPage("Add Candidate","button");
		refresh();
		searchRecord(email);
		Assert.assertEquals(numberOfRecordsFilter(), 1);
		
	}
	
	public void updateCandidate() {
		jo = new JsonOperations("C:\\Users\\Mani\\eclipse-workspace\\Advanced_Selenium_Test_Automation_Framework-master.zip_expanded\\Advanced_Selenium_Test_Automation_Framework-master\\src\\test\\resources\\json\\ATScandidate.json");
		openCandidate(jo.get("Search Candidate"));
		enterText("LinkedIn Link",jo.get("LinkedIn Link"));
		clickOnButton("Submit");
		clickOnButton("OK");
		waitToloadPage("Add Candidate","button");
		refresh();
		openCandidate(jo.get("Search Candidate"));
		verifyLinkedInLink(jo.get("LinkedIn Link"));
	}
	
	
	public void linkJobToCandidate(String candidate, String job) {
		searchRecord(candidate);
		clickOnAction("Link Jobs");
		linkJob(job);
		refresh();
		
	}
	
	public boolean verifyLinkedJob(String candidate, String job) {
		boolean flag = false;
		searchRecord(candidate);
		clickOnAction("Link Jobs");
		for (int count = 1; count <= 2; count++) {
			try {
				new WebDriverWait(DriverFactory.getInstance().getDriver(), 20)
						.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
								.findElement(By.xpath("//div[@title='Linked Jobs']//parent::div/following-sibling::div/descendant::table[1]/tbody/tr[1]/td[1]/div[1]/p[1]"))));
				break;
			} catch (Exception e) {
			}
		}
		
	List<WebElement> element =	DriverFactory.getInstance().getDriver().findElements(By.xpath("//div[@title='Linked Jobs']//parent::div/following-sibling::div/descendant::table[1]/tbody/tr/td[1]/div[1]/p[1]"));
		for(WebElement e : element ) {
			if (e.getText().equals(job)) {
				unlinkJob(job);
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void unlinkJob( String field_name) {
		int row_number = 	getRowNumber("Linked Jobs", "Job Ref No", field_name );
		deleteRowInTable("Linked Jobs",row_number);
	}
	public void openCandidate(String candidate) {
		waitToloadPage("Add Candidate", "button");
		searchRecord(candidate);
		clickOnAction("Edit Candidate");
		editCandidatePage("Candidate Details");
			
	}
	
	public boolean editCandidatePage(String value) {
		String xpath = "//span[contains(text(),'"+value+"')]";
		boolean result= false;
		try {
			WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath));
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
			until(ExpectedConditions.elementToBeClickable(element));
			element.isDisplayed();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, value+"==> Displayed Successfully! ");
			result=true;
		} catch (Exception e) {
			//log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to find the field: " +value +" due to exception: "+e);
		}
		return result;
	}
	
	public void verifyLinkedInLink(String value) {
		String actual = getText("LinkedIn Link");
		String expected = value;
		Assert.assertEquals(actual, expected);
		ExtentFactory.getInstance().getExtent().log(Status.PASS, value+"==> Displayed Successfully! ");
	}
	
	public void attachResume() {
		clickOnButton("Upload");
		try {
			Thread.sleep(1000);
			Runtime.getRuntime().exec("D:\\Software\\Auto It\\Fileupload.exe");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void phone(String phone) {
		String xpath = "//input[@class = 'TextInput---text TextInput---align_start TextInput---inSideBySideItem' and contains(@placeholder,'Contact Number')]";
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath)),"Phone",phone);
	}
	
	public int numberOfRecordsFilter() {
		String xpath = "//tbody/tr";
		int count =  DriverFactory.getInstance().getDriver().findElements(By.xpath(xpath)).size();
		return count;
	}
	
	public void waitToloadPage(String title, String fieldType) {
		if (fieldType == "button") {
			WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath("//button[contains(text(),'"+ title +"')]"));
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
			until(ExpectedConditions.elementToBeClickable(element));
		}else if(fieldType == "text") {
			WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[contains(text(),'"+ title +"')]"));
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
			until(ExpectedConditions.elementToBeClickable(element));
		}else if(fieldType == "link") {
			WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath("//a[contains(text(),'"+ title +"')]"));
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
			until(ExpectedConditions.elementToBeClickable(element));
		}
		
	}
	
	public void searchJobInLinkJobPage(String job) {
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//input[@type='text' and @xpath ='2']")).sendKeys(job);
		WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath("//button[contains(text(),'Search') and @xpath = '2']"));
		click_custom(element, "Search");
	}
	
	public boolean linkJobOnFirstPage(String job) {
		boolean flag = false;
		for (int count = 1; count <= 2; count++) {
			try {
				new WebDriverWait(DriverFactory.getInstance().getDriver(), 20)
						.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
								.findElement(By.xpath("//div[@title='Search Job']//parent::div/following-sibling::div/descendant::table[1]/tbody/tr[1]/td[1]/div[1]/p[1]"))));
				break;
			} catch (Exception e) {
			}
		}
		for(int i=1;i<=10;i++) {
		String xpath = "//div[@title='Search Job']//parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+i+"]/td[2]/div[1]/p[1]";
		WebElement element  = DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath));
		if (element.getText().equals(job)) {
			String checkbox_xpath ="//div[@title='Search Job']//parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+i+"]/td[1]//div[@role='group']";
			click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(checkbox_xpath)),job+" check box");
			 clickOnButton("Link Jobs");
			 flag=true;break;
		}
		}
		return flag;
		
	}
	public void linkJob(String job) {
		if(!linkJobOnFirstPage(job)) {
			searchJobInLinkJobPage(job);
			linkJobOnFirstPage(job);
		}
	}
	
	
	
}
