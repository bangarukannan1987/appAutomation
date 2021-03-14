package com.styli.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    private static Properties properties = new Properties();
    private static Properties configProperties;
    static {
        configProperties = loadPertiesFiles(System.getProperty("user.dir")+"/src/main/resources/config.properties");
    }

    private static Properties loadPertiesFiles(String filePath){
        try{
            properties.load(new FileInputStream(filePath));
        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties getConfigProperties(){
        return configProperties;
    }
}
