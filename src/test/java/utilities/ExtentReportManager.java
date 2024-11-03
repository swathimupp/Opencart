package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbase.BaseClass;

public class ExtentReportManager {

	public ExtentSparkReporter sparkReporter;//UI of the report(theme,location of the info)
	public ExtentReports extent;//populate common info like project name,module name
	public ExtentTest test;//creating test case entries in the report and update the status of the methods like failure,skipped
	
	String repName;
	public void OnStart(ITestContext testcontext)
	{
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);*/
		//Instead of 3 lines code created single line
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
		
		sparkReporter.config().setDocumentTitle("Automation Report");//Title of the report
		sparkReporter.config().setReportName("Functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
	
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);//connection to the spark reporter
		//combine UI and populate info
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		//extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		//extent.setSystemInfo("Tester Name", "Swathi");
		
		//capture these details from xml file instead of hardcoding the data
		String os=testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System",os);
		
		String browser=testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
        //If you run grouping.xml file will capture the include groups from xml file	
		List<String> includeGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())//if it is not empty then specify group info in report
		{
			extent.setSystemInfo("Groups", includeGroups.toString());//print them in report
		}
	}
	
      public void onTestSuccess(ITestResult result)
      {
    	  //display the class name
    	  test=extent.createTest(result.getTestClass().getName());//create a new entry in the report
    	  //name of the method dynamically and create entry in the report
    	  //getting the method from the testclass and display the groups
    	  test.assignCategory(result.getMethod().getGroups());//to display groups in report
    	  test.log(Status.PASS, "Test Case passed is:"+result.getName()+"got successfully executed");
    	  //getting the classname
    	  
      }
	
	public void OnTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, "Test case Failed is:"+result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {//base class driver and object related driver are same,so making driver as static
		String imgPath=new BaseClass().captureScreen(result.getName());//getting the name from thre result object
		test.addScreenCaptureFromPath(imgPath);//attach screenshot to the report
		//if screenshot is not taken properly will get filenot found exception so try catch block
		
		}catch(Exception e) {
			e.printStackTrace();//display exception message
		
		}
		
		
		//test.log(Status.FAIL,"Test case Failed cause is:"+result.getThrowable());
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());//creating new test entry in the report
		test.assignCategory(result.getMethod().getGroups());//getting the group info category wise
		test.log(Status.SKIP, "Test case Skipped is:"+result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context)
	{
		
		extent.flush();//will update entire in the report
		//to open the report the browser automatically
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
	    File extentReport=new File(pathOfExtentReport);
	
	    try {//if extent report is not avialbel try catch block used
	    Desktop.getDesktop().browse(extentReport.toURI());
	    
	    } catch(IOException e) {
	    	e.printStackTrace();
	    }
	  //create the email message
	  /*  URL url=new URL("file:///"+System.getProperty("user.dir"))+"\\reports\\"+repName);
	    ImageHtmlEmail email=new ImageHtmlEmail();//add dependency apachecoomons email
	    email.setDataSourceResolver(new DataSourceResolver(url));
	    email.setHostName("smtp.googlemail.com");//this keeps changing
	    email.setSmtpPort(465);
	    email.setAuthenticator(new DefaultAuthenticator("pavanoltarining@gmail.com", "password"));
	    //who is sending email
	    email.setSSLOnConnect(true);
	    email.setFrom("pavanoltraining@gmail.com");
	    email.setSubject("Test Results");
	    email.setMsg("Please find attached Report");
	    email.addTo("pavanobusy@gmail.com");//distribution list
	    email.attach(url,"extent report","please check report...");
	    email.send();*/
	    
	}
	
	
	
	
	
}


