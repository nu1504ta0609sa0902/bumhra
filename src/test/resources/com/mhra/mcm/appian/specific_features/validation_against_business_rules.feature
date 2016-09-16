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


  @mcm-42
  Scenario Outline: Check when banned substances are added the system will not run the decision table for a notification until the notification is in a PAID status
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
      | rdt1 | 1    | Healthy    | Failed              | action=Failed,user=Appian Administrator,comment= ,timestamp=GMT+ |


