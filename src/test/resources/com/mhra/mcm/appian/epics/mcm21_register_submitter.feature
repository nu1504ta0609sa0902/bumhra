Feature: Once Appian has parsed the notification data, the system will register the submitter of the notification and wait for a payment to be made.

  @mcm-21
  Scenario Outline: Register a new submitter without a tcaNumber and verify status
    Given I am logged into appian as "rdt1" user
    And I create new notification with following data
      | type          | <type>                    |
      | tcaNumber     |                           |
      | submitterName | <submitterNameGeneration> |
    Then I should see the stored notification with status set to "<initialStatus>"
    And I should see new task generated for the stored notification
    Examples:
      | type | submitterNameGeneration | initialStatus |
      | 1    | random                  | Uploaded      |
      | 2    | random                  | Uploaded      |
      | 3    | random                  | Uploaded      |
      | 4    | random                  | Uploaded      |


  @mcm-21 @e2e
  Scenario Outline: Register a new submitter without a tcaNumber than generate an invoice and wait for payment
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
    Examples:
      | type | submitterNameGeneration | initialStatus | statusWithTCANumber | statusWhenInvoiced |
      | 1    | random                  | Uploaded      | Ready for Invoicing | Unpaid             |
      | 2    | random                  | Uploaded      | Ready for Invoicing | Unpaid             |
      | 3    | random                  | Uploaded      | Ready for Invoicing | Unpaid             |
      | 4    | random                  | Uploaded      | Ready for Invoicing | Unpaid             |

  