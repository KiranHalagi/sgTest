package methods;


import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import driver.DriverScript;
import locators.ObjectLocators;

public class AppDependentMethods extends DriverScript implements ObjectLocators{
	
	
	 /****************************************************************
		 * Method Name		: NavigateUrl()
		 * Purpose			:
		 * Author			:
		 * Reviewer			:
		 * Date Creation	:
		 * Date Modified	:
		 * Modified by		:
		 * Parameters		:
		 * Return Type		:
		 *
		 ***************************************************************/
	 public boolean NavigateUrl(WebDriver oBrowser, String URL, ExtentTest test)
	 {
		 try
		 {
			 oBrowser.get(URL);
			 Thread.sleep(2000);
			 oBrowser.manage().window().maximize();
			 
			 if(appInd.compareValue(oBrowser, oBrowser.getTitle(), "actiTIME - Login", test))
			 {
				 return true;
			 }else
			 {
				 return false;
			 }
			 
		 }catch(Exception e)
		 {
			 reports.writeResult(oBrowser,"Exception","Exception in 'NaviagteUrl()' method "+e.getMessage(),test);
			 return false;
		 }
	 
	 }
	 
	 /****************************************************************
		 * Method Name		: LoginToApp()
		 * Purpose			:
		 * Author			:
		 * Reviewer			:
		 * Date Creation	:
		 * Date Modified	:
		 * Modified by		:
		 * Parameters		:
		 * Return Type		:
		 *
		 ***************************************************************/

	 public boolean LoginToApp(WebDriver oBrowser,String username, String Password, ExtentTest test)
	 {
		 String strStatus=null;
		 
		 try
		 {
			 
			 strStatus+=appInd.setObject(oBrowser, obj_UserName_Edit, username, test);
			 strStatus+=appInd.setObject(oBrowser, obj_Password_Edit, Password, test);
			 reports.writeResult(oBrowser, "screenshot", "LoginPage", test);
			 strStatus+=appInd.clickObject(oBrowser, obj_Login_Link, test);
			 appInd.waitFor(oBrowser, obj_HomePage_Title_Label, "Text", "Enter Time-Track", 10);
			
			 
			   strStatus+=appInd.VerifyText(oBrowser, obj_HomePage_Title_Label, "Text", "Enter Time-Track", test);
			   reports.writeResult(oBrowser, "screenshot", "Login Successful Page", test);
			 
				 if(appInd.verifyOptionalElement(oBrowser, obj_GettingStartedShortcut_Window, test))
					{
					    strStatus+=appInd.clickObject(oBrowser, obj_GettingStartedShortcut_Close_Btn, test);
						
					}
			 
			 if(strStatus.contains("false"))
			 {
				 reports.writeResult(oBrowser,"fail","Failed to login to application",test);
				 return false;
			 }else
			 {
				 reports.writeResult(oBrowser,"pass","Login is successfully",test);
				 return true;
			 }
			
		 }catch(Exception e)
		 {
			 reports.writeResult(oBrowser,"Exception","Exception in 'LoginToApp()' method "+e.getMessage(),test); 
			return false;
		 }
		 
	 }
	 
	 
		 


	

	/***************************************************************
	 * Method Name		: Logout()
	 * Author			:
	 * Modified by		:
	 *  
	 * *************************************************************
	 */


	public boolean  Logout(WebDriver oBrowser, ExtentTest test)
	{
		String strStatus =null;
	   try
	   {
		  
		  strStatus+= appInd.VerifyText(oBrowser, obj_Login_Header_Text, "text", "Please identify yourself", test);
			
			
			
			if(strStatus.contains("false"))
			{
				reports.writeResult(oBrowser, "fail","Failed to logout from the app", test);
				return false;
			}else
			{
				reports.writeResult(oBrowser, "pass","Logout is successful", test);
				return true;
				
			}
	   }catch(Exception e)
	   {
		   reports.writeResult(oBrowser, "Exception","Exception in 'LogOut()' method "+e.getMessage(), test);
		   return false;
	   }
	}
	
	
	
	
}
