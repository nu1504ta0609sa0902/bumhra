package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.domain.xmlPojo.EcigProductSubmission;
import com.mhra.mcm.appian.pageobjects.sections.MainNavigationBar;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.context.annotation.Scope;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class TasksPageSteps extends CommonSteps {

    @Given("^I should see new task generated for the stored notification$")
    public void i_should_see_new_task_generated_for_the_submitter() throws Throwable {
        //Stored data to verify
        Notification data = (Notification) scenarioSession.getData(SessionKey.storedNotification);
        String ecId = data.ecIDNumber;
        String submitterName = data.getSubmitter().name;
        log.info("Expected tasks for submitter : " + submitterName);

        //Verify new task generated
        boolean contains = false;
        int count = 0;
        do {
            mainNavigationBar = new MainNavigationBar(driver);
            tasksPage = mainNavigationBar.clickTasks();

            tasksPage = tasksPage.clickTaskWithSubmitterName(submitterName);
            contains = tasksPage.isCorrectECID(data.ecIDNumber);
            count++;
        }while(!contains && count <= 1);

        assertThat("Expected task with EC ID : " + ecId , contains, is(equalTo(true)));
    }



    @Given("^I find the new task generated for the stored notification$")
    public void i_find_the_new_task_generated_for_the_submitter() throws Throwable {
        //Stored data to verify
        EcigProductSubmission data = (EcigProductSubmission) scenarioSession.getData(SessionKey.storedNotification);
        String ecId = data.getEcIDNumber();
        String submitterName = RandomDataUtils.getRandomTestName("TestSubmitter");
        data.getSubmitter().setName(submitterName);
        log.info("Expected tasks for submitter : " + submitterName);

        //Verify new task generated for the stored notifications
        boolean contains = false;
        int count = 0;
        do {
            mainNavigationBar = new MainNavigationBar(driver);
            tasksPage = mainNavigationBar.clickTasks();

            tasksPage = tasksPage.clickOnLinkAndVerifyItsCorrectPage(count);
            contains = tasksPage.isCorrectECID(ecId);
            count++;
        }while(!contains && count <= 10);

        assertThat("Expected task with EC ID : " + ecId , contains, is(equalTo(true)));
    }



    @Given("^I should see new task generated for the submitter \"([^\"]*)\" with ecid \"([^\"]*)\"$")
    public void i_should_see_new_task_generated_for_the_submitter(String submitterName, String ecId) throws Throwable {

        boolean contains = false;
        int count = 0;
        do {
            mainNavigationBar = new MainNavigationBar(driver);
            tasksPage = mainNavigationBar.clickTasks();

            tasksPage = tasksPage.clickTaskWithSubmitterName(submitterName);
            contains = tasksPage.isCorrectECID(ecId);
            count++;
        }while(!contains && count <= 3);

        assertThat("Expected task with EC ID : " + ecId , contains, is(equalTo(true)));
    }


    @Given("^I view a task with heading containing \"([^\"]*)\"$")
    public void i_view_a_task_with_heading_containing_text(String taskHeading) throws Throwable {

        String ecId = (String) scenarioSession.getData(SessionKey.ECID);
        mainNavigationBar = new MainNavigationBar(driver);
        tasksPage = mainNavigationBar.clickTasks();
        tasksPage = tasksPage.clickTaskWithSubmitterName(taskHeading);

        String ecid = tasksPage.getECID();
        if(ecid!=null) {
            log.warn("ECID selected : " + ecid);
            scenarioSession.putData(SessionKey.ECID, ecid);
        }
    }

    @Given("^I view a task for stored notification with heading containing \"([^\"]*)\"$")
    public void i_view_a_task_for_stored_notification_with_heading_containing_text(String taskHeading) throws Throwable {
        String ecId = (String) scenarioSession.getData(SessionKey.ECID);
        log.info("Find task for ecid : " + ecId);
        boolean contains = false;
        int count = 0;
        do {
            mainNavigationBar = new MainNavigationBar(driver);
            tasksPage = mainNavigationBar.clickTasks();
            tasksPage = tasksPage.clickTaskWithText(taskHeading, count);
            contains = tasksPage.isHeadingContainsECID(ecId);
            if(!contains){
                contains = tasksPage.isCorrectECID(ecId);
            }
            count++;
        }while(!contains && count <= 10);

        //One last try
        if(!contains){
            mainNavigationBar = new MainNavigationBar(driver);
            tasksPage = mainNavigationBar.clickTasks();
            tasksPage = tasksPage.clickTaskWithText(taskHeading, 0);
        }
    }

    @When("^I set a new TCA number for the notification$")
    public void i_set_a_new_TCA_number_for_the_notification() throws Throwable {
        Notification data = (Notification) scenarioSession.getData(SessionKey.storedNotification);
        String newTCANumber = RandomDataUtils.getRandomNumberBetween(100000,1000000);
        //data.getSubmitter().tcaNumber = newTCANumber;

        //Set TCANumber
        tasksPage = tasksPage.acceptTask();
        tasksPage.enterTCANumber(newTCANumber);
        tasksPage = tasksPage.submitTask();
    }


    @When("^I should see option to accept or reject qa tasks$")
    public void i_should_see_option_to_accept_or_reject_qa() throws Throwable {
        boolean isDisplayed = tasksPage.isOptionToAcceptRejectDisplayed();
        Assert.assertThat("Accept and reject option not displayed", isDisplayed, is(true));
    }



    @When("^I set TCA details for the notification$")
    public void i_set_TCA_details_for_the_notification() throws Throwable {
        EcigProductSubmission data = (EcigProductSubmission) scenarioSession.getData(SessionKey.storedNotification);
        String newTCANumber = RandomDataUtils.getRandomNumberBetween(100000,1000000);
        scenarioSession.putData(SessionKey.tcaNumber, newTCANumber);
        //data.getSubmitter().tcaNumber = newTCANumber;

        //Set TCANumber
        tasksPage = tasksPage.acceptTask();
        tasksPage.enterTCANumber(newTCANumber);
        tasksPage.enterTCAName(data.getSubmitter().name);
        tasksPage = tasksPage.submitTask();
    }



    @Given("^I \"([^\"]*)\" the qa decision and add comment \"([^\"]*)\"$")
    public void i_view_a_task_with_heading_containing_text(String acceptOrRejectQADecision, String comment) throws Throwable {
        //Assumes in the tasks page
        tasksPage = tasksPage.acceptTask();

        if(acceptOrRejectQADecision.equals("accept")) {
            tasksPage = tasksPage.acceptOrRejectQADecision(true);
            if(!comment.equals(""))
                tasksPage.enterComment(comment);
        }else{
            tasksPage = tasksPage.acceptOrRejectQADecision(false);
            tasksPage.enterComment(comment);
        }
        tasksPage = tasksPage.submitTask();

        //If an alert is displayed accept it
        PageUtils.acceptAlert(driver, true);
    }

}
