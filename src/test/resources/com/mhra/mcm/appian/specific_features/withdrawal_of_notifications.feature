Feature: As a Finance user, I want to be emailed a consolidated spreadsheet when notifications are withdrawn,
  so that I can end the finance processing for the notification.


  @mcm-92 @wip
  Scenario Outline: Withdrawal of Invoice email should be generated
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    Then I should see the stored notification with status set to "<status>"
    And I update the stored notification to type "4" withdrawal
    When I login as "fin1" and generate a withdrawal invoice
    Then I receive an withdrawal email with heading "Withdrawn Notifications" from appian in next 2 min for "" notifications
    And The invoices should contains the stored ecid
    Examples:
      | user | type | price | ingredient  | glcode | status              |
      | super1 | 1    | 150   | SUPPLEMENT1 | 1770   | Ready for Invoicing |
      | rdt1 | 2    | 80    | SUPPLEMENT2 | 1771   | Ready for Invoicing |
      | rdt1 | 3    | 150   | SUPPLEMENT3 | 1770   | Ready for Invoicing |


  @mcm-92 @wip
  Scenario: Manually withdraw notification and verify Withdrawal of Invoice email should be generated
    Given I am logged into appian as "super1" user
    And I goto notifications page and update status of an existing notification to "Withdrawn"
#    And Set a new ecid for stored notification
    When I login as "fin1" and generate a withdrawal invoice
    Then I receive an withdrawal email with heading "Withdrawn Notifications" from appian in next 2 min for "" notifications
    And The invoices should contains the stored ecid