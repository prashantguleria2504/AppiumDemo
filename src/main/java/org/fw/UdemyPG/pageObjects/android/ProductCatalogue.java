package org.fw.UdemyPG.pageObjects.android;

import java.util.List;

import org.fw.UdemyPG.Utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidActions {
	

//Constructor
	
	AndroidDriver driver;
	
	public ProductCatalogue(AndroidDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	public List<WebElement> addToChartBtn;                                         // will be considered as findelements.
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cart;
	
	
// Actions
	
	public void addItemtoCartByIndex(int index) {
		
		addToChartBtn.get(index).click();
	}
	
	public CartPage goToCartPage() throws InterruptedException {
		
		cart.click();
		Thread.sleep(2000);
		
		return new CartPage(driver);
	}
	

}
