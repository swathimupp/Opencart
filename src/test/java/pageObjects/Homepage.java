package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage{
	
	
	public Homepage(WebDriver driver)//with out creating this class constructor we cannot
	//invoke parent class constructor
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkMyaccount;
	
	@FindBy(xpath ="//a[@class='dropdown-item'][normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(linkText = "Login")//login link addded
	WebElement linkLogin;
	
	public void clickMyAccount()
	{
		linkMyaccount.click();
	}
	
	public void clickRegister()
	{
		linkRegister.click();
	}
	
	public void clickLogin()
	{
		linkLogin.click();
	}
}
