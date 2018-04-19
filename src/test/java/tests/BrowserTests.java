package tests;

import Selenium.SeleniumUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserTests {
    SeleniumUtil util = new SeleniumUtil();

    @Test
    public void testProForStudents() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//nav/ul/li/button")).click();
            driver.findElement(By.xpath("//ul/li[2]/a[contains(@href,'/pro-for-students/')]/span")).click();
            driver.findElement(By.xpath("(//a[contains(@href, '/pro/pricing/students/')])[2]")).click();
            assertEquals("Pick Your Pro Plan", driver.findElement(By.xpath("//article[2]/header/h1")).getText());
        }
    }

    @Test
    public void testAppsWeb() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//header/nav/ul/li[2]/button")).click();
            driver.findElement(By.xpath("//li/a[contains(@href,'/web-apps/')]")).click();
            assertEquals("Web Apps Powered by Wolfram|Alpha",
                    driver.findElement(By.xpath("//div[@class='wrap']/div/div/h1")).getText());
        }
    }

    @Test
    public void testTour() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//li/a[contains(@href,'/tour/')]")).click();
            assertEquals("What Is Wolfram|Alpha?", driver.findElement(By.xpath("//main/header//div/h1")).getText());
        }
    }

    @Test
    public void testMathematics() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//a[contains(@href,'examples/mathematics/')]/div")).click();
            assertEquals("Do basic arithmetic:", driver
                    .findElement(By.xpath("//div/p[contains(@class,'subsection-caption')]")).getText());
        }
    }

    @Test
    public void testScience() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//a[contains(@href,'examples/science')]/div")).click();
            assertEquals("Compute mechanical work:", driver
                    .findElement(By.xpath("//div/p[contains(@class,'subsection-caption')]")).getText());
        }
    }

    @Test
    public void testSociety() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            WebElement element = driver.findElement(By.xpath("//a[contains(@href,'examples/society')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            assertEquals("Compare several people:", driver
                    .findElement(By.xpath("//div/p[contains(@class,'subsection-caption')]")).getText());
        }
    }

    @Test
    public void testEveryday() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//a[contains(@href,'examples/everyday')]/div")).click();
            assertEquals("Compare activities:", driver
                    .findElement(By.xpath("//div/p[contains(@class,'subsection-caption')]")).getText());
        }
    }

    @Test
    public void testSettings() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//button[contains(@class,'icon-gear')]")).click();
            assertEquals("Default with Examples", driver
                    .findElement(By.xpath("//div[contains(@class,'mid')]/article/div")).getText());
        }
    }

    @Test
    public void testSignInWithEmptyFields() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//li/button[contains(@class,'sign-in')]")).click();
            WebElement element = driver.findElement(By.xpath("//button[text()='Sign in']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            if (driver instanceof ChromeDriver) {
                assertEquals("Please correct the fields below.", driver
                        .findElement(By.xpath("//div[contains(@class,'alert-danger')]/p")).getText());
            }
            if (driver instanceof FirefoxDriver) {
                assertEquals("The email address or password is not correct.", driver
                        .findElement(By.xpath("//div[contains(@class,'alert-danger')]/p")).getText());
            }
        }
    }

    @Test
    public void testSignInSuccessfully() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//li/button[contains(@class,'sign-in')]")).click();
            driver.findElement(By.xpath("//input[@name='j_username']")).click();
            driver.findElement(By.xpath("//input[@name='j_username']")).clear();
            driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys("mrfomin07@gmail.com");
            driver.findElement(By.xpath("//input[@name='j_password']")).click();
            driver.findElement(By.xpath("//input[@name='j_password']")).clear();
            driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys("123456");
            driver.findElement(By.xpath("//button[contains(@class,'submit-btn')]")).click();
            WebDriverWait driverWait = new WebDriverWait(driver, 10);
            driverWait.until(ExpectedConditions.visibilityOf(
                    driver.findElement(By.xpath("//div/button[contains(@class,'user-icon-header')]"))));
            driver.findElement(By.xpath("//div/button[contains(@class,'user-icon-header')]")).click();
            assertEquals("Sign out", driver
                    .findElement(By.xpath("//nav/ul/li[4]/div/ul/li[9]/button")).getText());
        }
        util.signOut();
    }

    @Test
    public void testSignInWithIncorrectPassword() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//li/button[contains(@class,'sign-in')]")).click();
            driver.findElement(By.xpath("//input[@name='j_username']")).click();
            driver.findElement(By.xpath("//input[@name='j_username']")).clear();
            driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys("mrfomin07@gmail.com");
            driver.findElement(By.xpath("//input[@name='j_password']")).click();
            driver.findElement(By.xpath("//input[@name='j_password']")).clear();
            driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys("11111");
            driver.findElement(By.xpath("//button[contains(@class,'submit-btn')]")).click();
            assertEquals("The email address or password is not correct.", driver
                    .findElement(By.xpath("//form[@class='form']/div[2]/div/p")).getText());
        }
    }

    @Test
    public void testSignOut() {
        util.signIn();
        for (WebDriver driver : util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//div/button[contains(@class,'user-icon-header')]")).click();
            driver.findElement(By.xpath("//nav/ul/li[4]/div/ul/li[9]/button")).click();
            assertEquals("Sign in", driver
                    .findElement(By.xpath("//li/button[contains(@class,'sign-in')]/span")).getText());
        }
    }

    @AfterEach
    public void closeBrowsers() {
        for (WebDriver driver : util.getWebDrivers()) {
            driver.close();
        }
    }
}
