@regression_notifications
Feature: As a user I need to quickly verify there is no regression issues with creating editing notification
  So that I can trust the system

  @regression
  Scenario: Verify edit notification is only available to IPU super users
    Given I login to appian as "super1" user
    And I have notifications
    Then I should be able to edit notification


  @regression
  Scenario: Verify users can create a new notification and update status to Exception
    Given I am logged into appian as "super1" user
    And I create new notification with following data
      | type | 1 |
    Then I should see the stored notification
    And I update status of stored notification to "Exception"
    Then I should see the notification displayed in exception page