Feature: As a tester I would like to debug

  @ignore
  Scenario Outline: Create lots of notifications
    Given I am logged into appian as "<user>" user
    And I create new notification
    Then I should see the stored notification
    Examples:
      | user   |
      | super1 |
#      | fin1   |
#      | rdt1   |
#      | comm1  |
#      | ipu1   |
#      | vrmm1  |



  @ignore
  Scenario: Check price for an existing notification
    Given I am logged into appian as "fin1" user
    And I have a notification "79894-16-47650" generated
    When I generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 100 min with correct price "150" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "Paid"


  @ignore
  Scenario: Add toxicology report to existing notification
    Given I am logged into appian as "rdt1" user
    And I have a notification "12626-16-53234" generated


  @ignore
  Scenario: Verify new TCA task exists with EC id
    Given I am logged into appian as "rdt1" user
    And I should see new task generated for the submitter "TestE2E_2_74806" with ecid "34382-16-70839"
    When I set a new TCA number for the notification
    Then I should see the stored notification with status set to "Ready for Invoicing"


  @ignore
  Scenario Outline: Users can change the notification status to another status for existing notification
    Given I am logged into appian as "<user>" user
    When I view an existing notification with ecid  "<ecid>" in exception page
    And Update the status of stored notification to "<status>"
    Then I expect the notification status should be "<status>"
    Examples:
      | user   | status   | ecid           |
      | super1 | Uploaded | 36550-16-34422 |
      | super1 | Uploaded | 19585-16-36777 |
      | super1 | Uploaded | 95114-16-33516 |
      | super1 | Uploaded | 83374-16-64750 |
      | super1 | Uploaded | 24335-16-58479 |
      | super1 | Uploaded | 65895-16-37521 |
      | super1 | Uploaded | 58752-16-57074 |