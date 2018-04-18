package tests;

import Selenium.SeleniumUtil;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {
    SeleniumUtil util = new SeleniumUtil();

    @Test
    public void testProForStudents() {
        for(WebDriver driver: util.getWebDrivers()) {
            driver.get("http://www.wolframalpha.com/");
            driver.findElement(By.xpath("//nav/ul/li/button")).click();
            driver.findElement(By.xpath("//ul/li[2]/a[contains(@href,'/pro-for-students/')]/span")).click();
            driver.findElement(By.xpath("(//a[contains(@href, '/pro/pricing/students/')])[2]")).click();
            assertEquals("Pick Your Pro Plan", driver.findElement(By.xpath("//article[2]/header/h1")).getText());
        }
    }
}
