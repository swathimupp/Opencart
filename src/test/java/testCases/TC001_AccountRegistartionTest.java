package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistartionPage;
import pageObjects.Homepage;
import testbase.BaseClass;

public class TC001_AccountRegistartionTest extends BaseClass{

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("******Starting TC001_AccountRegistartionTest ******");
		try
		{
		Homepage hp=new Homepage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistartionPage regPage=new AccountRegistartionPage(driver);
		logger.info("Providing customer details....");
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		
		regPage.setEmail(randomString()+"@gmail.com");//randomly generated the email
		
		String password=randomAlphaNumeric();
		regPage.setPassword(password);
		
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		logger.info("Validating expected message..");
		String confmsg=regPage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!!!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed....");//logging error level logs
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created");
		}
		catch(Exception e)
		{
		  
		  Assert.fail();
		}
		logger.info("this is finished");
	}
	
	
	
}
