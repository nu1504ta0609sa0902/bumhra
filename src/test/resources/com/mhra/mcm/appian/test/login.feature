Feature: Able to login to MHRA application using different credentials



  Scenario Outline: Login into to MHRA notifications page using different credentials
    Given I am in appian page
    When I login as user "<user>"
    Then I should see name of logged in user as "<user>"
    When I re login as user "vrmm1"
    Then I should see name of logged in user as "vrmm1"
    Examples:
      | user  |
      | ipu1  |
      | fin1  |
      | rdt1  |
      | vrmm1 |
      | comm1 |

