Feature: As a member of the IPU, I can update the list of banned substances
  so that it aligns with the most recent medical research and ingredients recommendations.

  @mcm-44
  Scenario Outline: Only IPU users should be able to edit or update banned substances
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    Then I should "<action>" able to add a new substance
    Examples:
      | user  | action |
      | ipu1  | be     |
      | rdt1  | not be |
      | vrmm1 | not be |
      | comm1 | not be |
      | fin1  | not be |


  @mcm-44
  Scenario Outline: Add a new banned substances
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    And I add a substance "<substance>" which "<banned>" banned
    Then I should see the new substance in the manage substance page
    Examples:
      | user | substance | banned |
      | ipu1 | random    | is     |
#      | ipu1 | random    | is not |


  @mcm-44
  Scenario Outline: Update status of an existing substances
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    And I add a substance "<substance>" which "<banned>" banned
    And I search for substance containing "" and "<banned>" banned
    Then I should see substance "<banned>" banned
    Examples:
      | user | substance | banned |
      | ipu1 | random    | is not |
#      | ipu1 | random               | is |

