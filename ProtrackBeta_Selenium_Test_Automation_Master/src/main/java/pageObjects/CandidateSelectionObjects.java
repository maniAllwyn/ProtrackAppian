package pageObjects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.TestBase;

public class CandidateSelectionObjects extends TestBase{

	
			//span[contains(text(),'Participation in Medical/Dental/Vision plan')]/parent::div/following-sibling::div/descendant::div[@role='listbox']
			//Textbox xpath = //label[contains(text(),'First Name')]/parent::div/following-sibling::div/descendant::div/input[@type='text']
			//Dropdown xpath = //span[contains(text(),'Country')]/parent::div/following-sibling::div/descendant::div[@class='DropdownWidget---dropdown_value']
	
	public void createCandidate(HashMap<String, String> testData) throws Throwable {
		ExtentFactory.getInstance().getExtent().info(testData.get("Scenario"));
		enterText("First Name", testData.get("First Name"));
		enterText("Last Name", testData.get("Last Name"));
		enterText("Email", testData.get("Email")); 
		enterText("Phone", testData.get("Phone"));
		selectDropdownByText("Country", testData.get("Country"));
		enterText("Address Line 1", testData.get("Address Line 1"));
		enterText("Address Line 2", testData.get("Address Line 2"));
		enterText("City", testData.get("City"));
		enterText("Zip", testData.get("Zip"));
		selectDropdownByText("Work Authorization", testData.get("Work Authorization"));
		selectDropdownByText("Employment Type",testData.get("Employment Type"));
		selectDropdownByText("Pay Rate Currency", testData.get("Pay Rate Currency"));
		selectDropdownByText("Working Type", testData.get("Working Type"));
		enterText("Amount", testData.get("Amount"));
		enterText("Last four digits of SSN", testData.get("Last four digits of SSN"));
		enterText("Date of Birth", testData.get("Date of Birth"));
		selectDropdownByText("Participation in Medical/Dental/Vision plan", testData.get("Participation in Medical/Dental/Vision plan"));
		selectDropdownByText("Paid Time Off", testData.get("Paid Time Off"));
		enterText("Estimated Payroll Tax", testData.get("Estimated Payroll Tax"));
		enterText("Healthcare Monthly Cost", testData.get("Healthcare Monthly Cost"));
		selectDropdownByText("Job Title", testData.get("Job Title"));
		selectDropdownByText("Client Name", testData.get("Client Name"));
		enterText("Start Date", testData.get("Start Date"));
		enterText("Bill Rate to Client", testData.get("Bill Rate to Client"));
		selectDropdownByText("Vendor", testData.get("Vendor"));
		selectDropdownByText("Device Information", testData.get("Device Information"));
		clickOnButton("Submit");
	}
	
	public Boolean error(String exceptedError) {
		String[] errors = exceptedError.split(",");
		List<String> errorList = Arrays.asList(errors);
		String xpath = "//div[@class='FieldLayout---field_error']";
		Boolean flag = false;
		List<WebElement> list = DriverFactory.getInstance().getDriver().findElements(By.xpath(xpath));
		int errorCount = list.size();
		if (errorList.isEmpty() && errorCount ==0) {
			flag = true;
		}else {
		if(errorCount == 1) {
			for (WebElement element : list) {
				String actualError =  element.getText();
				ExtentFactory.getInstance().getExtent().info(actualError);
				if(actualError.equals(errorList.get(0))) {
				flag = true;
				ExtentFactory.getInstance().getExtent().info(actualError + " - error message displayed");
				break;
				}	
			}
		}else if(errorCount>1) {
			for (String e : errorList) {
			for (WebElement element : list) {
				String actualError =  element.getText();
				ExtentFactory.getInstance().getExtent().info(actualError);
				if(actualError.equals(e)) {
				flag = true;
				ExtentFactory.getInstance().getExtent().info(actualError + " - error message displayed");
				}	
			}
			}
		}else {
			ExtentFactory.getInstance().getExtent().info("Expected error, But no errors found");
		}
		}
		return flag;
	}

}
