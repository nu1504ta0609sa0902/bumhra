Feature: As an IPU super user, I want the ability to edit notification

  @mcm-106 @mcm-107 @regression
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

