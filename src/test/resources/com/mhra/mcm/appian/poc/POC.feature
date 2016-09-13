@poc @ignore
Feature: Perform a POC of end to end invoicing process to stakeholders
  So that we can better understand the purpose of automation testing

  @poce2e1
  Scenario Outline: POC perform simple end to end invoice processing of type 1 notification
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type | <type> |
    Then I should see the stored notification
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<price>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<status>"
    Examples:
      | user | type | price | status |
      | rdt1 | 1    | 150   | Paid   |


  @poce2e2
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


  @poce2e3
  Scenario Outline: POC example of invoice processing of type 1 notification without a TCA number
    Given I am logged into appian as "rdt1" user
    When I create new notification with following data
      | type          | <type>                    |
      | tcaNumber     |                           |
      | submitterName | <submitterNameGeneration> |
      | ingredient    | <ingredient>              |
    And I attach a toxicology report for "<ingredient>"
    And I should see new task generated for the stored notification
    When I set a new TCA number for the notification
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    When I login as "fin1" and generate a standard invoice
    Then I should see the stored notification with status set to "<statusWhenInvoiced>"
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<amountToInvoice>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<statusInvoicePaid>"
    Examples:
      | type | statusWithTCANumber | statusWhenInvoiced | statusInvoicePaid | amountToInvoice | initialStatus | submitterNameGeneration | ingredient  |
      | 1    | Ready for Invoicing | Unpaid             | Paid              | 150             | Uploaded      | random                  | Supplement2 |

