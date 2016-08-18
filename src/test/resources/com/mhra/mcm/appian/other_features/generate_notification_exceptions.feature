Feature: As an IPU user, I want the ability to see a list of notifications that are in an "Exception" status
  so that I can take the appropriate action


  @mcm-89 @mcm-72
  Scenario Outline: Create a new notification and update status to Exception
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type | 1 |
    Then I should see the stored notification
    And I update status of stored notification to "Exception"
    Then I should see the notification displayed in exception page
    Examples:
    |user|
    | super1 |
    | super1 |
    | super2 |
    | super1 |
    | super1 |


  @mcm-89 @mcm-72
  Scenario Outline: Update existing notification to Exception status
    Given I am logged into appian as "<user>" user
    And I update status of an existing notification to "Exception"
    Then I should see the notification displayed in exception page
  Examples:
      |user|
      | super1 |
      | super1 |

