package com.trello.qa.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class TeamHelper extends HelperBase {


    public TeamHelper(WebDriver driver) {

        super(driver);

    }

    //------------------Variables for teams fill----------------------------------------
    //public String teamName;
    //public String description = "descr Learn_delete_ok";
    // WebDriverWait driverWait;
    public String name2Team = "name2";
    public String desc = "desc";

    //------------------------COMMON METHODS FOR CREAT------------------------------------------


    //---------------------------------METHODS FOR TEAMS-----------------------------------------------
    public String getTeamNameFromPage() throws InterruptedException {

        Thread.sleep(3000);
        // new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        // Thread.sleep(3000);
        // System.out.println(driver.findElement(By.cssSelector("")).getText());
        return driver.findElement(By.cssSelector("h1")).getText();

    }

    public int getTeamsCount() {

        /*new WebDriverWait(driver, 10).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath("//*[@class='NC6qaILF7dGKjb']/../li")));
        return sizeList(By.xpath("//*[@class='NC6qaILF7dGKjb']/../li")); */

        if (isElementPresent(By.cssSelector("[data-test-id='home-team-tab-name']"))) {

            return sizeList(By.xpath("//*[@class='NC6qaILF7dGKjb']/../li"));
            //return sizeList(By.cssSelector("//*[@data-test-id='home-team-tab-name']/../.."));

        } else {
            return 0;
        }

    }

    public void deleteTeam() {

        driver.manage().window().fullscreen();
        new WebDriverWait(driver, 15).until(elementToBeClickable(By.cssSelector(".quiet-button")));
        click(By.cssSelector(".window-module.u-gutter"));

        new WebDriverWait(driver, 15).until(elementToBeClickable(By.cssSelector(".js-confirm.full.negate")));
        new WebDriverWait(driver, 15).until(elementToBeClickable(By.cssSelector(".js-confirm.full.negate")));
        click(By.cssSelector(".js-confirm.full.negate"));

        new WebDriverWait(driver, 15).until(elementToBeClickable(By.cssSelector("a[href='/']")));
        click(By.cssSelector("a[href='/']"));

    }

    public void openSettings() throws InterruptedException {

        new WebDriverWait(driver, 15).
                until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".icon-gear.icon-sm._2aV_KY1gTq1qWc")));
        new WebDriverWait(driver, 15).
                until(ExpectedConditions.elementToBeClickable(By.cssSelector(".icon-gear.icon-sm._2aV_KY1gTq1qWc")));

        click(By.cssSelector(".icon-gear.icon-sm._2aV_KY1gTq1qWc"));
        Thread.sleep(1000);
    }

    public void clickOnFirstTeam() {
//
        new WebDriverWait(driver, 15).until(elementToBeClickable(By.xpath("//*[@class='NC6qaILF7dGKjb']/../li")));
        click(By.xpath("//*[@class='NC6qaILF7dGKjb']/../li"));
    }

    public void initEditTeamProfile() {

        //click(By.cssSelector(".js-edit-profile"));// option
        waitForElementAndClick(By.cssSelector(".js-edit-profile"), 10);

    }

    public void changeTeamProfile() {

        typeTextInTheFieldNameBoard(By.xpath("//input[@name='displayName']"), name2Team);
        typeTextInTheFieldNameBoard(By.xpath("//textarea[@name='desc']"), desc);

    }

    public void confirmEditTeam() {

        click(By.cssSelector(".primary.wide.js-submit-profile"));
    }


    public boolean isTeamsPresent() {

        return getTeamsCount() > 0;
    }

    public void createTeamDefault() throws InterruptedException {

        int beforeCountTeams = getTeamsCount();
        System.out.println("testTeamCreationFromButtonOnHeader beforeCountTeams = " + beforeCountTeams);
        clickButtonPlusUp();
        selectCreateTeamFromDropDown(By.cssSelector("[data-test-id='header-create-team-button']"));
        Thread.sleep(1000);

       /* fillTeamCreationForm(
                new TeamData()
                        .withTeamName(teamName)
                        .withDescription(description));*/
        //  fillTeamCreationForm(team);
        Thread.sleep(2000);
        clickContinueButton();
        Thread.sleep(3000);
        click(By.xpath("//a[@class='eg0KI5SqghoOFd']"));//click on the inscription "return to home page"
        String createdTeamName = getTeamNameFromPage();
        //Assert.assertEquals(createdTeamName.toLowerCase(), getTeamName.toLowerCase());
        returnToHome();
    }

    public void clickContinueButton() {

        click(By.cssSelector("[type=submit]"));
    }

    public void fillTeamCreationForm(TeamData teamData) {
//Thread.sleep(1000);
        typeTextInTheFieldNameBoard(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamData.getTeamName());
        // Thread.sleep(1000);
        typeTextInTheFieldNameBoard(By.cssSelector("textarea"), teamData.getDescription());
    }


}

