Feature: Able to login to MHRA application using different credentials

  @wip
  Scenario Outline: Login into to MHRA notifications page using different credentials
    Given I am in appian page
    When I login as user "<user>"
    Then I should see name of logged in user as "<user>"
    Examples:
      | user  |
#      | ipu1  |
      | fin1  |
#      | rdt1  |
#      | vrmm1 |
#      | comm1 |

