Feature: As an IPU user, I want the ability to see a list of notifications that are in an "Exception" status
  so that I can take the appropriate action


  Scenario: Update existing notification to Exception status 2
    Given I am logged into appian as "super1" user
    And I update status of an existing notification to "Exception"
    Then I should see the notification displayed in exception page


  Scenario: Create a new notification and update status to Exception
    #Given I am logged into appian as "super1" user
    #And I create new notification with following data
    #  | type | 1 |
    Given I am logged into appian as "super1" user
    And I create new notification
    Then I should see the stored notification
    And I update status of stored notification to "Exception"
    Then I should see the notification displayed in exception page