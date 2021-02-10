package com.selenium.demo.base;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

    Properties prop = new Properties();

    public ReadConfig(){
        try (InputStream input = new FileInputStream("src/main/java/resources/config.properties")) {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getUrl()
    {
        return  prop.getProperty("baseUrl");
    }
    public String getUserName(){
        return  prop.getProperty("username");
    }
    public String getPassword(){
        return  prop.getProperty("password");
    }

    public String getPlatform() {
        return prop.getProperty("platform");
    }
}
