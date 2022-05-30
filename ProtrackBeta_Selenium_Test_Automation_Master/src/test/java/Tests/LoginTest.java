package Tests;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.DriverFactory;
import testBase.TestBase;

public class LoginTest extends TestBase {

	LoginPage login = new LoginPage();

	@Test
	// Admin User Login
	public void AdminLogin() throws InterruptedException {
		// https://allwynprod.appiancloud.com/suite/
		login.login("mani@allwyncorp.com", "Welcome@2");
		// login.login("mani@allwyncorp.com", "Welcome@2");
		// String expected_URL = "https://allwyntest.appiancloud.com/suite/";
		// String actual_URL = DriverFactory.getInstance().getDriver().getCurrentUrl();
		DriverFactory.getInstance().getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		// Use the timeout when navigating to a webpage
		// Assert.assertEquals(actual_URL, expected_URL, "Login failed");
		// test git repo

		/*
		 * <test name="LoginTests"> <classes> <class name="Tests.UserLoginTests" />
		 * </classes> </test> <!-- Test --> <test name="DataDriven Tests"> <classes>
		 * <class name="Tests.TestCase" /> </classes> </test> <!-- Test -->
		 */

		// Open candidate
		goto_page(2821);
		//goto_page_back(4181);
		
		mis_res_2();
	}

	/*
	 * public void mis() throws InterruptedException {
	 * DriverFactory.getInstance().getDriver().findElement(By.xpath(
	 * "//div[contains(text(),'Candidates')]")).click();
	 * 
	 * int j = 1; for (int i = 1; i <= 7; i++) { String S =
	 * DriverFactory.getInstance().getDriver() .findElement(By.xpath("//tbody/tr[" +
	 * i + "]/td[1]/div[1]/p[1]")).getText(); // System.out.println(S); String can =
	 * "//a[contains(text(),'" + S + "')]";
	 * DriverFactory.getInstance().getDriver().findElement(By.xpath(can)).click();
	 * 
	 * String parent = DriverFactory.getInstance().getDriver().getWindowHandle();
	 * 
	 * Set<String> s = DriverFactory.getInstance().getDriver().getWindowHandles();
	 * 
	 * // Now iterate using Iterator Iterator<String> I1 = s.iterator();
	 * 
	 * while (I1.hasNext()) {
	 * 
	 * String child_window = I1.next();
	 * 
	 * if (!parent.equals(child_window)) {
	 * DriverFactory.getInstance().getDriver().switchTo().window(child_window); }
	 * 
	 * }
	 * 
	 * // open documents
	 * DriverFactory.getInstance().getDriver().findElement(By.xpath(
	 * "//div[contains(text(),'Documents')]")) .click(); try {
	 * 
	 * String doc =
	 * DriverFactory.getInstance().getDriver().findElement(By.xpath("//tr[1]/td[2]")
	 * ).getText(); // System.out.println(doc); String link =
	 * "//a[contains(text(),'" + doc + "')]";
	 * DriverFactory.getInstance().getDriver().findElement(By.xpath(link)).click();
	 * 
	 * // verify the download icon String downlod_img =
	 * "//tbody/tr[1]/td[2]/div[1]/p[1]/a[2]/span[1]/*[1]"; try { WebElement element
	 * = DriverFactory.getInstance().getDriver().findElement(By.xpath(downlod_img));
	 * new WebDriverWait(DriverFactory.getInstance().getDriver(), 5)
	 * .until(ExpectedConditions.elementToBeClickable(element)); boolean display =
	 * element.isDisplayed();
	 * 
	 * System.out.println(display);
	 * 
	 * System.out.print(" resume download for " + S); } catch (Exception e) {
	 * System.out.print(" resume not download for " + S); }
	 * 
	 * } catch (Exception e) { System.out.println("No resume for " + S); }
	 * 
	 * DriverFactory.getInstance().getDriver().close();
	 * DriverFactory.getInstance().getDriver().switchTo().window(parent);
	 * 
	 * if (i % 2 == 0) { DriverFactory.getInstance().getDriver().findElement(By.
	 * xpath("//a[@aria-label='Next page']")).click(); i = 1; }
	 * 
	 * if (j == 5) { break;
	 * 
	 * }
	 * 
	 * Thread.sleep(5000); }
	 * 
	 * }
	 */
	public void mis_res_2() throws InterruptedException {
	//	DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[contains(text(),'Candidates')]")).click();

		int j = 2821;//1921+680;//281; //2241; // 1361
		int max_att = 3;

		for (int i = 1; i <= 20; i++) {
			WebElement res = DriverFactory.getInstance().getDriver()
					.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div[1]/p[1]"));
			Actions act = new Actions(DriverFactory.getInstance().getDriver());
			// act.moveToElement(res).click().perform();
			String S = res.getText();
			// System.out.println(S);
			String can = "//a[contains(text(),'" + S + "')]";

			WebElement candidate = DriverFactory.getInstance().getDriver().findElement(By.xpath(can));
			act.moveToElement(candidate).click().perform();
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

			// open documents
			for (int count = 1; count <= max_att; count++) {
				try {
					new WebDriverWait(DriverFactory.getInstance().getDriver(), 25)
							.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
									.findElement(By.xpath("//div[contains(text(),'Documents')]"))))
							.click();
					break;
				} catch (Exception e) {
				}
			}

			// DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[contains(text(),'Documents')]")).click();
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

			DriverFactory.getInstance().getDriver().close();
			DriverFactory.getInstance().getDriver().switchTo().window(parent);

			if (i % 20 == 0) {
				WebElement nxt = DriverFactory.getInstance().getDriver()
						.findElement(By.xpath("//a[@aria-label='Next page']"));
				new WebDriverWait(DriverFactory.getInstance().getDriver(), 20)
						.until(ExpectedConditions.elementToBeClickable(nxt));
				act.moveToElement(nxt).click().perform();
				int m = j + 1;
				String m1;
				if (m >= 1000) {
					m1 = addCharToString(Integer.toString(m), ',', 1);
				} else {
					m1 = Integer.toString(m);
				}
for(int k=1;k<=3;k++) {
	try {
		WebElement nxt_wait = DriverFactory.getInstance().getDriver()
				.findElement(By.xpath("//strong[contains(text(),'" + m1 + "')]"));
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 20)
				.until(ExpectedConditions.elementToBeClickable(nxt_wait));

	} catch (Exception e) {
Thread.sleep(2000);	}
								
}
				// System.out.println(i);
				i = 0;
			}
			j++;
			if (j == 4312) {  //4239
				break;

			}

			Thread.sleep(2000);
		}

	}

	public void goto_page_back(int page) throws InterruptedException {
		int max_att = 3;
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[contains(text(),'Candidates')]")).click();

		Actions act = new Actions(DriverFactory.getInstance().getDriver());
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 20)
		.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
				.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/p[1]"))));
		
		WebElement nxt = DriverFactory.getInstance().getDriver()
				.findElement(By.xpath("//i[@class='GridFooter---paging_control GridFooter---fa-angle-double-right-svg']"));
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 25)
				.until(ExpectedConditions.elementToBeClickable(nxt));
		act.moveToElement(nxt).click().perform();
		
		
		int m = 4301;
		for (int i = 1; i <= 500; i++) {
			for (int count = 1; count <= max_att; count++) {
				try {
					new WebDriverWait(DriverFactory.getInstance().getDriver(), 5)
							.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
									.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/p[1]"))));
					break;
				} catch (Exception e) {
				}
			}
			
			WebElement before = DriverFactory.getInstance().getDriver()
					.findElement(By.xpath("//i[@class='GridFooter---paging_control GridFooter---fa-angle-left-svg']"));
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 5)
					.until(ExpectedConditions.elementToBeClickable(before));
			act.moveToElement(before).click().perform();
			
			m = m - 20;

			String m1;
			if (m >= 1000) {
				m1 = addCharToString(Integer.toString(m), ',', 1);
			} else {
				m1 = Integer.toString(m);
			}
			for (int count = 1; count <= max_att; count++) {
				try {
					WebElement nxt_wait = DriverFactory.getInstance().getDriver()
							.findElement(By.xpath("//strong[contains(text(),'" + m1 + "')]"));
					new WebDriverWait(DriverFactory.getInstance().getDriver(), 5)
							.until(ExpectedConditions.elementToBeClickable(nxt_wait));
					break;
				} catch (Exception e) {
					//Thread.sleep(2000);
				}
			
			}
			if (m == page) {
				break;
			}
		}
	}
	public void goto_page(int page) {
		int max_att = 3;
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[contains(text(),'Candidates')]")).click();

		Actions act = new Actions(DriverFactory.getInstance().getDriver());
		int m = 1;
		for (int i = 1; i <= 500; i++) {
			for (int count = 1; count <= max_att; count++) {
				try {
					new WebDriverWait(DriverFactory.getInstance().getDriver(), 20)
							.until(ExpectedConditions.elementToBeClickable(DriverFactory.getInstance().getDriver()
									.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/p[1]"))));
					break;
				} catch (Exception e) {
				}
			}
			WebElement nxt = DriverFactory.getInstance().getDriver()
					.findElement(By.xpath("//a[@aria-label='Next page']"));
			new WebDriverWait(DriverFactory.getInstance().getDriver(), 25)
					.until(ExpectedConditions.elementToBeClickable(nxt));
			act.moveToElement(nxt).click().perform();

			m = m + 20;

			String m1;
			if (m >= 1000) {
				m1 = addCharToString(Integer.toString(m), ',', 1);
			} else {
				m1 = Integer.toString(m);
			}
			for (int count = 1; count <= max_att; count++) {
				try {
					WebElement nxt_wait = DriverFactory.getInstance().getDriver()
							.findElement(By.xpath("//strong[contains(text(),'" + m1 + "')]"));
					new WebDriverWait(DriverFactory.getInstance().getDriver(), 25)
							.until(ExpectedConditions.elementToBeClickable(nxt_wait));
					break;
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			if (m == page) {
				break;
			}
		}
	}

	public static String addCharToString(String str, char c, int pos) {
		StringBuilder stringBuilder = new StringBuilder(str);
		stringBuilder.insert(pos, c);
		return stringBuilder.toString();
	}
}
