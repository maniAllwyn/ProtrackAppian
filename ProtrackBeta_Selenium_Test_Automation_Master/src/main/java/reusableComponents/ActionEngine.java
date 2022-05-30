package reusableComponents;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;

/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class ActionEngine {

	//Customized sendkeys method-> To log sendkeys message for every occ.
	public void sendKeys_custom(WebElement element, String fieldName, String valueToBeSent) {
		try {
			clear_custom(element,fieldName);
			element.sendKeys(valueToBeSent);
			//log success message in exgent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Ented value as: "+valueToBeSent);
		} catch (Exception e) {
			//log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field: "+fieldName + " is failed due to exception: "+e);
		}
	}


	//custom click method to log every click action in to extent report
	public void click_custom(WebElement element, String fieldName) {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
			until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Clicked Successfully! ");
		} catch (Exception e) {
			//log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
		}
	}


	//clear data from field
	public void clear_custom(WebElement element,String fieldName) {
		try {
			element.clear();
			Thread.sleep(250);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Data Cleared Successfully! ");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to clear Data on field: " +fieldName +" due to exception: "+e);

		} 
	}

	//custom mouseHover 
	public void moveToElement_custom(WebElement element,String fieldName){
		try{
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());		
			actions.moveToElement(element).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		}catch(Exception e){
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to hover mouse on field: " +fieldName +" due to exception: "+e);

		}
	}


	//check if element is Present
	public boolean isElementPresent_custom(WebElement element,String fieldName){
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Presence of field is: "+ flag);
			return flag;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Checking for presence of field: " +fieldName +" not tested due to exception: "+e);
			return flag;
		}
	}


	//Select dropdown value value by visibleText
	public void selectDropDownByVisibleText_custom(WebElement element, String fieldName, String ddVisibleText) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddVisibleText);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
		}
	}

	//Select dropdown value value by value
	public void selectDropDownByValue_custom(WebElement element, String fieldName, String ddValue) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByValue(ddValue);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddValue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
		}
	}

	//String Asserts
	public void assertEqualsString_custom(String expvalue, String actualValue, String locatorName) throws Throwable {
		try {
			if(actualValue.equals(expvalue)) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
			}else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	//Get text from webelement
	public String getText_custom(WebElement element, String fieldName) {
		String text = "";
		try {
			text = element.getText();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Text retried is: "+ text);
			return text;
		} catch (Exception e) {		
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, fieldName+"==> Text not retried due to exception: "+ e);

		}
		return text;
	}
	
	
	
	// ---------------------------------------------------------------Appian Elements---------------------------------------------------
	//Enter text to the textfields
	public void enterText(String label, String value) {
		String xpath = "//label[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div/input[@type='text']";
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath)), label, value);

	}
	
	public String getText(String label) {
		String xpath = "//label[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div/input[@type='text']";
		WebElement element =  DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath));
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
		until(ExpectedConditions.elementToBeClickable(element));
		return element.getAttribute("value");
	}
	
	//Enter text to the comment box
	public void enterComments(String label, String value) {
		String xpath = "//label[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div/textarea[@role='textbox']";
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath)), label, value);

	}
	
	public String getComment(String label) {
		String xpath = "//label[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div/textarea[@role='textbox']";
		WebElement element =  DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath));
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
		until(ExpectedConditions.elementToBeClickable(element));
		return element.getText();
	}
	
	
	//enter text to the richtext editors
	public void enterTextInRichtextEditor(int iframe_index,String label, String value) {
		DriverFactory.getInstance().getDriver().switchTo().frame(1);
		/*String xpath = "//div[@placeholder = 'Enter qualification']";
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(xpath)), label, value);*/
		
		WebElement editor = DriverFactory.getInstance().getDriver().findElement(By.xpath("//*[@id=\"quill-container\"]/div[1]"));
	      JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
	     // jsExecutor.executeScript("arguments[0].innerHTML = '<h1>Selenium Test </h1><h2>Create By Young</h2>'", editor);
	      jsExecutor.executeScript("document.querySelector(\"//div[@class='ql-editor ql-blank']//div\").value='Krishna'");
	      DriverFactory.getInstance().getDriver().switchTo().defaultContent();
	}
	
	// this method will click on the dropdown value after click on the dropdown
	public void selectValueFromList(String value) {
		List<WebElement> list = DriverFactory.getInstance().getDriver().findElements(By.xpath("//ul[@role='listbox']//li"));
		//System.out.println(list.size());
		for(WebElement element : list){
			System.out.println(element.getText());
			if(element.getText().equals(value))
			{
				click_custom(element, value);
				break;
			}
		}	
	}
	
	// this method used to handle the dropdown
	public void selectDropdownByText(String label, String value)  {
		String dropdownXpath = "//span[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div[@role='listbox']";
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(dropdownXpath)), label);
		selectValueFromList(value);
	}
	
	public void selectDropdownByTextDiv(String label, String value)  {
		String dropdownXpath = "//div[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div[@role='listbox']";
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(dropdownXpath)), label);
		selectValueFromList(value);
	}
	
	public String getDropdownValue(String label) {
		String dropdownXpath = "//span[contains(text(),'"+label+"')]/parent::div/following-sibling::div/descendant::div[@role='listbox']";
		WebElement element =  DriverFactory.getInstance().getDriver().findElement(By.xpath(dropdownXpath));
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
		until(ExpectedConditions.elementToBeClickable(element));
		String value = element.getText();
		ExtentFactory.getInstance().getExtent().log(Status.PASS, value+"==> Displayed Successfully! ");
		return value;

	}
	public void getGridField(String table, String column_label) {
		String xpath = "//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody";
	}
	
	
	public int getfield_grid(String table, String label) {
		//it will return the column based on the label
		int lastColumncount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]")).findElements(By.tagName("th")).size();
		int column_number=0;
		for (int i=1; i<=lastColumncount; i++)
		{
			String columnXpath =  "//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]//tr//th["+i+"]";
			String columnLabel = DriverFactory.getInstance().getDriver().findElement(By.xpath(columnXpath)).getText();
			columnLabel = columnLabel.replaceAll("[-+^]*", "");
			label = columnLabel.replaceAll("[-+^]*", "");
			System.out.println(columnLabel);
			if(columnLabel.equalsIgnoreCase(label) ) {
				 column_number = i;
				break;
			}	
		}
		return column_number;
	}
	
	public int getRowNumber(String table, String column_label, String row_label ) {
		int row_number = 0;
		int column_number = getfield_grid(table,column_label);
		int lastRowcount = DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody")).findElements(By.tagName("tr")).size();
		
		for (int i=1;i<=lastRowcount;i++) {
			String rowXpath = "//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+i+"]/td["+column_number+"]";
			String rowLabel = DriverFactory.getInstance().getDriver().findElement(By.xpath(rowXpath)).getText();
			
			if(rowLabel.equalsIgnoreCase(row_label) ) {
				row_number = i;
				break;
			}
		}
		/*column_label = column_label.replaceAll("[-+^]*", "");
		ArrayList<String> list = new ArrayList<String>();
		getFieldValue_grid(table,  column_label);
		int i = 1;
		for(String s : list) {
			if (s.equals(row_label)) {
				row_number=i;
				break;
			}else {i++;}
		}*/
		
		return row_number;
	}
	
	public ArrayList<String> getFieldValue_grid(String table, String column_label) {
		int column_number = getfield_grid(table,column_label);
		List<WebElement> element = DriverFactory.getInstance().getDriver().findElements(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody/tr/td["+column_number+"]"));
		ArrayList<String> list = new ArrayList<String>();
		for(WebElement e :element) {
			list.add(e.getText());
		}
		return list;
	}
	
	//it will enter the data in last row of the grid
	public void enterText_Grid(String table, String column_label, String value) {
		int lastRowcount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody")).findElements(By.tagName("tr")).size();
		int column_number = getfield_grid(table,column_label);
		WebElement e = DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+lastRowcount+"]/td["+column_number+"]//input[@type='text'")); 
		sendKeys_custom(e, column_label, value);
	}
	
	//This method handle the dropdown in the grid
	public void selectDropdown_Grid(String table, String column_label, String value) {
		int lastRowcount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody")).findElements(By.tagName("tr")).size();
		int column_number = getfield_grid(table,column_label);
		WebElement e = DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+lastRowcount+"]/td["+column_number+"]//div[@role='listbox']")); 
		click_custom(e, column_label);
		selectValueFromList(value);	
	}
	
	//This method clicks on the button
	public void clickOnButton(String buttonText) {
		String buttonXpath = "//button[contains(text(),'"+buttonText+"')]";
		WebElement e =DriverFactory.getInstance().getDriver().findElement(By.xpath(buttonXpath)); 
		click_custom(e, buttonText);
	}
	
	//This method clicks on the buttons present  on appian popups
	public void clickOnButtonOnPopup(String buttonText) {
		String buttonXpath = "//button[contains(text(),'"+buttonText+"')and @class = 'Button---btn Button---default_direction Button---primary appian-context-first-in-list appian-context-last-in-list Button---inModalDialogLayout']";
		WebElement e =DriverFactory.getInstance().getDriver().findElement(By.xpath(buttonXpath)); 
		click_custom(e, buttonText);
	}
	
	//This method delete the rows in the grid
	public void deleteRowInGrid(String table, int rowIndex) {
		int lastColumncount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]")).findElements(By.tagName("th")).size();
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+rowIndex+"]//td["+lastColumncount+"]//a//img")), rowIndex+"row deleted");
	}
	
	public void deleteRowInTable(String table, int rowIndex) {
		int lastColumncount=DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]")).findElements(By.tagName("th")).size();
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@title='"+table+"']/parent::div/following-sibling::div/descendant::table[1]/tbody/tr["+rowIndex+"]//td["+lastColumncount+"]//a")), rowIndex+"row deleted");
	}
	
	//This method will the search the record
	public void searchRecord(String value) {
		String searchBox_xpath = "//input[contains(@placeholder, 'Search') ]";
		String searchBotton_xpath = "//button[contains(text(), 'Search') ]";
		WebElement search = DriverFactory.getInstance().getDriver().findElement(By.xpath(searchBox_xpath));
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
		until(ExpectedConditions.elementToBeClickable(search));
		sendKeys_custom(search,"search", value );
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(searchBotton_xpath)),"Search button");
		
		for(int i=1; i<=3;i++) {
			
		try {
			WebElement nxt = DriverFactory.getInstance().getDriver()
					.findElement(By.xpath("//a[@aria-label='Next page']"));
			nxt.isDisplayed() ;
			Thread.sleep(2000);
			
		} catch (Exception e) {
			break;
		}
		}	
		try {
			WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath("//a[contains(text(),'"+ value +"')]"));
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
			until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			value = value.toLowerCase();
			WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath("//p[contains(text(),'"+ value +"')]"));
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
			until(ExpectedConditions.elementToBeClickable(element));
		}
		
		
	}

	public void clickOnAction(String action) {
		String actionButton_xpath = "//button[contains(text(), 'ACTIONS') ]";
		String editButton_xpath = "//span[contains(text(), '"+ action +"') and @class = 'MenuItem---primary_text']";
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(actionButton_xpath)),"Action button");
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(editButton_xpath)),action+" button");
	}
	
	public boolean verifyPageTitle(String value) {
		String xpath = "//strong[contains(text(),'"+value+"')]";
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
	
	
	public String getDate() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");   //yyyy/MM/dd HH:mm:ss
		   LocalDateTime now = LocalDateTime.now();  
		  return (dtf.format(now));  
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphanumeric(5).toLowerCase();
		return generatedString;
	}
	
	public void refresh() {
		DriverFactory.getInstance().getDriver().navigate().refresh();
		}
	
	public void clickOnlink(String linktext) {
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//a[contains(text(),'"+linktext+"')]")),linktext);
	}
	
	public void clickOnText(String linktext) {
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[contains(text(),'"+linktext+"')]")),linktext);
	}
	
	public String get_parentWindow() {
		String parent_window = DriverFactory.getInstance().getDriver().getWindowHandle();
		return parent_window;
	}
	public String get_childWindow() {
		String parent = DriverFactory.getInstance().getDriver().getWindowHandle();
		Set<String> s = DriverFactory.getInstance().getDriver().getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();
		String child_window ="";
		while (I1.hasNext()) {

			child_window = I1.next();
		}
		return child_window;
	}
	
	public void switchWindow(String windowId) {
		DriverFactory.getInstance().getDriver().switchTo().window(windowId);
	}
	
	public void closewindow() {
		DriverFactory.getInstance().getDriver().close();
	}
}
