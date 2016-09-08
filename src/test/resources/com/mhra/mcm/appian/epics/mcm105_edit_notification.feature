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


  @mcm-103
  Scenario Outline: Verify audit log for existing notifications
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<status>"
    And I view an random notification with status "<status>"
    And
    Examples:
    | user | status |
    | super1 | Unpaid |


