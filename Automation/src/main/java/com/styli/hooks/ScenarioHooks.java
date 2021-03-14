package com.styli.hooks;

import com.styli.driverhandler.Driver;
import com.styli.factory.FactoryMethods;
import com.styli.factory.ReadPropertyFile;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;

public class ScenarioHooks {
    public static Scenario scenario;
    public static HashMap<String, String> config = new HashMap<String, String>();
    public static String version = null;

    @BeforeTest
    @Parameters({"platformVersion"})
    public void getVersion(String platformVersion) throws Exception{
        version = platformVersion;
        config.put("DEVICENAME_"+platformVersion, ReadPropertyFile.getConfigProperties().getProperty("DEVICENAME_"+platformVersion));
        config.put("PORTNUMBER_"+platformVersion, ReadPropertyFile.getConfigProperties().getProperty("PORTNUMBER_"+platformVersion));
        Driver.initDriver(config.get("DEVICENAME_"+platformVersion), version);
    }

    @Before
    public void setUp(Scenario scenario) throws Exception{
        this.scenario = scenario;
        if(System.getProperty("version")==null)
            System.setProperty("version","9.0");
        Driver.initDriver(ReadPropertyFile.getConfigProperties().getProperty("DEVICENAME_"+System.getProperty("version")),
                System.getProperty("version"));
    }

    @After
    public void tearDown(){
        if(scenario.isFailed())
            FactoryMethods.takeScreenShot();
    }

    @AfterTest
    public void clearUpVersions(){
        version=null;
        config.clear();
    }
}
