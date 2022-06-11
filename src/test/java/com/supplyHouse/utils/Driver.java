package com.supplyHouse.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver obj;

    private Driver(){}

    public static WebDriver getDriver(){

        String browserName= ConfigReader.read("browser");

        if(obj==null) {

            if ("chrome".equals(browserName)) {
                WebDriverManager.chromedriver().setup();
                obj = new ChromeDriver();
                obj.manage().window().maximize();
                obj.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else {
                obj = null;
            }

        }
        return obj;
    }


    public static void closeBrowser(){
        if(obj!=null){
            obj.quit();
            obj = null;
        }
    }

}
