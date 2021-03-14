package com.styli.screens;

import com.styli.driverhandler.DriverManager;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class ProductScreen {
    private final String searchProductByText = "//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[contains(@text,'%PRODUCT%')]";

    public boolean verifyCartButton(){
        DriverManager.getDriver().manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        return DriverManager.getDriver().findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView").isDisplayed();
    }

    public boolean searchProduct(String productName) {
        return DriverManager.getDriver().findElement(By.xpath(searchProductByText.replace("%PRODUCT%", productName))).isDisplayed();
    }

    public ProductScreen selectProduct(String productName) {
        DriverManager.getDriver().findElement(By.xpath(searchProductByText.replace("%PRODUCT%", productName))).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String verifyProduct() {
        return DriverManager.getDriver().findElement(By.xpath("//android.widget.ScrollView//android.widget.TextView")).getText();
    }
}

