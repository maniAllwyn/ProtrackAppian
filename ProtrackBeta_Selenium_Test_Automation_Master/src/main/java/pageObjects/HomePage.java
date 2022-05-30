package pageObjects;

import org.openqa.selenium.By;

import testBase.DriverFactory;
import testBase.TestBase;

public class HomePage extends TestBase {
	
	HomePageObjects hpo = new HomePageObjects();

	By candidate_selection = By.linkText("Candidate Selection");

	// click on menu bar - by passing name of menu
	public void clickOnMenu(String menu) {
		String menuXpath = "//div[@class='SiteMenuTab---nav_label SiteMenuTab---mercury_style_label' and contains(text(),'"
				+ menu + "')]";
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(menuXpath)), menu + " menu");
	}

	// click on tiles in homepage - by passing name of tile
	public void clickOnTile(String tile) {
		String tileXpath = "//span[@class='SizedText---large SizedText---predefined' and contains(text(),'" + tile
				+ "')";
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath(tileXpath)), tile + " tile");
	}
	
	//Open ATS
	public void openATS() {
		DriverFactory.getInstance().getDriver().get("https://allwyntest.appiancloud.com/suite/sites/applicant-tracking");
	}

	//open HRIS
	public void openHRIS() {
		DriverFactory.getInstance().getDriver().get("https://allwyntest.appiancloud.com/suite/sites/protrack");
	}

	public void clickOnCandidateSelection() {
		click_custom(DriverFactory.getInstance().getDriver().findElement(candidate_selection), "Candidate Selection");
	}
	
	public void signout() {
		hpo.clickProfile();
		hpo.clickSignOut();
		
	}
}
