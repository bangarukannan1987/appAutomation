package com.styli.step.definition;

import com.styli.factory.FactoryMethods;
import com.styli.hooks.ScenarioHooks;
import com.styli.screens.LoginScreen;
import com.styli.screens.ProductScreen;
import org.junit.Assert;

public class LoginSteps {
    LoginScreen loginScreen;
    ProductScreen productScreen;

    public void sendUserNameAndPassword(String userName, String password) {
        loginScreen = new LoginScreen().sendUserName(userName).sendPassword(password);
        ScenarioHooks.scenario.write(String.format("USERNAME - %s and PASSWORD - %s", userName, password));
    }

    public void clickWithValidCredentials(){
        productScreen = loginScreen.clickSuccuessLogin();
        ScenarioHooks.scenario.write("CLICKED ON LOGIN BUTTON..");
    }

    public void validateErrorMessage(){
        Assert.assertTrue("UNABLE TO IDENTIFY ERROR MESSAGE ", loginScreen.validateErrorMessage());
        ScenarioHooks.scenario.write("EXPECTED ERROR OCCURRED");
    }

    public void validateCartButtoninHomeScreen() {
        Assert.assertTrue("UNABLE TO IDENTIFY ERROR MESSAGE ", productScreen.verifyCartButton());
    }

    public void searchProduct(String productName) {
        Assert.assertTrue(String.format("PRODUCT %s NOT AVAILABLE", productName), productScreen.searchProduct(productName));
        ScenarioHooks.scenario.write(String.format("SEARCHING PRODUCT %s", productName));
    }

    public void selectProduct(String productName) {
        productScreen.selectProduct(productName);
        ScenarioHooks.scenario.write(String.format("%s PRODUCT SELECTED", productName.toUpperCase()));
        FactoryMethods.takeScreenShot();
    }

    public void validateProduct(String productName) {
        Assert.assertEquals("SELECTED PRODUCT NOT AVAILABLE", productScreen.verifyProduct(), productName);
        ScenarioHooks.scenario.write(String.format("%s PRODUCT DETAILS VERIFIED", productName.toUpperCase()));
    }
}
