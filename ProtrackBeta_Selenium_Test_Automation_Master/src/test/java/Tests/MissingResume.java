package Tests;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import reusableComponents.ExcelOperations;
import testBase.DriverFactory;
import testBase.TestBase;

public class MissingResume extends TestBase{
	
	int max_count = 3;
	
	LoginPage login = new LoginPage();
	ExcelOperations excel = new ExcelOperations("Sheet4");
	
	
	@Test
	public void resumeCheck() {
		login.login("mani@allwyncorp.com", "Welcome@2");
		DriverFactory.getInstance().getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[contains(text(),'Candidates')]")).click();
		
		for(int i = 326;i<excel.getRowCount();i++) {
		String can = excel.getCellData(i, 0).trim();
		//System.out.println(can);
		
		String candidate = can;
		
		
		waitForCandidatePage();
		searchRecord(candidate);
		waitForCandidatePage();
		clickOnCandidateLink(candidate);
		
		String parent = DriverFactory.getInstance().getDriver().getWindowHandle();
		Set<String> s = DriverFactory.getInstance().getDriver().getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				DriverFactory.getInstance().getDriver().switchTo().window(child_window);
			}
		}
		
		OpenDocuments();
		checkDownload();
		DriverFactory.getInstance().getDriver().close();
		DriverFactory.getInstance().getDriver().switchTo().window(parent);
		refresh();
		}
	}
	
	public void searchRecord(String value) {
		String searchBox_xpath = "//input[contains(@placeholder, 'Search') ]";
		String searchBotton_xpath = "//button[contains(text(), 'Search') ]";
		WebElement search = DriverFactory.getInstance().getDriver().findElement(By.xpath(searchBox_xpath));
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 5).
		until(ExpectedConditions.elementToBeClickable(search));
		sendKeys_custom(search,"search", value );
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(searchBotton_xpath)),"Search button");
		
	}
	
	public void waitForCandidatePage() {
		for (int count = 1; count <= max_count; count++) {
			try {
				new WebDriverWait(DriverFactory.getInstance().getDriver(), 20)
						.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
								.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/p[1]"))));
				break;
			} catch (Exception e) {
			}
		}
	}
	
	public void clickOnCandidateLink(String candidate) {
		for(int i=1;i<=max_count;i++) {
		try {
			String candidateXpath = "//a[contains(text(),'"+candidate+"')]";
			WebElement candidateLink = DriverFactory.getInstance().getDriver().findElement(By.xpath(candidateXpath));
			click_custom(candidateLink,"Candidate Link");
			break;
		} catch (Exception e) {
			
		}
		}
	}
	
	public void OpenDocuments() {
		for (int count = 1; count <= max_count; count++) {
			try {
				new WebDriverWait(DriverFactory.getInstance().getDriver(), 25)
						.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
								.findElement(By.xpath("//div[contains(text(),'Documents')]"))))
						.click();
				break;
			} catch (Exception e) {
			}
		}
	}
	
	public void checkDownload() {
		try {
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5)
					.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
							.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]/p[1]/a[1]/span[1]/*[1]"))));

			String doc = DriverFactory.getInstance().getDriver().findElement(By.xpath("//tr[1]/td[2]")).getText();
			// System.out.println(doc);
			String link = "//a[contains(text(),'" + doc + "')]";

			WebElement at_link = DriverFactory.getInstance().getDriver().findElement(By.xpath(link));

			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5)
					.until(ExpectedConditions.elementToBeClickable(at_link)).click();
			String S1 = DriverFactory.getInstance().getDriver().findElement(By.tagName("h1")).getText();
			for (int count = 1; count <= 2; count++) {
			try {
				// verify the download icon
				String downlod_img = "//tbody/tr[1]/td[2]/div[1]/p[1]/a[2]/span[1]/*[1]";
				WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath(downlod_img));
				new WebDriverWait(DriverFactory.getInstance().getDriver(), 5)
						.until(ExpectedConditions.elementToBeClickable(element));
				boolean display = element.isDisplayed();

				System.out.print(display);

				System.out.println(" resume download for - " + S1);
				break;
			} catch (Exception e) {
				if (count==2) {
				System.out.println(" resume not download for - " + S1);}
			}
			}

		} catch (Exception e) {
			String S1 = DriverFactory.getInstance().getDriver().findElement(By.tagName("h1")).getText();
			System.out.println("No resume for - " + S1);
		}
	}

}
