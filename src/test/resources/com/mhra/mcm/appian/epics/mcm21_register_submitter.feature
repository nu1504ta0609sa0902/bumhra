Feature: Once Appian has parsed the notification data, the system will register the submitter of the notification and wait for a payment to be made.

  @mcm-21 @mcm-50
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
#      | 2    | random                  | Uploaded      |
#      | 3    | random                  | Uploaded      |
#      | 4    | random                  | Uploaded      |


  @mcm-21 @e2e @mcm-50 @mcm-70
  Scenario Outline: Register a new submitter without a tcaNumber than add a new tcaNumber for the submitter
    Given I am logged into appian as "rdt1" user
    And I create new notification with following data
      | type          | <type>                    |
      | tcaNumber     |                           |
      | submitterName | <submitterNameGeneration> |
    Then I should see the stored notification with status set to "<initialStatus>"
    And I should see new task generated for the stored notification
    When I set a new TCA number for the notification
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    Examples:
      | type | submitterNameGeneration | initialStatus | statusWithTCANumber |
      | 1    | random                  | Uploaded      | Ready for Invoicing |
#      | 2    | random                  | Uploaded      | Ready for Invoicing |
#      | 3    | random                  | Uploaded      | Ready for Invoicing |
#      | 4    | random                  | Uploaded      | Ready for Invoicing |


  @mcm-21 @mcm-70
  Scenario Outline: Check to see invoice is not created without a TCA number
    Given I am logged into appian as "rdt1" user
    And I create new notification with following data
      | type          | <type>                    |
      | tcaNumber     |                           |
      | submitterName | <submitterNameGeneration> |
    Then I should see the stored notification with status set to "<initialStatus>"
    When I login as "fin1" and generate a standard invoice
    Then If I receive an invoice email with heading "" from appian in next 2 min than the invoice should not contain my notification
    Examples:
      | type | submitterNameGeneration | initialStatus |
      | 1    | random                  | Uploaded      |
#       | 2    | random                  | Uploaded      |
#       | 3    | random                  | Uploaded      |
#       | 4    | random                  | Uploaded      |

