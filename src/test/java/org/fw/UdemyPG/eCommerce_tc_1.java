package org.fw.UdemyPG;

import java.net.MalformedURLException;

import org.fw.UdemyPG.TestUtils.AndroidBaseTest;
import org.fw.UdemyPG.pageObjects.android.CartPage;
import org.fw.UdemyPG.pageObjects.android.ProductCatalogue;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_1 extends AndroidBaseTest{
	
		
	@Test
	public void FillForm() throws MalformedURLException, InterruptedException {
		
		// FormPage formPage = new FormPage(driver);
		
		formPage.setNameField("Prashant");
		formPage.setGender("female");
		formPage.setCountrySelection("Argentina");
		ProductCatalogue productCatalogue = formPage.submitForm();    // catching the return landed page.
			
		System.out.println(driver.findElement(By.xpath("//android.widget.TextView[@text='Products']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//android.widget.TextView[@text='Products']")).getText(), "Products");
		
	//	ProductCatalogue productCatalogue = new ProductCatalogue(driver);  // now no need to create the object of the new page.
		productCatalogue.addItemtoCartByIndex(0);
		productCatalogue.addItemtoCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(
		 * "com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));
		 */
		
		double totalSum = cartPage.GetProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		
		
		Assert.assertEquals(totalSum, displayFormattedSum);
		
		cartPage.acceptTermConditions();
		cartPage.submitOrder();
				
	}
	
}
