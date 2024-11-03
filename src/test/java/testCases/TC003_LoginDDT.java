package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

/*
 * data is valid-login success-test pass-logout
 * daata is valid --login failed-test faile
 * data is invalid -login success-testfail-logout
 * data is invalid--login failed--test pass
 */
public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass = DataProviders.class,groups = "Datadriven")//In which class 
	//dataprovider is present dataProviderClass=DataProviders.class,getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp) throws InterruptedException
	{
		logger.info("*******starting TC_003_LoginDDT********");
	          
		     try
		     {
		     //HomePage
				Homepage hp=new Homepage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				//Login
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(p.getProperty("email"));
				lp.setPassword(p.getProperty("password"));
				lp.clickLogin();
				
				//MyAccount
				MyAccountPage macc=new MyAccountPage(driver);
				boolean targetPage=macc.isMyAccountPageExists();
				
				
				if(exp.equalsIgnoreCase("Valid"))
				{
				   if(targetPage==true)	
				   {
					   Assert.assertTrue(true);
					   macc.clickLogout();
				   }
				   else
				   {
					   Assert.assertTrue(false);
				   }
					
				}
				
				if(exp.equalsIgnoreCase("Invalid"))
				{
					if(targetPage==true)//login is successfull
					{
						macc.clickLogout();
						Assert.assertTrue(false);//should logout
					}
					else
					{
						Assert.assertTrue(true);
					}
				}
	            }catch(Exception e)
				{
					Assert.fail();
				}
				Thread.sleep(3000);
				logger.info("******Finished TC_003_LoginDDT********");
				
	}
}
