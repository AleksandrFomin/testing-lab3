package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SeleniumUtil {

    private String baseUrl = "https://www.wolframalpha.com/";

    private WebDriver firefoxDriver;

    private WebDriver chromeDriver;

    private ArrayList<WebDriver> webDrivers = new ArrayList<WebDriver>();

    public SeleniumUtil(){
        System.setProperty("webdriver.gecko.driver", "/home/aleksandr/drivers/geckodriver-v0.20.1-linux64/geckodriver");
        System.setProperty("webdriver.chrome.driver", "/home/aleksandr/drivers/chromedriver_linux64/chromedriver");
        firefoxDriver = new FirefoxDriver();
        chromeDriver = new ChromeDriver();
        webDrivers.add(firefoxDriver);
        webDrivers.add(chromeDriver);
        for(WebDriver webDriver: webDrivers){
            webDriver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            webDriver.get(baseUrl);
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public WebDriver getFirefoxDriver() {
        return firefoxDriver;
    }

    public WebDriver getChromeDriver() {
        return chromeDriver;
    }

    public ArrayList<WebDriver> getWebDrivers() {
        return webDrivers;
    }
}
