Feature: As an RDT user, IPU user or a Finance User
  I want to see know which notifications do not have the correct information for being added to the invoice spreadsheet
  so that I can take the appropriate action.


  @mcm-89 @mcm-72
  Scenario: Create a new notification and update status to Exception
    Given I am logged into appian as "super1" user
    And I create new notification with following data
      | type | 1 |
    Then I should see the stored notification
    And I update status of stored notification to "Exception"
    Then I should see the notification displayed in exception page

  @mcm-89 @mcm-72
  Scenario: Update existinkg notification to Exception status
    Given I am logged into appian as "super1" user
    And I goto notifications page and update status of an existing notification to "Exception"
    Then I should see the notification displayed in exception page

