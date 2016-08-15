Feature: As an IPU user, I want the ability to see a list of notifications that are in an "Exception" status
  so that I can take the appropriate action


  Scenario: Update existing notification to Exception status
    Given I am logged into appian as "super1" user
    And I update status of an existing notification to "Exception"
    Then I should see the notification displayed in exception page