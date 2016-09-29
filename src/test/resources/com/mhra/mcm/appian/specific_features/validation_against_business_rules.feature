Feature: The system shall automatically check against the business rules defined

  @mcm-42
  Scenario Outline: Check the system will not run the decision table for a notification until the notification is in a PAID status.
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type                       | <type>                       |
      | ingredient                 | <ingredient>                 |
      | batteryWattageLiquidVolume | <batteryWattageLiquidVolume> |
    And I attach a toxicology report for "<ingredient>"
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    And Audit log displays correct status "<statusWithTCANumber>" user name "<user_name>" and comment
    And Verify audit log details "<statusWithTCANumber>,<details>"
    Examples:
      | user | type | ingredient | user_name | statusWithTCANumber | batteryWattageLiquidVolume | details                                                  |
      | rdt1 | 1    | SUPPA1     | RDT       | Ready for Invoicing | 12                         | action=Set Up Complete,user=RDT,comment= ,timestamp=GMT+ |


  @mcm-42
  Scenario Outline: Check when invoices are generated the system will not run the decision table for a notification until the notification is in a PAID status
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type                       | <type>                       |
      | ingredient                 | <ingredient>                 |
      | batteryWattageLiquidVolume | <batteryWattageLiquidVolume> |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    And Audit log displays correct status "<statusWithTCANumber>" user name "<user_name>" and comment
    And Verify audit log details "<statusWithTCANumber>,<details>"
    Examples:
      | user | type | ingredient | user_name | statusWithTCANumber | batteryWattageLiquidVolume | details                                                                       |
      | rdt1 | 1    | SUPPA1     |           | Unpaid              | 12                         | action=Update,user=Periodic Invoicing Process,comment=Docuemnt,timestamp=GMT+ |


  @mcm-42
  Scenario Outline: Check when invoices are paid the system will not run the decision table for a notification until the notification is in a PAID status
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type                       | <type>                       |
      | ingredient                 | <ingredient>                 |
      | batteryWattageLiquidVolume | <batteryWattageLiquidVolume> |
      | nicotineConcetration       | <nicotineConcetration>       |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "" notifications
    When I send paid email response back to appian
    Then I expect the notification status should be "Paid"
    And The notification status should update to "<statusWithTCANumber>"
    And I should see the stored notification with status set to "<statusWithTCANumber>"
    And Audit log displays correct status "<statusWithTCANumber>" user name "<user_name>" and comment
    And Verify audit log details "<statusWithTCANumber>,<details>"
    Examples:
      | user | type | ingredient | user_name | statusWithTCANumber | batteryWattageLiquidVolume | nicotineConcetration | details                                                          |
      | rdt1 | 1    | SUPPA1     | RDT       | Failed              | 12                         |                      | action=Failed,user=Appian Administrator,comment= ,timestamp=GMT+ |
      | rdt1 | 1    | SUPPA1     | RDT       | Failed              |                            | 22                   | action=Failed,user=Appian Administrator,comment= ,timestamp=GMT+ |


  @mcm-42 @mcm-44
  Scenario Outline: Check refusal for notification email is received when banned substances are added to a notification
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "" notifications
    When I send paid email response back to appian
    Then I expect the notification status should be "Unpaid"
    And I receive an refusal email with heading "Refusal For Notification" from appian in next 2 min for "" notifications
    And The notification status should update to "<statusWithTCANumber>"
    Examples:
      | user | type | ingredient | statusWithTCANumber | details                                                          |
      | rdt1 | 1    | Vitamin    | Failed              | action=Failed,user=Appian Administrator,comment= ,timestamp=GMT+ |
      | rdt1 | 1    | Retinal    | Failed              | action=Failed,user=Appian Administrator,comment= ,timestamp=GMT+ |


  @mcm-44
  Scenario Outline: Check when ALLOWABLE substances are added the system will Pass the notification
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    And I add a substance "<substance>" which "<banned>" banned
    Then I should see the new substance in the manage substance page
    And I create new notification with following data
      | type       | <type> |
      | ingredient | stored |
    And I attach a toxicology report for "stored"
    When I login as "fin1" and generate a standard invoice
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "" notifications
    When I send paid email response back to appian
    Then I expect the notification status should be "Unpaid"
    Then I expect the notification status should be "<status>"
    Examples:
      | user | type | status     | substance | banned |
      | ipu1 | 1    | Successful | random    | is not |
#      | ipu1 | 1    | Failed              | random    | is |


  @mcm-44 @mcm-43
  Scenario Outline: Check when water is added as ingredient the system will Pass the notification
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "" notifications
    When I send paid email response back to appian
    Then I expect the notification status should be "Paid"
    Then I expect the notification status should be "<status>"
    Examples:
      | user | type | ingredient | status     |
      | ipu1 | 1    | Water      | Successful |


  @mcm-43
  Scenario Outline: Check refusal for notification email is received when notification report is not added
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    Then I should see the stored notification with status set to "<statusOnLoad>"
    When I login as "fin1" and generate a standard invoice
    And I receive an invoice email with heading "Uninvoiced Notifications" from appian in next <time> min for "" notifications
    When I send paid email response back to appian
    Then I should see the stored notification with status set to "<statusBeforeSuccessful>"
    And I receive an refusal email with heading "<refusedEmail>" from appian in next 2 min for "" notifications
    And I should see the stored notification with status set to "<statusAfterSuccessful>"
    Examples:
      | user | type | ingredient | statusOnLoad        | statusAfterSuccessful | statusBeforeSuccessful | refusedEmail             | time |
      | rdt1 | 1    | Sugar      | Ready for Invoicing | Failed                |                        | Refusal For Notification | 2    |
      | rdt1 | 1    | EvianWater | Ready for Invoicing | Failed                |                        | Refusal For Notification | 2    |
      | rdt1 | 1    | Water      | Ready for Invoicing | Successful            | Paid                   |                          | 2    |

  @mcm-43
  Scenario: Check to see if toxicology reports can be viewed for existing notification record
    Given I am logged into appian as "super1" user
    When I go to the notifications page
    And I view an random notification with status "Successful"
    Then I should be able to view documents page
    And There should be at least "0" or more documents

  @mcm-43
  Scenario Outline: Check to see if toxicology reports can be viewed for new notification record
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    Then I should see the stored notification with status set to "<status>"
    Then I should be able to view documents page
    And There should be at least "1" or more documents
    And There should be at least a document for stored report name
    Examples:
      | user | type | ingredient | status              |
      | ipu1 | 1    | SupplementD1      | Ready for Invoicing |