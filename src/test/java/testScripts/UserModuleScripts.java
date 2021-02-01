package testScripts;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import driver.DriverScript;

public class UserModuleScripts extends DriverScript {
	
	/***********************************************************
	 * Script Name			: TS_LoginLogout()
	 * Author Name			: 
	 * Purpose				: Automated the test case user_101
	 * Arguments			: NA
	 * Return Type			: boolean
	 * 
	 ************************************************************/
	
	public boolean TS_LoginLogout()
	{
		WebDriver oBrowser=null;
		Map<String,String>objData=null;
		String strStatus=null;
		
		try
		{
			test=extent.startTest("TS_Login_Logout()");
			objData=datatable.getTestDataFromExcel("userModule", "user_101", test);
			oBrowser=appInd.launchBrowser(objData.get("Browser"), test);
			strStatus+=appDep.NavigateUrl(oBrowser, appInd.readConfigData("URL"), test);
			strStatus+=appDep.LoginToApp(oBrowser, objData.get("UserName"), objData.get("Password"), test);
			//strStatus+=appDep.Logout(oBrowser, test);
			
			
			if(strStatus.contains("false"))
			{
				reports.writeResult(oBrowser, "fail", "The testScript 'TS_Login_Logout()' failed", test);
				return false;
			}
			else
			{
				reports.writeResult(oBrowser, "pass", "The testScript 'TS_Login_Logout()' passed", test);
				return true;
			}
			
		}catch(Exception e)
		{
			System.out.println("Exception in 'TS_Login_Logout()' method "+e.getMessage());
			return false;
		}
		finally
		{
			appInd.CloseBrowser(oBrowser, test);
			reports.endExtentReport(test);
			objData=null;
		}
	}

	
	
}
