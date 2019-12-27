package com.trello.qa.tests;
//one upload start project

import com.trello.qa.manager.ApplicationManager;
import com.trello.qa.manager.TeamData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeamCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validTeams() {

        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{"name", "description"});
        list.add(new Object[]{"NAME", "DESC"});
        list.add(new Object[]{"6546", "988"});
        list.add(new Object[]{"%^$", ")897"});
        list.add(new Object[]{"%^$", ""});
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Object[]> validTeamsFromCsv() throws IOException {

        List<Object[]> list1 = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader
                (new File("src/test/resources/Team.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list1.add(new Object[]{
                    new TeamData()
                            .withTeamName(split[0])
                            .withDescription(split[1])});
            line = reader.readLine();
        }
        return list1.iterator();
    }

    @BeforeClass
    public void ensurePrecondition() throws InterruptedException {

        if (!app.getSessionHelper().isUserLoggedIn()) {
            app.getSessionHelper().loginToTrello(ApplicationManager.email, ApplicationManager.password);
        }
    }

    @BeforeMethod
    public void isOnHomePage() {

        // app.getTeamHelper().teamName = "test";

        if (!app.getBoardHelper().isPersonalBoards()) {
            app.getTeamHelper().returnToHome();
        }
    }


    @Test(dataProvider = "validTeamsFromCsv")
    public void testCreateBoardFromButtonPlusLeftFromCsv(TeamData team) throws InterruptedException {

        int beforeCountTeams = app.getTeamHelper().getTeamsCount();
        System.out.println("testTeamCreationFromButtonOnHeader beforeCountTeams = " + beforeCountTeams);
        app.getTeamHelper().click(By.cssSelector("[data-test-id='home-navigation-create-team-button']"));
        //app.getTeamHelper().fillTeamCreationForm(new TeamData(teamName2, app.getTeamHelper().description));
        app.getTeamHelper().fillTeamCreationForm(team);
        Thread.sleep(3000);
        app.getTeamHelper().clickContinueButton();
        Thread.sleep(3000);
        app.getTeamHelper().click(By.xpath("//a[@class='eg0KI5SqghoOFd']"));//click on the inscription "return to created team page"

        //String createdTeamName = app.getTeamHelper().getTeamNameFromPage();
        //Assert.assertEquals(createdTeamName.toLowerCase(),app.getTeamHelper().teamName.toLowerCase());
        app.getTeamHelper().returnToHome();
        System.out.println(app.getTeamHelper().getTeamsCount() + " = " + (beforeCountTeams + 1));
        Assert.assertEquals(app.getTeamHelper().getTeamsCount(), beforeCountTeams + 1);

    }

    @Test(dataProvider = "validTeams")
    public void testTeamCreationFromButtonOnHeaderDataProvider(String teamName, String description)
            throws InterruptedException {

        TeamData team = new TeamData().withTeamName(teamName).withDescription(description);
        int beforeCountTeams = app.getTeamHelper().getTeamsCount();
        // app.getTeamHelper().teamName = (beforeCountTeams+1)+" "+app.getTeamHelper().teamName + (int) System.currentTimeMillis();
        System.out.println("testTeamCreationFromButtonOnHeader beforeCountTeams = " + beforeCountTeams);
        app.getTeamHelper().clickButtonPlusUp();
        app.getBoardHelper().selectCreateSomethingFromDropDown(By.cssSelector("[data-test-id='header-create-team-button']"));
        Thread.sleep(1000);

        app.getTeamHelper().fillTeamCreationForm(team);
        /*app.getTeamHelper().fillTeamCreationForm(
                new TeamData()
                        .withTeamName(app.getTeamHelper().teamName)
                        .withDescription(app.getTeamHelper().description));*/
        Thread.sleep(2000);
        app.getTeamHelper().clickContinueButton();
        Thread.sleep(3000);
        app.getTeamHelper().click(By.xpath("//a[@class='eg0KI5SqghoOFd']"));//click on the inscription "return to home page"
        String createdTeamName = app.getTeamHelper().getTeamNameFromPage();
        //Assert.assertEquals(createdTeamName.toLowerCase(), get.team.withTeamName(teamName).toLowerCase());
        app.getTeamHelper().returnToHome();
        int afterCountTeams = app.getTeamHelper().getTeamsCount();
        System.out.println("testTeamCreationFromButtonOnHeader afterCountTeams = " + afterCountTeams);
        Assert.assertEquals(afterCountTeams, beforeCountTeams + 1);
        // Assert.assertTrue(isUserLoggedIn(By.xpath("//a[@class='button-link tabbed-pane-header-details-edit js-edit-profile']")), "ass");
    }

    @Test(dataProvider = "validTeamsFromCsv")
    public void testTeamCreationFromButtonOnHeaderDataProviderFromCsv(TeamData team) throws InterruptedException {

        int beforeCountTeams = app.getTeamHelper().getTeamsCount();
        System.out.println("testTeamCreationFromButtonOnHeader beforeCountTeams = " + beforeCountTeams);
        app.getTeamHelper().clickButtonPlusUp();
        app.getBoardHelper().selectCreateSomethingFromDropDown
                (By.cssSelector("[data-test-id='header-create-team-button']"));
        app.getTeamHelper().fillTeamCreationForm(team);
        //Thread.sleep(2000);
        app.getTeamHelper().clickContinueButton();
        //Thread.sleep(3000);
        app.getTeamHelper().click(By.xpath("//a[@class='eg0KI5SqghoOFd']"));//click on the inscription "return to home page"
        // String createdTeamName = app.getTeamHelper().getTeamNameFromPage();
        app.getTeamHelper().returnToHome();
        int afterCountTeams = app.getTeamHelper().getTeamsCount();
        System.out.println("testTeamCreationFromButtonOnHeader afterCountTeams = " + afterCountTeams);
        Assert.assertEquals(afterCountTeams, beforeCountTeams + 1);
        Thread.sleep(1000);
    }
 /*  @Test(enabled = false)
      public void testTeamCancelCreationFromButtonOnHeader() {
          TeamData team = new TeamData().withTeamName(teamName).withDescription(description);

          app.getTeamHelper().clickButtonPlusUp();
          app.getBoardHelper().selectCreateSomethingFromDropDown(By.cssSelector("[data-test-id='header-create-board-button']"));
          //app.getTeamHelper().fillTeamCreationForm(new TeamData(app.getTeamHelper().teamName, app.getTeamHelper().description));
          app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName(app.getTeamHelper().teamName).
                  withDescription(app.getTeamHelper().description));
          app.getTeamHelper().clickXorCancel();
          //Assert.assertTrue(isUserLoggedIn(), "ass");
      }*/
  /*@Test()
    public void testTeamCreationFromButtonOnHeader() throws InterruptedException {


       // app.getTeamHelper().teamName = app.getTeamHelper().teamName + (int) System.currentTimeMillis();
        int beforeCountTeams = app.getTeamHelper().getTeamsCount();
        System.out.println("testTeamCreationFromButtonOnHeader beforeCountTeams = " + beforeCountTeams);
        app.getTeamHelper().clickButtonPlusUp();
        app.getBoardHelper().selectCreateSomethingFromDropDown(By.cssSelector("[data-test-id='header-create-team-button']"));
        Thread.sleep(1000);

        app.getTeamHelper().fillTeamCreationForm(
                new TeamData()
                        .withTeamName(app.getTeamHelper().teamName3)
                        .withDescription(app.getTeamHelper().description3));
        app.getTeamHelper().fillTeamCreationForm3(teamName3,description3);
        Thread.sleep(2000);
        app.getTeamHelper().clickContinueButton();
        Thread.sleep(3000);
        app.getTeamHelper().click(By.xpath("//a[@class='eg0KI5SqghoOFd']"));//click on the inscription "return to home page"
        String createdTeamName = app.getTeamHelper().getTeamNameFromPage();
        Assert.assertEquals(createdTeamName.toLowerCase(), app.getTeamHelper().teamName3.toLowerCase());
        app.getTeamHelper().returnToHome();
        int afterCountTeams = app.getTeamHelper().getTeamsCount();
        System.out.println("testTeamCreationFromButtonOnHeader afterCountTeams = " + afterCountTeams);
        Assert.assertEquals(afterCountTeams, beforeCountTeams + 1);
        // Assert.assertTrue(isUserLoggedIn(By.xpath("//a[@class='button-link tabbed-pane-header-details-edit js-edit-profile']")), "ass");
    }*/
}
