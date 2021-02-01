package methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import driver.DriverScript;
import locators.ObjectLocators;

public class UserModuleMethods extends DriverScript implements ObjectLocators {
	
	 /****************************************************************
	 * Method Name		: CreatUser() method
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

public String CreatUser(WebDriver oBrowser,Map<String,String>data,ExtentTest test)
{
	
	String userName = null;
	String strStatus=null;
   
 try
 {
	 strStatus+=appInd.clickObject(oBrowser, obj_Users_Menu, test);
		appInd.waitFor(oBrowser, obj_AddUser_Btn, "visible", "", 3);
		
		strStatus+=appInd.clickObject(oBrowser, obj_AddUser_Btn, test);
		appInd.waitFor(oBrowser, obj_AddUser_Btn, "visible", "", 3);
		
		 
		
		if(appInd.VerifyElementExists(oBrowser, obj_AddUser_Btn, test))
		{
		strStatus+=appInd.setObject(oBrowser, obj_FirstName_User_Edit, data.get("FirstName"), test);
		strStatus+=appInd.setObject(oBrowser, obj_LastName_User_Edit,  data.get("LastName"), test);
		strStatus+=appInd.setObject(oBrowser, obj_Email_User_Edit, data.get("Email"), test);
		strStatus+=appInd.setObject(oBrowser, obj_UserName_User_Edit,data.get("User_UN"), test);
		strStatus+=appInd.setObject(oBrowser, obj_Password_User_Edit, data.get("User_PWD"), test);
		strStatus+=appInd.setObject(oBrowser, obj_Retype_User_Edit, data.get("User_ReType"), test);
		
		strStatus+=appInd.clickObject(oBrowser, obj_CreateUser_Btn, test);
		
		userName = data.get("LastName")+", "+data.get("FirstName");
		appInd.waitFor(oBrowser, By.xpath("//span[text()='"+userName+"']"), "visible", "", 10);
		
		strStatus+=appInd.verifyOptionalElement(oBrowser, By.xpath("//span[text()='"+userName+"']"), test);
	   
		if(strStatus.contains("false"))	
	   {
			reports.writeResult(oBrowser,"fail","Failed to create the user",test);
			return null;
	    }
		
		else
		{
			reports.writeResult(oBrowser,"pass","User created successfully",test);
			return userName;	
		}
		
		}
		else
		{
			reports.writeResult(oBrowser,"fail","failed To Open The 'Add User' ",test);
			return null;
		}
	 
 }catch(Exception e)
 {
	 reports.writeResult(oBrowser, "Exception","Exception in 'CreateUser()' method "+e.getMessage(), test);
	 return null;
 }
}	 


public boolean DeleteUser(WebDriver oBrowser, String userName, ExtentTest test)
{
	String strStatus=null;
   
   try
   {   
	   strStatus+=appInd.clickObject(oBrowser, By.xpath("//div[@class='name']/span[text()='"+userName+"']"), test);
		appInd.waitFor(oBrowser, obj_DeleteUser_Btn, "clickable", null, 10 );
		
		strStatus+=appInd.clickObject(oBrowser, obj_DeleteUser_Btn, test);
		Thread.sleep(2000);
		
		oBrowser.switchTo().alert().accept();
		Thread.sleep(4000);
		
		strStatus+=appInd.VerifyElementNotExists(oBrowser, By.xpath("//div[@class='name']/span[text()='"+userName+"']"), test);
		
		if(strStatus.contains("false")){
			reports.writeResult(oBrowser, "fail","Failed to delete the user", test);
			return false;
		}else
		{
			reports.writeResult(oBrowser, "pass","The user was deleted successfully", test);
			return true;       
		} 
   }catch(Exception e)
   {
	   reports.writeResult(oBrowser, "Exception","Exception in 'DeleteUser()' method "+e.getMessage(), test);
	   return false;
   }
   
}

}
