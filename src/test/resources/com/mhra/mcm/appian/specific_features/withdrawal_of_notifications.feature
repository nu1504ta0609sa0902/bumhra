Feature: As a Finance user, I want to be emailed a consolidated spreadsheet when notifications are withdrawn,
  so that I can end the finance processing for the notification.


  @mcm-92 @wip
  Scenario Outline: Withdrawal of Invoice email should be generated
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type       | <type>       |
      | tcaNumber     |                           |
#      | ingredient | <ingredient> |
#    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a withdrawal invoice
    Then I receive an withdrawal email with heading "Withdrawn Notifications" from appian in next 2 min for "" notifications
    And The invoices should contains the stored ecid
    Examples:
      | user | type | price | ingredient  | glcode |
      | rdt1 | 1    | 150   | SUPPLEMENT1 | 1770   |
      | rdt1 | 2    | 80    | SUPPLEMENT2 | 1771   |
      | rdt1 | 3    | 150   | SUPPLEMENT3 | 1770   |


  @mcm-92 @wip
  Scenario: Manually withdraw notification and verify Withdrawal of Invoice email should be generated
    Given I am logged into appian as "super1" user
    And I goto notifications page and update status of an existing notification to "Withdrawn"
    When I login as "fin1" and generate a withdrawal invoice
    Then I receive an withdrawal email with heading "Withdrawn Notifications" from appian in next 2 min for "" notifications