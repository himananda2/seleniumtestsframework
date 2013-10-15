package com.seleniumtests.driver;

import com.seleniumtests.core.TestLogging;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class BaseWebUtil {
	private WebDriver driver;
	
	public BaseWebUtil(WebDriver driver){
		this.driver = driver;
	}
	
	public void resizeWindow(int width, int height) {
		try {
			TestLogging.logWebStep(null, "Resize browser window to width " + width
                    + " height " + height, false);
			Dimension size = new Dimension(width, height);
			driver.manage().window().setPosition(new Point(0, 0));
			driver.manage().window().setSize(size);
		} catch (Exception ex) {
		}
	}

	public void maximizeWindow() {
		try {
			BrowserType browser = BrowserType.getBrowserType(WebUIDriver
					.getWebUXDriver().getBrowser());
			if (browser == BrowserType.Android || browser == BrowserType.IPad
					|| browser == BrowserType.IPhone)
				return;

			Dimension oldDimension = driver.manage().window().getSize();
			driver.manage().window().maximize();
			Dimension dimension = driver.manage().window().getSize();
		} catch (Exception ex) {

			try {
				((JavascriptExecutor) driver)
						.executeScript("if (window.screen){window.moveTo(0, 0);window.resizeTo(window.screen.availWidth,window.screen.availHeight);}");
			} catch (Exception ignore) {
				TestLogging.log("Exception when maximizing browser window"
                        + ignore.getMessage());
			}
		}
	}
	
	public static void main(String[] args){
		WebUIDriver.getWebUXDriver().setMode("ExistingGrid");
		WebUIDriver.getWebUXDriver().setHubUrl(" ");
		WebDriver driver = WebUIDriver.getWebDriver(true);
		System.out.print(driver.manage().window().getSize().width + ":"+driver.manage().window().getSize().height);
	}

}