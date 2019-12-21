package com.trello.qa.tests;

import com.trello.qa.manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class TestBase {

    protected static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


    @BeforeSuite
    public void setUp() throws InterruptedException {

        app.init();
    }

    @AfterSuite
    public void tearDown() {

        app.stop();
    }

 /* public void confirmBoardCreation(By locator) {
//By.xpath("//button[@class='_3UeOvlU6B5KUnS uj9Ovoj4USRUQz _2MgouXHqRQDP_5']
    //By.cssSelector("[data-test-id='create-board-submit-button']"
    clickOnWebElement(locator);
  }*/


}
