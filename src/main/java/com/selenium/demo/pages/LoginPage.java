package com.selenium.demo.pages;
import com.selenium.demo.utils.Log;
import com.selenium.demo.base.Base;
import org.openqa.selenium.By;

public class LoginPage extends Base {
    // locators
    private final By usernameTxt = By.cssSelector("#login\\:usernameDecorate\\:username");
    private final By passwordTxt = By.cssSelector("#login\\:passwordDecorate\\:password");
    private final By loginBtn = By.cssSelector("#login\\:login");

    public void setUsername(String username){
        Log.info("Setting user name");
        driver.findElement(usernameTxt).sendKeys(username);
    }

    public void setPassword(String password){
        Log.info("Setting password");
        driver.findElement(passwordTxt).sendKeys(password);
    }

    public DashboardPage clickLoginBtn(){
        Log.info("Clicking log in button");
        driver.findElement(loginBtn).click();
        return new DashboardPage();
    }
}
