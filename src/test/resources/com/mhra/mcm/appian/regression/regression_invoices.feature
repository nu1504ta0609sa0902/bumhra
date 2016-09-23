@regression_invoices
Feature: As a user I need to quickly verify there is no regression issues with invoice processing
  So that I can trust the system

  @regression
  Scenario Outline: Verify invoice processing of type 1 2 and 3 notification with ingredient and toxicology report
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
#      | rdt1 | 2    | 80    | Paid   | SUPPLEMENT2 | Successful |
#      | rdt1 | 3    | 150   | Paid   | SUPPLEMENT3 | Successful |

