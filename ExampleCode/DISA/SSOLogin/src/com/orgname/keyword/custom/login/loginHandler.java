package com.orgname.keyword.custom.login;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.siebel.automation.keywordFramework.KeywordActionHandler;

/*
 * Description: The following sample code assumes that the webpage has three fields:
 * 	input with name attribute as "username"
 *  input with name attribute as "password"
 *  input/button with value as "Login"
 * 
 * The class loginHandler updates the field with values and performs click action on the Login button.
 * 
 * Sample code below does not handle any error scenario and hence performs no corrective action. 
 * 
 * This code only for educational purpose.
 */

public class loginHandler implements KeywordActionHandler{

	@Override
	public boolean ExecuteOperation(
			WebDriver driver,ArrayList<String> inputvariables,
			ArrayList<String> outvariables) {
		
		String USERID = "<PROVIDE_USERID>";
		String PASSWORD = "<PROVIDE_PASSWORD>";
	
		driver
			.findElement(
					By.xpath("//input[@name='username']"))
			.sendKeys( USERID );
		
		driver
			.findElement(
					By.xpath("//input[@name='password']"))
			.sendKeys( PASSWORD );
		
		driver
			.findElement(
					By.xpath("//input[@value='Login']"))
			.click();
		
		return true;
	}
}



