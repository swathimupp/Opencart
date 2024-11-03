package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistartionPage extends BasePage {

	public AccountRegistartionPage(WebDriver driver) {
			super(driver);
	}

@FindBy(xpath="//input[@id='input-finrstname']")
WebElement txtFirstname;

@FindBy(xpath ="//input[@id='input-lastname']")
WebElement txtLastName;


@FindBy(xpath ="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath ="//input[@id='input-password']")
WebElement txtPassword;

@FindBy(xpath="//input[@name='agree']")
WebElement chkdPolicy;

@FindBy(xpath = "//button[normalize-space()='Continue']")
WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstName(String fname)
{
	txtFirstname.sendKeys(fname);
}

public void setLastName(String lname)
{
	txtLastName.sendKeys(lname);
}

public void setEmail(String email)
{
	txtEmail.sendKeys(email);
}

public void setPassword(String pwd)
{
	txtPassword.sendKeys(pwd);
}

public void setPrivacyPolicy()
{
	chkdPolicy.click();
}

public void clickContinue()
{
	btnContinue.click();
}

//sol2:btn.continue.submit();
//sol3:Actions act=new Actions(driver);
//act.movetoelement(btncontinue).click().perform()

//sol4: JavaScriptExceutor js=(JavaScriptExecutor)driver;
//js.executeScript("arguments[0].click();",btnContinue);

//sol5:btnContinue.sendKeys(Keys.Return);

//sol6:WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(!0));
//mywait.until(ExpectedConditions.elementtobeClickable(btncontinue).click();

public String getConfirmationMsg()
{
	try {
	return (msgConfirmation.getText());
	}
	catch(Exception e) {
		return(e.getMessage());
	}
}




































	
}
