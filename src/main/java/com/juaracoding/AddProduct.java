package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class AddProduct {
    public static void main(String[] args) {
        // // System.setProperty
        System.setProperty("webdriver.chrome.driver","C:\\MyTools\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Open Browser URL");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        // Web Scrapping
        delay(3);
        WebElement companyBranding = driver.findElement(By.xpath("//div[@class='login_logo']"));
        System.out.println(companyBranding.isDisplayed());

        // Scenario Test Login Lakukan Assert
        delay(1);
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("login");

        String txtDashboard = driver.findElement(By.xpath("//span[@class='title']")).getText();
        String expected    = "Products";
        customAssertEquals(txtDashboard,expected);

        // Scenario Test Add 1 Product To Cart
        delay(1);
        WebElement ProductToCartButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        ProductToCartButton.click();
        System.out.println("Tambahkan 1 produk ke dalam keranjang");

        delay(1);
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        String cartItemCount = cartBadge.getText();
        String expectedItemCount = "1";
        customAssertEquals(cartItemCount, expectedItemCount);

        // Negative Test
        delay(1);
        String unexpectedItemCount = "2";
        System.out.println("Apakah ada 2 produk di dalam keranjang?");
        customAssertEquals(cartItemCount, unexpectedItemCount);

        delay(3);
        driver.quit();
        System.out.println("Exit Browser");
    }

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void customAssertEquals(String actual, String expected){
        System.out.print("Test Result: ");
        if(actual.equals(expected)){
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }
}
