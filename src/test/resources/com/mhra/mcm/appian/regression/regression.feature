@regressionAll
Feature: As a user I need to quickly verify there is no regression issues
  So that I can trust the system

  @regression
  Scenario Outline: Add a new banned substances
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    And I add a banned substance "<substance>" to appian
    Then I should see the new substance in the manage substance page
    Examples:
      | user | substance |
      | ipu1 | random    |

  @regression
  Scenario: Verify error message is displayed when value are more than 100 or less than 0 for quality assurance
    Given I am logged into appian as "ipumanager1" user
    And I update qa percentage to "101"
    Then I should see "value must be between" in the error message


  @regression
  Scenario: Verify quality assurance is updated successfully
    Given I am logged into appian as "ipumanager1" user
    And I update qa percentage to "1"
    Then I should see qa percentage updated to "1"


  @regression
  Scenario: Edit notification is only available to IPU super users
    Given I login to appian as "super1" user
    And I have notifications
    Then I should be able to edit notification


  @regression
  Scenario: Create a new notification and update status to Exception
    Given I am logged into appian as "super1" user
    And I create new notification with following data
      | type | 1 |
    Then I should see the stored notification
    And I update status of stored notification to "Exception"
    Then I should see the notification displayed in exception page


  @regression
  Scenario: Search for notification using the ecid and than submitter name
    Given I am logged into appian as "super1" user
    When I search for an existing notification by "ecid"
    Then I should see only 1 notification
    When I search for the stored submitter name
    Then I should see 1 or more notification


  @regression
  Scenario Outline: POC example of invoice processing of type 1 notification with ingredient and toxicology report
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<price>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<status>"
    Then The notification status should update to "<status2>"
    Examples:
      | user | type | price | status | ingredient  | status2    |
      | rdt1 | 1    | 150   | Paid   | SUPPLEMENT1 | Successful |
      | rdt1 | 2    | 80    | Paid   | SUPPLEMENT2 | Successful |
      | rdt1 | 3    | 150   | Paid   | SUPPLEMENT3 | Successful |