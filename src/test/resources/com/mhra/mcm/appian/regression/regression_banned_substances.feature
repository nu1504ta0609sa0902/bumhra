@regression_banned_substances
Feature: As a user I need to quickly verify there is no regression issues with banned substances
  So that I can trust the system


  @regression
  Scenario Outline: Verify adding a new banned substances with and without cas number
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    And I add a substance "<substance>" with following details "<commaDelimitedDetails>"
    Then I should see the new substance in the manage substance page
    Examples:
      | user | substance | commaDelimitedDetails                  |
      | ipu1 | random    | banned=true,permissible=true,cas=false |
      | ipu1 | random    | banned=true,permissible=true,cas=true  |


  @regression
  Scenario Outline: Users should be able to update the status of banned substances
#Create a substance
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    And I add a substance "<substance1>" which "<banned>" banned
#Update status of substance
    When I go to manage substance page
    When I search and update status of "<substance2>" substance to "<updateBanned>" banned
#Verify actively banned status is updated
    And I search for stored substance name which "<updateBanned>" banned
    Then I should see substance "<updateBanned>" banned
    Examples:
      | user | substance1 | substance2 | banned | updateBanned |
      | ipu1 | random     | stored     | is not | is           |
      | ipu1 | random     | stored     | is     | is not       |

