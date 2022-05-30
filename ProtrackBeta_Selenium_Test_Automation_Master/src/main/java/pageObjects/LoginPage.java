package pageObjects;

import org.openqa.selenium.By;

import reusableComponents.PropertiesOperations;
import testBase.DriverFactory;
import testBase.TestBase;

public class LoginPage extends TestBase{
	
	By email = By.id("un");
	By pwd = By.id("pw");
	By signIn = By.xpath("//input[@type = 'submit']");
	
	public void login(String username, String password) {
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(email), "LoginEmailFIeld", username);
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(pwd), "LoginPasswordFIeld", password);
		click_custom(DriverFactory.getInstance().getDriver().findElement(signIn), "LoginButton");
		
	}
	
	public void hrManagerLogin() {
		login(PropertiesOperations.getPropertyValueByKey("hr_username"),PropertiesOperations.getPropertyValueByKey("hr_password") );
	}
	
	public void amManagerLogin() {
		login(PropertiesOperations.getPropertyValueByKey("am_username"),PropertiesOperations.getPropertyValueByKey("am_password") );
	}
	
	public void recruiterLogin() {
		login(PropertiesOperations.getPropertyValueByKey("recruiter_username"),PropertiesOperations.getPropertyValueByKey("recruiter_password") );
	}
	public void logout() {
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@class='UserProfileLayout---current_user_menu_wrapper']")), "User profile");
		click_custom(DriverFactory.getInstance().getDriver().findElement(By.xpath("//button[contains(text(),'Sign Out')]")), "Sign Out");

		
		
		//button[contains(text(),'Sign Out')]
	}
}
