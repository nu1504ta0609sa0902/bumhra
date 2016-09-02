Feature:Appian system will provide the ability for an RDT user to upload the notification XML file and related attachments manually.


  @mcm-20
  Scenario Outline: Upload different types of notification
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type | <type> |
    Then I should see the stored notification with status set to "<initialStatus>"
    Examples:
      | user | type | initialStatus |
      | ipu1 | 2    | Ready for Invoicing          |
      | ipu1 | 1    | Ready for Invoicing          |


  @mcm-20
  Scenario Outline: Upload different types of notification without TCA number
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type | <type> |
      | tcaNumber |  |
    Then I should see the stored notification with status set to "<initialStatus>"
    Examples:
      | user | type | initialStatus |
      | ipu1 | 1    | Uploaded          |
      | ipu1 | 2    | Uploaded          |

