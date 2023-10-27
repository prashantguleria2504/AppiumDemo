package org.fw.UdemyPG.pageObjects.android;

import java.util.List;

import org.fw.UdemyPG.Utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.internal.BaseTestMethod;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	// Constructor

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	public WebElement termsBtn;

	@AndroidFindBy(id = "android:id/button1")
	public WebElement acceptBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	public WebElement proceedBtn;

	@AndroidFindBy(className = "android.widget.CheckBox")
	public WebElement checkBox;

// Actions

	public List<WebElement> getProductList() {
		return productList;
	}

	public double GetProductsSum() {

		int count = productList.size();
		double Totalsum = 0;
		for (int i = 0; i < count; i++) {

			String amount = productList.get(i).getText(); // $160.07
			String a = amount.substring(1); // removed dollar $
			Double price = Double.parseDouble(a); // converted String to double.
			Totalsum = (Double) (Totalsum + price);

		}
		System.out.println(Totalsum);
		return Totalsum;
	}

	
	  public double getTotalAmountDisplayed() {
	  
	  String actualAmount = totalAmount.getText(); 
	  double amountActual = GetFormattedAmount(actualAmount); 
	  return amountActual;
	  
	  }
	 

	public void acceptTermConditions() {

		LongPressAction(termsBtn);
		acceptBtn.click();
	}

	public void submitOrder() {

		checkBox.click();
		proceedBtn.click();

	}

//------------------------------------------		
	/*
	 * public double GetFormattedAmount(String actualAmount) {
	 * 
	 * String b = actualAmount.substring(1); double amountActual =
	 * Double.parseDouble(b); return amountActual; }
	 */

}
