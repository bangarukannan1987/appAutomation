package com.styli.screens;

import com.styli.driverhandler.DriverManager;
import org.testng.Reporter;

public class LoginScreen {

    private final String user = "test-Username";
    private final String pass = "test-Password";
    private final String btnLogin = "test-LOGIN";

    public LoginScreen sendUserName(String userName){
        DriverManager.getDriver().findElementByAccessibilityId(user).sendKeys(userName);
        Reporter.log(String.format("ENTERED USERNAME - %s",userName));
        return this;
    }

    public LoginScreen sendPassword(String password){
        DriverManager.getDriver().findElementByAccessibilityId(pass).sendKeys(password);
        Reporter.log(String.format("ENTERED PASSWORD - %s",password));
        return this;
    }

    public LoginScreen clickFailedLogin(){
        DriverManager.getDriver().findElementByAccessibilityId(btnLogin).click();
        return this;
    }

    public ProductScreen clickSuccuessLogin(){
        DriverManager.getDriver().findElementByAccessibilityId(btnLogin).click();
        return new ProductScreen();
    }

    public boolean validateErrorMessage(){
        return DriverManager.getDriver().findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView").isDisplayed();
    }

}
