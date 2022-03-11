package com.samples.factories;

import com.samples.enums.Environments;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static synchronized WebDriver getDriver(Environments environment, MutableCapabilities options){
        switch (environment){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = (ChromeOptions) options;
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = (FirefoxOptions) options;
                return new FirefoxDriver(firefoxOptions);

            default: throw new RuntimeException("driver not created!");
        }
    }
}
