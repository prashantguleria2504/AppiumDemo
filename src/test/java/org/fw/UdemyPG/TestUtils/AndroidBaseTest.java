package org.fw.UdemyPG.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.fw.UdemyPG.Utils.AppiumUtils;
import org.fw.UdemyPG.pageObjects.android.FormPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils{
	
	
	public AndroidDriver driver;
	public FormPage formPage;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void configureAppium() throws IOException {

// Starting the Appium Server.
		
		// getting the details from the global properties file.
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
				
		service = startAppiumServer(ipAddress, Integer.parseInt(port));   // converting port string to integer.
		
// Starting the Appium Server -- END
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setApp("C:\\Users\\004438744\\Desktop\\IBM\\Full Stack Testing Training\\Appium_Self\\Files\\PractiseApp\\General-Store.apk");
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		
		options.setChromedriverExecutable("C:\\Users\\004438744\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

// If server is started via service then service already knows the url so updating above (53) as:
		//driver = new AndroidDriver(service.getUrl(),options);
				
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
		formPage = new FormPage(driver);
		
	}
	
	@AfterClass
	public void testdown() {
		
		driver.quit();
	}
	
	
	/*
	 * public double GetFormattedAmount(String actualAmount) {
	 * 
	 * String b = actualAmount.substring(1); double amountActual =
	 * Double.parseDouble(b); return amountActual; }
	 */
	 
	
	public void LongPressAction(WebElement tc) {
		
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",((RemoteWebElement)tc).getId(),"duration",2000));
	}

}
