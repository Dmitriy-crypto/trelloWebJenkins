package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamModificationTest extends TestBase {

    @Test
    public void testRenameTeam() throws InterruptedException {

        app.getTeamHelper().clickOnFirstTeam();
        app.getTeamHelper().openSettings();
        app.getTeamHelper().initEditTeamProfile();
        app.getTeamHelper().changeTeamProfile();
        app.getTeamHelper().confirmEditTeam();
        //System.out.println("expected "+app.getTeamHelper().getTeamNameFromPage()+"  actual  "+app.getTeamHelper().name2Team);
        Assert.assertEquals(app.getTeamHelper().getTeamNameFromPage(), app.getTeamHelper().name2Team);
    }
}
