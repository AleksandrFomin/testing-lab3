package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    public void signIn(){
        for (WebDriver driver : webDrivers) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//li/button[contains(@class,'sign-in')]")).click();
            driver.findElement(By.xpath("//input[@name='j_username']")).click();
            driver.findElement(By.xpath("//input[@name='j_username']")).clear();
            driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys("mrfomin07@gmail.com");
            driver.findElement(By.xpath("//input[@name='j_password']")).click();
            driver.findElement(By.xpath("//input[@name='j_password']")).clear();
            driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys("123456");
            driver.findElement(By.xpath("//button[contains(@class,'submit-btn')]")).click();
        }
    }

    public void signOut(){
        for (WebDriver driver : webDrivers) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//div/button[contains(@class,'user-icon-header')]")).click();
            driver.findElement(By.xpath("//nav/ul/li[4]/div/ul/li[9]/button")).click();
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
