package com.styli.driverhandler;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Driver {

    private Driver(){

    }

    private static AppiumDriverLocalService service;

    public static void initDriver(String deviceName, String platformVersion) throws Exception{
        int port = getAvailablePort();
        if(port!=0){
            if(Objects.isNull(DriverManager.getDriver())){
                try {
                    AppiumServiceBuilder builder;
                    System.out.println("Building and starting the server");
                    builder= new AppiumServiceBuilder();
                    builder.usingPort(port);
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability("deviceName", deviceName);
                    desiredCapabilities.setCapability("platformVersion",platformVersion);
                    if(System.getProperty("platformName").equalsIgnoreCase("android")){
                        desiredCapabilities.setCapability("app",System.getProperty("user.dir")+"/src/main/resources/apk/sauclabs.apk");
                        desiredCapabilities.setCapability("appPackage","com.swaglabsmobileapp");
                        desiredCapabilities.setCapability("appActivity","com.swaglabsmobileapp.MainActivity");
                    }else {
                        desiredCapabilities.setCapability("app",System.getProperty("user.dir")+"/src/main/resources/app/"); //iOS app path need to configure
                    }
                    builder.withCapabilities(desiredCapabilities);
                    service = AppiumDriverLocalService.buildService(builder);
                    service.start();
                    System.out.println("Server started on Port - "+port);
                    DriverManager.setDriver(new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"), desiredCapabilities));
                    DriverManager.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        }else {
            throw new Exception("PORT NOT AVAILABLE TO INITIATE THE RUN");
        }
    }

    public static int getAvailablePort(){
        OptionalInt number = IntStream.range(4723,5723).filter(port -> isLocalPortFree(port)).findFirst();
        if(number.isPresent())
            return number.getAsInt();
        else
            return 0;
    }

    public static boolean isLocalPortFree(int port){
        try {
            new ServerSocket(port).close();
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public static void quitDriver(){
        if(Objects.nonNull(DriverManager.getDriver())){
            DriverManager.getDriver().quit();
            DriverManager.unload();
            System.out.println("Trying to stop the server..");
            service.stop();
            System.out.println("Server stopped");
        }
    }
}
