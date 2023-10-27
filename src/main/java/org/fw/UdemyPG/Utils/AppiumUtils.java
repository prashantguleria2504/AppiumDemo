package org.fw.UdemyPG.Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {

	/*
	 * AppiumDriver driver; // since this class is inherited both by Android & ios
	 * so will use Appium driver. public AppiumUtils(AndroidDriver driver) {
	 * 
	 * this.driver = driver; }
	 */
	
	public AppiumDriverLocalService service;
	
	public double GetFormattedAmount(String actualAmount) {
		
		String b = actualAmount.substring(1);
		double amountActual = Double.parseDouble(b);
		return amountActual;
	}
	

	public void waitForElementToAppear(WebElement ele, AppiumDriver driver ) {
		
		
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.attributeContains((ele),"text", "Cart"));
		 
		
	}
	
	// Common Utility to read JSON 
	
	public List<HashMap<String,String>> getJsonData(String jsonFilePath) throws IOException {
		
		// System.getProperty("user.dir")+"\\src\\test\\java\\org\\fw\\UdemyPG\\TestData\\eCommerce.json"
		// convert json file to json string
		
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath));
		
		// convert json string to HashMap
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;
	}
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
						
		// Start the Appium Server. Use class AppiumServiceBuilder, and tell where is main.js file.
		
		service = new AppiumServiceBuilder()
					.withAppiumJS(new File("C:\\Users\\004438744\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					//.usingAnyFreePort().build()
					.withIPAddress(ipAddress).usingPort(port).build();
		service.start();
			
		return service;
	
	}

}
