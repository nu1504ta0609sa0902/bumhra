Feature: Create notifications and invoices for type 4 submissions
  So that I can verify system is processing type 4 notifications correctly


  @type4 @mcm-68 @mcm-36 @wip
  Scenario Outline: Create an invoice processing of type 4 notification
    Given I am logged into appian as "<user>" user
    And I select a previous notification with ecid "<previousECID>"
    When I create new notification with following data
      | type      | <type> |
      | tcaNumber |        |
#      | ingredient | <ingredient> |
#    And I attach a toxicology report for "<ingredient>"
    Then I should see the stored notification with status set to "Ready for Invoicing"
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<price>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<status>"
    Examples:
      | user | type | price | status | ingredient | previousECID |
      | rdt1 | 4    | 150   | Paid   | SUPPA1     | random       |


  @type4
  Scenario Outline: Verify invoice processing  of type 4 notification with TCA number
    Given I am logged into appian as "rdt1" user
    And I select a previous notification with ecid "<previousECID>"
    And I create new notification with following data
      | type | <type> |
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    When I login as "fin1" and generate a standard invoice
    Then I should see the stored notification with status set to "<statusWhenInvoiced>"
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<amountToInvoice>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<statusInvoicePaid>"
    Examples:
      | type | statusWithTCANumber | statusWhenInvoiced | statusInvoicePaid | amountToInvoice |
      | 4    | Ready for Invoicing | Unpaid             | Paid              | 150             |


  @type4
  Scenario Outline: Verify invoice processing of type 4 notification without a TCA number
    Given I am logged into appian as "rdt1" user
    And I create new notification with following data
      | type          | <type>                    |
      | tcaNumber     |                           |
      | submitterName | <submitterNameGeneration> |
    Then I should see the stored notification with status set to "<initialStatus>"
    And I should see new task generated for the stored notification
    When I set a new TCA number for the notification
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    When I login as "fin1" and generate a standard invoice
    Then I should see the stored notification with status set to "<statusWhenInvoiced>"
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<amountToInvoice>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<statusInvoicePaid>"
    Examples:
      | type | statusWithTCANumber | statusWhenInvoiced | statusInvoicePaid | amountToInvoice | initialStatus | submitterNameGeneration |
      | 4    | Ready for Invoicing | Unpaid             | Paid              | 150             | Uploaded      | random                  |


  @type4
  Scenario Outline: POC example of invoice processing of type 4 notification with ingredient and toxicology report
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
      | rdt1 | 4    | 150   | Paid   | SUPPLEMENT1 | Successful |

