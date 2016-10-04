Feature: As a super user, I can update the business rules in place for notifications, so that the system complies with the most recent legislation from the EU.


  @mcm-45
  Scenario: Business rule functionality is only available to IPU super users
    Given I login to appian as "super1" user
    And I go to actions page
    Then I should "see" the option to update business rules

  @mcm-45
  Scenario Outline: Business rule functionality is not available to finance, rdt, comms, vrmm and ipu users
    Given I login to appian as "<user>" user
    And I go to actions page
    Then I should "not see" the option to update business rules
    Examples:
      | user  |
      | ipu1  |
      | fin1  |
      | rdt1  |
      | vrmm1 |
      | comm1 |


  @wip
  Scenario Outline: Only notifications created AFTER the new business rules are submitted will use the new business rules
    Given I am in appian and relogin as user "super1"
    When I update business rules for product type "Other" and set "<productName>" to "<nicotineConcetration>"
    Given I am in appian and relogin as user "<user>"
    And I create new notification with following data
      | type                       | <type>                       |
      | ingredient                 | <ingredient>                 |
      | batteryWattageLiquidVolume | <batteryWattageLiquidVolume> |
      | nicotineConcetration       | <nicotineConcetration>       |
      | productType                | <productType>                |
    Examples:
      | user | type | ingredient | batteryWattageLiquidVolume | nicotineConcetration | productType | productName       |
      | rdt1 | 1    | BR_SUPPA1  |                            | 22                   | 9           | Nicotine Strength |
      #| rdt1 | 1    | BR_SUPPA2  |                              | 22                   | 9 |Nicotine Strength|
