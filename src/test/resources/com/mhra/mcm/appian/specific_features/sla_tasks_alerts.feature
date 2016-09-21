@ignore
Feature: As a manager I want to view the work queues for tasks in the system as well as their SLA
  so that I can take action if an SLA breach is possible


  @mcm-28 @wip
  Scenario Outline: Check to see if all assigned tasks are listed in SLA Alerts
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type      | <type> |
      | tcaNumber |        |
    Then I should see the stored notification with status set to "Uploaded"
    Examples:
      | user | type | price | status | ingredient |
      | rdt1 | 1    | 150   | Paid   | SUPPA1     |