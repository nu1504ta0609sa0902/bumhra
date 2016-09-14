Feature: The system shall automatically check against the business rules defined

  @mcm-42
  Scenario Outline: Check the system will not run the decision table for a notification until the notification is in a PAID status.
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type          | <type>          |
      | ingredient    | <ingredient>    |
      | eLiquidVolume | <eLiquidVolume> |
    And I attach a toxicology report for "<ingredient>"
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    And Audit log displays correct status "<statusWithTCANumber>" user name "<user_name>" and comment
    And Verify "<statusWithTCANumber>" details are correct
    Examples:
      | user | type | price | status | ingredient | user_name | status2    | statusWithTCANumber | eLiquidVolume |
      | rdt1 | 1    | 150   | Paid   | SUPPA1     | RDT       | Successful | Ready for Invoicing | 12            |