import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Lists;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumSiiloMock {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");

		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");

		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");

		dc.setCapability("appPackage", "com.siilo.android.registration");

		dc.setCapability("appActivity", "com.siilo.android.registration.ui.activity.RegistrationActivity");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(url, dc);
		Thread.sleep(5000);

		/* Welcome screen - User clicks on New user for registration */
		driver.findElementById("com.siilo.android.registration:id/button_new_user").click();

		/* Terms screen - User clicks on I agree to proceed with registration */
		driver.findElementById("com.siilo.android.registration:id/button_yes_agree").click();

		/* Names screen - User enters valid First and last name */
		driver.findElementById("com.siilo.android.registration:id/reg_first_name").sendKeys("Dhanashree");
		driver.findElementById("com.siilo.android.registration:id/reg_last_name").sendKeys("Joshi");
		driver.findElementById("com.siilo.android.registration:id/button_done").click();

		/* Professions screen - User selects one of the listed professions */
		driver.findElementById("com.siilo.android.registration:id/profession_item_dentist").click();

		/* email screen - User enters email id */
		driver.findElementById("com.siilo.android.registration:id/reg_email").sendKeys("dhanashree.damle@gmail.com");
		driver.findElementById("com.siilo.android.registration:id/button_done").click();
        
		/* email confirmation screen     */
		List<WebElement> data = driver.findElementsById("com.siilo.android.registration:id/button_done");
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getText().equalsIgnoreCase("Next")) {
				data.get(i).click();
			}
		}

		
		/* Phone Number screen - Select country code and enter phone number */
		int retry = 0;
		while (driver.findElementsById("com.siilo.android.registration:id/reg_country_text").size() == 0 && retry < 3) {

			driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent()
					.withKey(io.appium.java_client.android.nativekey.AndroidKey.TAB)
					.withFlag(io.appium.java_client.android.nativekey.KeyEventFlag.SOFT_KEYBOARD)
					.withFlag(io.appium.java_client.android.nativekey.KeyEventFlag.CANCELED)
					.withFlag(io.appium.java_client.android.nativekey.KeyEventFlag.CANCELED_LONG_PRESS));
			retry++;
		}

		if (driver.findElementsById("com.siilo.android.registration:id/reg_country_text").size() == 0) {

			List<io.appium.java_client.android.nativekey.AndroidKey> numpad = Lists.newArrayList(
					io.appium.java_client.android.nativekey.AndroidKey.NUMPAD_1,
					io.appium.java_client.android.nativekey.AndroidKey.NUMPAD_2,
					io.appium.java_client.android.nativekey.AndroidKey.NUMPAD_3,
					io.appium.java_client.android.nativekey.AndroidKey.NUMPAD_4);
			numpad.forEach(keytoenter -> driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent()
					.withKey(keytoenter).withFlag(io.appium.java_client.android.nativekey.KeyEventFlag.SOFT_KEYBOARD)
					.withFlag(io.appium.java_client.android.nativekey.KeyEventFlag.CANCELED)
					.withFlag(io.appium.java_client.android.nativekey.KeyEventFlag.CANCELED_LONG_PRESS)));

		} else {
			driver.findElementsById("com.siilo.android.registration:id/reg_country_text").get(0).click();
			try {
			driver.findElementsById("com.siilo.android.registration:id/country_text").get(0).click();	
			} catch(Exception e) {
				
			}
			driver.findElementById("com.siilo.android.registration:id/reg_phonenumber").sendKeys("+31642092110");
		}
		driver.findElementById("com.siilo.android.registration:id/button_done").click();
		
		/*     verification code screen      */
		driver.findElementById("com.siilo.android.registration:id/reg_verification_code").sendKeys("12345");
		
		
		data = driver.findElementsById("com.siilo.android.registration:id/button_done");
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getText().equalsIgnoreCase("Next")) {
				data.get(i).click();
			}
		}

		
		/* notification choice screen */
		data = driver.findElementsById("com.siilo.android.registration:id/button_done");
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getText().equalsIgnoreCase("Next")) {
				data.get(i).click();
			}
		}
		
		

	}

}
