package com.mhra.mcm.appian.steps.v1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.springframework.context.annotation.Scope;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

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
            tasksPage = mainNavigationBar.clickTasks();
            tasksPage = tasksPage.clickTaskWithSubmitterName(submitterName);
            contains = tasksPage.isCorrectECID(data.ecIDNumber);
            count++;
        }while(!contains && count <= 1);

        assertThat("Expected task with EC ID : " + ecId , contains, is(equalTo(true)));
    }




    @Given("^I should see new task generated for the submitter \"([^\"]*)\" with ecid \"([^\"]*)\"$")
    public void i_should_see_new_task_generated_for_the_submitter(String submitterName, String ecId) throws Throwable {

        boolean contains = false;
        int count = 0;
        do {
            tasksPage = mainNavigationBar.clickTasks();
            tasksPage = tasksPage.clickTaskWithSubmitterName(submitterName);
            contains = tasksPage.isCorrectECID(ecId);
            count++;
        }while(!contains && count <= 3);

        assertThat("Expected task with EC ID : " + ecId , contains, is(equalTo(true)));
    }

    @When("^I set a new TCA number for the notification$")
    public void i_set_a_new_TCA_number_for_the_notification() throws Throwable {
        Notification data = (Notification) scenarioSession.getData(SessionKey.storedNotification);
        String newTCANumber = RandomDataUtils.getRandomNumberBetween(100000,1000000);
        //data.getSubmitter().tcaNumber = newTCANumber;

        //Set TCANumber
        tasksPage = tasksPage.acceptTask();
        tasksPage.enterTCANumber(newTCANumber);
        tasksPage = tasksPage.updateSummary();
    }

}
