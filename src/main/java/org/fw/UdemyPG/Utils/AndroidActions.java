package org.fw.UdemyPG.Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils{

	AndroidDriver driver;

// Constructor	
	public AndroidActions(AndroidDriver driver) {
		
		this.driver = driver;
	}
	

// Android Actions.
	
public void LongPressAction(WebElement ele) {
		
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),"duration",2000)); // RemoteWebElement is a upper hierachry class that supports any element either web or mobile element.
	}

// -------
	
public void ScrollToEndAction() {
		
		boolean canScrollMore;
		
		do
		{
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
		}while(canScrollMore);

	}

//-------
	
public void SwipeAction(WebElement firstImage, String dir ) {
		
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",((RemoteWebElement)firstImage).getId(),
			    "direction", dir,
			    "percent", 0.75
			));	
	
	}

//-------
	
public void DragAndDropGestureTest(WebElement source) {
		
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",((RemoteWebElement)source).getId(),"endX", 832, "endY", 738));
	}

//-------

public void scrollToText(String text) {
	
	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
}
		
}
