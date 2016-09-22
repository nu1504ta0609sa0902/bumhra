@regression_miscellaneous
Feature: As a user I need to quickly verify there is no regression issues with general features such as searching qa etc
  So that I can trust the system

  @regression
  Scenario: Verify error message is displayed when value are more than 100 or less than 0 for quality assurance
    Given I am logged into appian as "ipumanager1" user
    And I update qa percentage to "101"
    Then I should see "value must be between" in the error message


  @regression
  Scenario: Verify quality assurance is updated successfully
    Given I am logged into appian as "ipumanager1" user
    And I update qa percentage to "random"
    Then I should see qa percentage updated to "random"

  @regression
  Scenario: Verify users can search for notification using the ecid and than submitter name
    Given I am logged into appian as "super1" user
    When I search for an existing notification by "ecid" for text "random"
    Then I should see only 1 notification
    When I search for the stored submitter name
    Then I should see 1 or more notification