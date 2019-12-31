package com.trello.qa.tests;

import com.trello.qa.manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TestBase.class);
    // public static Logger logger = LoggerFactory.getLogger(TestBase.class);

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


    @BeforeMethod
    public void startLogger(Method m) {

        System.out.println("-----------------------------------------------------");
        logger.info("\n -  start metod - "
                + m.getName());
    }

    @AfterMethod
    public void stopLogger(Method m) {

        System.out.println("-----------------------------------------------------");

        logger.info("\n - stop " +
                "metod -" + m.getName());
    }

 /* public void confirmBoardCreation(By locator) {
//By.xpath("//button[@class='_3UeOvlU6B5KUnS uj9Ovoj4USRUQz _2MgouXHqRQDP_5']
    //By.cssSelector("[data-test-id='create-board-submit-button']"
    clickOnWebElement(locator);
  }*/


}
