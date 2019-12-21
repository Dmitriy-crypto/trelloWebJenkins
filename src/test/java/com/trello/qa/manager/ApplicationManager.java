package com.trello.qa.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    //-------------------Variables-------------------------------------
    public static String url = "https://trello.com/";
    public static String email = "dmitriy.stadnikov@yahoo.com";
    public static String password = "75Dmitriy.2013Eva";
    public String x = "23";
    public int numberBoards = 3;// number of boards to creat
    WebDriver driver;
    BoardHelper boardHelper;
    TeamHelper teamHelper;
    SessionHelper sessionHelper;
    /* boolean no_team = true;//option selection
     boolean privet_team = true;//option selection not work
     boolean enable_background_random = true;// enable random background selection*/
    int n; //board selection by position in "public void selectPersonalBoardByNumber"
    public String browser;

    public ApplicationManager(String browser) {

        this.browser = browser;
    }


    public void init() throws InterruptedException {

        //String browser = null;
        if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        }
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        }
        if (browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //driver.manage().window().fullscreen();

        teamHelper = new TeamHelper(driver);
        boardHelper = new BoardHelper(driver);
        sessionHelper = new SessionHelper(driver);

        sessionHelper.openSite(url);
        sessionHelper.loginToTrello(email, password);
    }

    public void stop() {

        driver.quit();
    }

    public BoardHelper getBoardHelper() {

        return boardHelper;
    }

    public TeamHelper getTeamHelper() {

        return teamHelper;
    }

    public SessionHelper getSessionHelper() {

        return sessionHelper;
    }
}
