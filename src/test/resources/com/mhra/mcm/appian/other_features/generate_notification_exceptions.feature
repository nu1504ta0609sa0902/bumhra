Feature: As an IPU user, I want the ability to see a list of notifications that are in an "Exception" status
  so that I can take the appropriate action


  @mcm-89 @mcm-72
  Scenario Outline: Create a new notification and update status to Exception
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type | 1 |
    And I update status of stored notification to "Exception"
    Then I should see the notification displayed in exception page
    Examples:
      | user   |
      | super1 |
      | super2 |


  @mcm-89 @mcm-72
  Scenario Outline: Update existing notification to Exception status
    Given I am logged into appian as "<user>" user
    And I update status of an existing notification to "Exception"
    Then I should see the notification displayed in exception page
    Examples:
      | user   |
      | super1 |
      | super2 |


  Scenario Outline: Users can change the notification status to another status
    Given I am logged into appian as "<user>" user
    When I view a notification displayed in exception page
    And Update the status of stored notification to "<status>"
    Then I expect the notification status should be "<status>"
    Examples:
      | user   | status              |
      | super1 | Uploaded            |
      | super2 | Ready for Invoicing |




