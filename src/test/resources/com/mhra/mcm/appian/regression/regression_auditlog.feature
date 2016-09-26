@regression_auditlog
Feature: As a user I need to quickly verify there is no regression issues with audit log
  So that I can trust the system

  @regression
  Scenario Outline: Verify audit log functions correctly
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<statusFrom>"
    When I update status of an existing notification to "<status>"
    Then The notification status should update to "<status>"
    And Verify audit log details "<status>,<details>"
    Examples:
      | user   | statusFrom | status   | details                                                               |
      | super1 | Uploaded   | Unpaid   | action=Update,user=Super 1,comment=Manage Notification,timestamp=GMT+ |
      | super1 | Unpaid     | Uploaded | action=Update,user=Super 1,comment=Manage Notification,timestamp=GMT+ |

