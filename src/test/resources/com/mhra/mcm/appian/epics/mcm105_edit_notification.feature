Feature: As an IPU super user, I want the ability to edit notification

  @mcm-106 @mcm-107
  Scenario: Edit notification is only available to IPU super users
    Given I login to appian as "super1" user
    And I have notifications
    Then I should be able to edit notification

  @mcm-106 @mcm-107
  Scenario Outline: Edit notification is not available to finance, rdt, comms, vrmm and ipu users
    Given I login to appian as "<user>" user
    And I have notifications
    Then I should not be able to edit notification
    Examples:
      | user  |
      | ipu1  |
      | fin1  |
      | rdt1  |
      | vrmm1 |
      | comm1 |

  @mcm-106
  Scenario: Verify IPU super user can change available fields for a notification
    Given I login to appian as "super1" user
    And I have notifications
    And I make change to submitter name by appending "Test1234"
    Then I should see the submitter name containing "Test1234"


  @mcm-107 @ignore
  Scenario Outline: Add new ingredient to existing notification
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<status>"
    And I view an random notification with status "<status>"
    And Add a new ingredient called "<ingredient>"
    Examples:
      | user   | status   | ingredient      |
      | super1 | Unpaid   | SUPPLEMENT_NEW1 |
      | super1 | Uploaded | SUPPLEMENT_NEW2 |


  @mcm-107
  Scenario Outline: Change status of existing notifications
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<statusFrom>"
    When I update status of an existing notification to "<status>"
    Then The notification status should update to "<status>"
    Examples:
      | user   | statusFrom | status   |
      | super1 | Uploaded   | Unpaid   |
      | super1 | Unpaid     | Uploaded |
      | super1 | Uploaded   | Failed   |
      #| super1 | Failed     | Uploaded |


  @mcm-97
  Scenario Outline: Add comments to notifications
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<status>"
    And I view an random notification with status "<status>"
    When I add comment "<comment>" to selected notification
    Then I should see comment "" displayed in notification for user "<userName>"
    Examples:
      | user   | status | comment | userName |
      | rdt1   | Unpaid | random  | RDT 1    |
      | super1 | Paid   | random  | Super 1  |


  @mcm-97
  Scenario Outline: Manage documents should be available to all users
    Given I login to appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<status>"
    And I view an random notification with status "<status>"
    Then I should be able to manage documents
    Examples:
      | user   | status   |
      | super1 | Uploaded |
      | ipu1   | Uploaded |
      | fin1   | Unpaid   |
      | rdt1   | Uploaded |
      | vrmm1  | Unpaid   |
      | comm1  | Unpaid   |


  @mcm-97 @wip @bug
  Scenario Outline: Users can add different types of documents to new notifications
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a "<documentType>" report for "<ingredient>"
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    Examples:
      | user | statusWithTCANumber | documentType                          | ingredient |
      | rdt1 | Ready for Invoicing | E-Cigarette Leaflet                   | SUPPDOC1   |
      | rdt1 | Ready for Invoicing | Product Market Research               | SUPPDOC2   |
      | rdt1 | Ready for Invoicing | Product Studies Summary               | SUPPDOC3   |
      | rdt1 | Ready for Invoicing | Product Unit Packet Picture           | SUPPDOC4   |
      | rdt1 | Ready for Invoicing | Emission Methods                      | SUPPDOC5   |
      | rdt1 | Ready for Invoicing | E-Cigarette Nicotine Dose/Uptake      | SUPPDOC6   |
      | rdt1 | Ready for Invoicing | E-Cigarette Opening/Refill            | SUPPDOC7   |
      | rdt1 | Ready for Invoicing | E-Cigarette Consistent Dosing Methods | SUPPDOC8   |
      | rdt1 | Ready for Invoicing | Product Mode of Sales                 | SUPPDOC9   |
      | rdt1 | Ready for Invoicing | Other                                 | SUPPDOC10  |
      | rdt1 | Ready for Invoicing | Approval Notification                 | SUPPDOC11  |
      | rdt1 | Ready for Invoicing | Failed Notification                   | SUPPDOC12  |
      | rdt1 | Ready for Invoicing | Withdrawal Notification               | SUPPDOC13  |

  @mcm-97 @wip @bug
  Scenario Outline: Users can add different types of documents to existing notifications
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I view an random notification with status "Uploaded"
    And I attach a "<documentType>" report for "<ingredient>"
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    Examples:
      | user | statusWithTCANumber | documentType                          | ingredient |
      | rdt1 | Ready for Invoicing | E-Cigarette Leaflet                   | SUPPDOC1   |
      | rdt1 | Ready for Invoicing | Product Market Research               | SUPPDOC2   |
      | rdt1 | Ready for Invoicing | Product Studies Summary               | SUPPDOC3   |
      | rdt1 | Ready for Invoicing | Product Unit Packet Picture           | SUPPDOC4   |
      | rdt1 | Ready for Invoicing | Emission Methods                      | SUPPDOC5   |
      | rdt1 | Ready for Invoicing | E-Cigarette Nicotine Dose/Uptake      | SUPPDOC6   |
      | rdt1 | Ready for Invoicing | E-Cigarette Opening/Refill            | SUPPDOC7   |
      | rdt1 | Ready for Invoicing | E-Cigarette Consistent Dosing Methods | SUPPDOC8   |
      | rdt1 | Ready for Invoicing | Product Mode of Sales                 | SUPPDOC9   |
      | rdt1 | Ready for Invoicing | Other                                 | SUPPDOC10  |
      | rdt1 | Ready for Invoicing | Approval Notification                 | SUPPDOC11  |
      | rdt1 | Ready for Invoicing | Failed Notification                   | SUPPDOC12  |
      | rdt1 | Ready for Invoicing | Withdrawal Notification               | SUPPDOC13  |


