package org.fw.UdemyPG.pageObjects.android;

import org.fw.UdemyPG.Utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class FormPage extends AndroidActions{

// Constructor 
	
	AndroidDriver driver;
	
	public FormPage (AndroidDriver driver) {
		super(driver);
		this.driver = driver;                					 // this refers to local variable or current class variable.
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
// PageFactory Design.
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text = 'Male']")
	private WebElement maleOption;
	
	@AndroidFindBy(id = "android:id/text1")
	private WebElement countrySelection;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement BtnLetsShop;
	
	
// Performing actions.
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
	}
	
	public void setGender(String gender) {
		
		if(gender.contains("female")) {
			femaleOption.click();
		}
		else {
			maleOption.click();
		}
	}
	
	public void setCountrySelection(String countryName) {
		
		countrySelection.click();
	//	Thread.sleep(2000);
		
		scrollToText(countryName);	
		driver.findElement(By.xpath("//android.widget.TextView[@text = '"+countryName+"']")).click();
		
	}
	
	public ProductCatalogue submitForm() {
		BtnLetsShop.click();
		
		// create the object of next landing page here only and return the same.
		return new ProductCatalogue(driver);
		
	}
	
	public void setActivity() {
		
		  Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity"); 
		  driver.startActivity(activity); 
		  
	}


	

}
