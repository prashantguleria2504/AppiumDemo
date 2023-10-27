package org.fw.UdemyPG;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.fw.UdemyPG.TestUtils.AndroidBaseTest;
import org.fw.UdemyPG.pageObjects.android.CartPage;
import org.fw.UdemyPG.pageObjects.android.ProductCatalogue;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class eCommerce_tc_3_UsingHashMap extends AndroidBaseTest{
	
	

	  @BeforeMethod	
	  public void preSetup() {
	  
		  formPage.setActivity();
	  
	  }

	
	@Test(dataProvider = "getDataFromJson")
	public void FillForm(HashMap<String,String> input) throws MalformedURLException, InterruptedException {
		
		// FormPage formPage = new FormPage(driver);
		
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
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
	
	
	@Test
	public void VerifyErrorToastMessage() throws MalformedURLException, InterruptedException {
		
		// anyone who wants to implement toast message have to use tagName "android.widget.Toast", and for toast msg instead of text we have Name.
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String error = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		System.out.println(error);
		Thread.sleep(2000);
		Assert.assertEquals(error, "Please enter your name");
		
		Thread.sleep(2000);

	}
	
	// read data from json file and give as input.
	
	/*
	 * Parse Json file --> Json String (using Common-io)
	 * json String --> Hash Map (using Jackson databind)
	 * HashMap --> Test Case (TestNG data Provider)
	 */
	
	@DataProvider            // returns 2 dimensional array.
	public Object[][] getDataFromJson() throws IOException {
		
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\fw\\UdemyPG\\TestData\\eCommerce.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		// return new Object[][] {{data.get(0)}};
	}
	
	 
	
}
