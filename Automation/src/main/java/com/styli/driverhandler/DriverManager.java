package com.styli.driverhandler;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {
    private DriverManager(){

    }
    private static ThreadLocal<AndroidDriver> threadLocalDriver = new ThreadLocal<AndroidDriver>();
    public static AndroidDriver getDriver(){
        return threadLocalDriver.get();
    }
    public static void setDriver(AndroidDriver driver){
        threadLocalDriver.set(driver);
    }
    public static void unload(){
        threadLocalDriver.remove();
    }
}
