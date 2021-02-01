package methods;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import driver.DriverScript;

public class AppIndependentMethods extends DriverScript {
	
	
	
	/***********************************************************
	 * Method Name			: readConfigData()
	 * Author Name			: 
	 * Purpose				: IT is to read the global test data from the /Configuration/config.properties file
	 * Arguments			: String strKey
	 * Return Type			: String
	 * 
	 ************************************************************/
	
	public String readConfigData(String strKey)
	{
		FileInputStream fin=null;
		Properties prop=null;
		try
		{
			fin=new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\Config.properties");
			prop=new Properties();
			prop.load(fin);
			
			return prop.getProperty(strKey);
			
		}catch(Exception e)
		{
			System.out.println("Exception in 'readConfigData()' method "+e.getMessage());
			return null;
		}
		finally
		{
			try
			{
				fin.close();
				fin=null;
				prop=null;
			}catch(Exception e)
			{
				System.out.println("Exception in 'redConfigData()' method "+e.getMessage());
				return null;
			}
		}
	}
	
	
	/***********************************************************
	 * Method Name			: getDateTime()
	 * Author Name			: 
	 * Purpose				: IT is to read the global test data from the /Configuration/config.properties file
	 * Arguments			: String strKey
	 * Return Type			: String
	 * 
	 ************************************************************/
	
	public String getDateTime(String strDateFormat)
	{
		Date dt=null;
		SimpleDateFormat sdf=null;
		try
		{
		   dt=new Date();
		   sdf=new SimpleDateFormat(strDateFormat);
		   return sdf.format(dt);
		}catch(Exception e)
		{
			System.out.println("Exception in 'getDateTime()' method "+e.getMessage());
			return null;
		}
		finally
		{
			dt=null;
			sdf=null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name		: LaunchBrowser()
	 * Purpose			:
	 * Author			:
	 * Reviewer			:
	 * Date Creation	:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 * *********************************************
	 */

	public WebDriver launchBrowser(String browserName, ExtentTest test)
	{
		WebDriver oBrowser=null;
		
		try
		{
			switch(browserName.toLowerCase())
			{
			    case "chrome" :
			    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Library\\Drivers\\chromedriver.exe");
			    	oBrowser=new ChromeDriver();
			    	break;
			    	
			    case "firefox":
			    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"");
			    	oBrowser=new FirefoxDriver();
			        break;
			
			    case "ie":
			    	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"");
			    	oBrowser=new  InternetExplorerDriver();
			    	break;
			    	
			    default:
			    	reports.writeResult(oBrowser, "fail", "invalid browser tyepe '"+browserName+"'specified", test);
			    	
			}
			
			if(oBrowser!=null)
			{
				reports.writeResult(oBrowser,"pass", "The browser '"+oBrowser+"' launched successfully",test);
				return oBrowser;
			}else
			{
				reports.writeResult(null,"fail","The browser '"+oBrowser+"' failed to launch",test);
				return null;
			}
			
		}catch(Exception e)
		{
			reports.writeResult(null,"Exception","Exception in 'launchBrowser()' method "+e.getMessage(),test);
			return null;
		}
		finally
		{
			oBrowser=null;
		}
	}
	
	/*****************************************************
	 * Method Name		: CloseBrowser()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	public boolean CloseBrowser(WebDriver oBrowser,ExtentTest test)
	{
		
		try
		{
			oBrowser.close();
			return true;
		}catch(Exception e)
		{
			reports.writeResult(null,"Exception","Exception in 'Closebrowser()' Method "+e.getMessage(),test);
			return false;
		}
		
		finally
		{
			oBrowser=null;
		}
	}
	
	/*****************************************************
	 * Method Name		: clickObject()
	 * Purpose			: 
	 * Author			:
	 * Reviewer			:
	 * Date Creation 	:
	 * Date Modified	:
	 * Modified by 		:
	 * Parameters 		:
	 * Return Type		:
	 * ***************************************************
	 */
	
	public boolean clickObject(WebDriver oBrowser, By objby, ExtentTest test)
	{
		List<WebElement> oEles=null;
		try
		{
			oEles=oBrowser.findElements(objby);
			if(oEles.size()>0)
			{
				oEles.get(0).click();
				reports.writeResult(oBrowser,"pass","The element was '"+String.valueOf(objby)+"' clicked successfully",test);
				return true;
			}
			else
			{
				reports.writeResult(oBrowser,"fail","The elsement was '"+String.valueOf(objby)+"' failed to click",test);
			    return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","exception in 'clickObject()' method "+e.getMessage(),test);
			return false;
		}
		finally
		{
			oEles=null;
		}
	}
	
	
	/*****************************************************
	 * Method Name		: clickObject()
	 * Purpose			: 
	 * Author			:
	 * Reviewer			:
	 * Date Creation 	:
	 * Date Modified	:
	 * Modified by 		:
	 * Parameters 		:
	 * Return Type		:
	 * ***************************************************
	 */
	
	public boolean clickObject(WebDriver oBrowser, String strObjectName, ExtentTest test)
	{
		List<WebElement> oEles=null;
		try
		{
			oEles=oBrowser.findElements(By.xpath(strObjectName));
			if(oEles.size()>0)
			{
				oEles.get(0).click();
				reports.writeResult(oBrowser,"pass","The element was '"+strObjectName+"' clicked successfully",test);
				return true;
			}
			else
			{
				reports.writeResult(oBrowser,"fail","The elsement was '"+strObjectName+"' failed to click",test);
			    return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","exception in 'clickObject()' method "+e.getMessage(),test);
			return false;
		}
		finally
		{
			oEles=null;
		}
	}
	
	/*****************************************************
	 * Method Name		: setObject()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	public boolean setObject(WebDriver  oBrowser, By objby, String strData,ExtentTest test)
	{
		List<WebElement> oEles=null;
		try
		{
			oEles=oBrowser.findElements(objby);
			
			if(oEles.size()>0)
			{
				oEles.get(0).sendKeys(strData);
				reports.writeResult(oBrowser,"pass","The data '"+strData+"' was entered successfully",test);
				return true;
			}
			else
			{
				reports.writeResult(oBrowser,"fail","The data '"+strData+"' was failed to enter",test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'setObject()' method "+e.getMessage(),test);
			return false;
		}finally
		{
			
		}
	}
	
	
	/*****************************************************
	 * Method Name		: setObject()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	public boolean setObject(WebDriver  oBrowser, String strObjectName, String strData,ExtentTest test)
	{
		List<WebElement> oEles=null;
		try
		{
			oEles=oBrowser.findElements(By.xpath(strObjectName));
			
			if(oEles.size()>0)
			{
				oEles.get(0).sendKeys(strData);
				reports.writeResult(oBrowser,"pass","The data '"+strData+"' was entered successfully",test);
				return true;
			}
			else
			{
				reports.writeResult(oBrowser,"fail","The data '"+strData+"' was failed to enter",test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'setObject()' method "+e.getMessage(),test);
			return false;
		}finally
		{
			
		}
	}
	
	
	/*****************************************************
	 * Method Name		: clearandsetObject()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	public boolean clearAndSetObject(WebDriver oBrowser, By objby, String strData, ExtentTest test)
	{
		List<WebElement> oEles=null;
 		try
		{
			oEles=oBrowser.findElements(objby);
			
			if(oEles.size()>0)
			{
				oEles.get(0).clear();
				oEles.get(0).sendKeys(strData);
				reports.writeResult(oBrowser,"pass","The data '"+strData+"' clear and set successfully",test);
				return true;
			}else
			{
				reports.writeResult(oBrowser,"fail","The data '"+strData+"' failed to clear and set ",test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'clearAndsetObject()' method "+e.getMessage(),test);
			return false;
		}
	}
	
	
	/*****************************************************
	 * Method Name		: clearandsetObject()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	public boolean clearAndSetObject(WebDriver oBrowser, String strObjectName, String strData, ExtentTest test)
	{
		List<WebElement> oEles=null;
 		try
		{
			oEles=oBrowser.findElements(By.xpath(strObjectName));
			
			if(oEles.size()>0)
			{
				oEles.get(0).clear();
				oEles.get(0).sendKeys(strData);
				reports.writeResult(oBrowser,"pass","The data '"+strData+"' clear and set successfully",test);
				return true;
			}else
			{
				reports.writeResult(oBrowser,"fail","The data '"+strData+"' failed to clear and set ",test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'clearAndsetObject()' method "+e.getMessage(),test);
			return false;
		}
	}
	
	/*****************************************************
	 * Method Name		: ComapareValue()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	public boolean compareValue(WebDriver oBrowser,String actual, String expected,ExtentTest test)
	{
		try
		{
			if(actual.equalsIgnoreCase(expected))
			{
				reports.writeResult(oBrowser,"pass","The actual '"+actual+"' and expected '"+expected+"' values are matching",test);
			return true;
			}
			else
			{
				reports.writeResult(oBrowser,"fail","The actual '"+actual+"' and expected '"+expected+"' values are not matching",test);
				return false;
			}
			
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'compareValue()' method "+e.getMessage(),test);
			return false;
		}
	}
	
	
	/*****************************************************
	 * Method Name		: verifyText()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	public boolean VerifyText(WebDriver oBrowser, By objby, String objType, String Expected,ExtentTest test)
	{
		List<WebElement> oEles=null;
		String actual=null;
		Select osel=null;
		
		try
		{
			oEles=oBrowser.findElements(objby);
			if(oEles.size()>0)
			{

				switch(objType.toLowerCase())
				{
				   case "text" :
					   actual=oEles.get(0).getText();
					   break;
					   
				   case "value":
				       actual=oEles.get(0).getAttribute("value");
				       break;
				       
				   case "dropdown":
					   osel=new Select(oEles.get(0));
					   actual=osel.getFirstSelectedOption().getText();
					   break;
				  
					   default:
						   reports.writeResult(oBrowser,"fail","Invalid objectType '"+objType+"' was specified",test);
						   return false;
				}
				
				if(appInd.compareValue(oBrowser, actual, Expected, test))
				{
					return true;
				}else
				{
					return false;
				}
			
			}
			else
			{
				reports.writeResult(oBrowser,"pass","The element "+String.valueOf(objby)+"' was not found in the DOM.",test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'VerifyText()' method "+e.getMessage(),test);
			return false;
		}
		finally
		{
			oEles=null;
			osel=null;
			actual=null;
		}
	}
	
	
	/*****************************************************
	 * Method Name		: verifyText()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	public boolean VerifyText(WebDriver oBrowser, String strObjectName, String objType, String Expected,ExtentTest test)
	{
		List<WebElement> oEles=null;
		String actual=null;
		Select osel=null;
		
		try
		{
			oEles=oBrowser.findElements(By.xpath(strObjectName));
			if(oEles.size()>0)
			{

				switch(objType.toLowerCase())
				{
				   case "text" :
					   actual=oEles.get(0).getText();
					   break;
					   
				   case "value":
				       actual=oEles.get(0).getAttribute("value");
				       break;
				       
				   case "dropdown":
					   osel=new Select(oEles.get(0));
					   actual=osel.getFirstSelectedOption().getText();
					   break;
				  
					   default:
						   reports.writeResult(oBrowser,"fail","Invalid objectType '"+objType+"' was specified",test);
						   return false;
				}
				
				if(appInd.compareValue(oBrowser, actual, Expected, test))
				{
					return true;
				}else
				{
					return false;
				}
			
			}
			else
			{
				reports.writeResult(oBrowser,"pass","The element "+strObjectName+"' was not found in the DOM.",test);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'VerifyText()' method "+e.getMessage(),test);
			return false;
		}
		finally
		{
			oEles=null;
			osel=null;
			actual=null;
		}
	}
	
	/*****************************************************
	 * Method Name		: VerifyElementExists()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	
	public boolean VerifyElementExists(WebDriver oBrowser,By objby,ExtentTest test)
	{
		List<WebElement> oEles=null;
		try
		{
			oEles=oBrowser.findElements(objby);
			
			if(oEles.size()>0)
			{
				reports.writeResult(oBrowser,"pass","The webelement '"+String.valueOf(objby)+"' was present on the DOM",test);
				return true;
			}
			else
			{
				reports.writeResult(oBrowser,"fail","The webelement '"+String.valueOf(objby)+"' was not present on the DOM",test);
			    return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in VerifyText() method "+e.getMessage(),test);
			return false;
		}
		finally
		{
			oEles=null;
		}
	}
	
	
	/*****************************************************
	 * Method Name		: VerifyElementExists()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	
	public boolean VerifyElementExists(WebDriver oBrowser,String strObjectName,ExtentTest test)
	{
		List<WebElement> oEles=null;
		try
		{
			oEles=oBrowser.findElements(By.xpath(strObjectName));
			
			if(oEles.size()>0)
			{
				reports.writeResult(oBrowser,"pass","The webelement '"+strObjectName+"' was present on the DOM",test);
				return true;
			}
			else
			{
				reports.writeResult(oBrowser,"fail","The webelement '"+strObjectName+"' was not present on the DOM",test);
			    return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in VerifyText() method "+e.getMessage(),test);
			return false;
		}
		finally
		{
			oEles=null;
		}
	}
	
	
	/*****************************************************
	 * Method Name		: VerifyElementNotExists()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	
	public boolean VerifyElementNotExists(WebDriver oBrowser,By objby,ExtentTest test)
	{
		List<WebElement> oEles=null;
		try
		{
			oEles=oBrowser.findElements(objby);
			
			if(oEles.size()>0)
			{
				reports.writeResult(oBrowser,"fail","The webelement '"+String.valueOf(objby)+"' was present on the DOM",test);
				return false;
			}
			else
			{
				reports.writeResult(oBrowser,"pass","The webelement '"+String.valueOf(objby)+"' was not present on the DOM",test);
			    return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in VerifyText() method "+e.getMessage(),test);
			return false;
		}
		finally
		{
			oEles=null;
		}
	}
	
	
	/*****************************************************
	 * Method Name		: VerifyElementNotExists()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	
	public boolean VerifyElementNotExists(WebDriver oBrowser,String strObjectName,ExtentTest test)
	{
		List<WebElement> oEles=null;
		try
		{
			oEles=oBrowser.findElements(By.xpath(strObjectName));
			
			if(oEles.size()>0)
			{
				reports.writeResult(oBrowser,"fail","The webelement '"+strObjectName+"' was present on the DOM",test);
				return false;
			}
			else
			{
				reports.writeResult(oBrowser,"pass","The webelement '"+strObjectName+"' was not present on the DOM",test);
			    return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in VerifyText() method "+e.getMessage(),test);
			return false;
		}
		finally
		{
			oEles=null;
		}
	}
	
	/*****************************************************
	 * Method Name		: VerifyOptionalElement()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	public boolean verifyOptionalElement(WebDriver oBrowser, By objby, ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(objby);
			
			if(oEles.size() > 0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'verifyOptionalElement()' method. "+ e.getMessage(),test);
			return false;
		}
		finally{
			oEles = null;
		}
	}
	
	
	/*****************************************************
	 * Method Name		: VerifyOptionalElement()
	 * Purpose			:
	 * Author			:
	 * reviewer			:
	 * Date Modified	:
	 * Modified by		:
	 * Parameters		:
	 * Return Type		:
	 ***************************************************
	 */
	
	public boolean verifyOptionalElement(WebDriver oBrowser, String strObjectName , ExtentTest test)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oBrowser.findElements(By.xpath(strObjectName));
			
			if(oEles.size() > 0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oBrowser,"Exception","Exception in 'verifyOptionalElement()' method. "+ e.getMessage(),test);
			return false;
		}
		finally{
			oEles = null;
		}
	}
	
	
	public boolean waitFor(WebDriver oBrowser, String strObjectName, String waitReason, String value, int timeOut)
	{
		WebDriverWait oWait=null;
		try
		{
		   	oWait=new WebDriverWait(oBrowser, timeOut);
		   	switch(waitReason.toLowerCase())
		   	{
		   	     case "visible":
		   	    	 oWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(strObjectName)));
		   	    	 break;
		   	    	 
		   	     case "clickable":
		   	    	 oWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strObjectName)));
		   	    	 break;
		   	    	 
		   	     case "text":
		   	    	 oWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(strObjectName), value));
		   	    	 break;
		   	    	 
		   	     case "value":
		   	    	 oWait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(strObjectName), value));
		   	    	 break;
		   	    	 
		   	     case "invisibility":
		   	    	 oWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(strObjectName)));
		   	    	 break;
		   	    	 
		   	    	 default:
		   	    		 System.out.println("Invalid Condition '"+waitReason+"' for wait");
		   	    	 
		   	}
		   	return true;
		}catch(Exception e)
		{
			System.out.println("Exception in 'waitFor()' method "+e.getMessage());
			return false;
		}
	}
	
	
	public boolean waitFor(WebDriver oBrowser, By objby, String waitReason, String value, int timeOut)
	{
		WebDriverWait oWait=null;
		try
		{
		   	oWait=new WebDriverWait(oBrowser, timeOut);
		   	switch(waitReason.toLowerCase())
		   	{
		   	     case "visible":
		   	    	 oWait.until(ExpectedConditions.presenceOfElementLocated(objby));
		   	    	 break;
		   	    	 
		   	     case "clickable":
		   	    	 oWait.until(ExpectedConditions.elementToBeClickable(objby));
		   	    	 break;
		   	    	 
		   	     case "text":
		   	    	 oWait.until(ExpectedConditions.textToBePresentInElementLocated(objby, value));
		   	    	 break;
		   	    	 
		   	     case "value":
		   	    	 oWait.until(ExpectedConditions.textToBePresentInElementValue(objby, value));
		   	    	 break;
		   	    	 
		   	     case "invisibility":
		   	    	 oWait.until(ExpectedConditions.invisibilityOfElementLocated(objby));
		   	    	 break;
		   	    	 
		   	    	 default:
		   	    		 System.out.println("Invalid Condition '"+waitReason+"' for wait");
		   	    	 
		   	}
		   	return true;
		}catch(Exception e)
		{
			System.out.println("Exception in 'waitFor()' method "+e.getMessage());
			return false;
		}
	}

}
