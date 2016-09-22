@ignore @regressionAll
Feature: This feature should be ignored
  So that I can trust the system

  @regression @ignore
  Scenario Outline: Verify invoice processing of type 1 2 and 3 notification with ingredient and toxicology report
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<price>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<status>"
    Then The notification status should update to "<status2>"
    Examples:
      | user | type | price | status | ingredient  | status2    |
      | rdt1 | 1    | 150   | Paid   | SUPPLEMENT1 | Successful |
      | rdt1 | 2    | 80    | Paid   | SUPPLEMENT2 | Successful |
      | rdt1 | 3    | 150   | Paid   | SUPPLEMENT3 | Successful |

  @regression
  Scenario Outline: Verify audit log functions correctly
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<statusFrom>"
    When I update status of an existing notification to "<status>"
    Then The notification status should update to "<status>"
    And Verify audit log details "<status>,<details>"
    Examples:
      | user   | statusFrom | status | details                                                               |
      | super1 | Uploaded   | Unpaid | action=Update,user=Super 1,comment=Manage Notification,timestamp=GMT+ |
      #| super1 | Unpaid     | Uploaded | action=Update,user=Super 1,comment=Manage Notification,timestamp=GMT+ |


  @regression
  Scenario Outline: Verify adding a new banned substances with and without cas number
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    And I add a substance "<substance>" with following details "<commaDelimitedDetails>"
    Then I should see the new substance in the manage substance page
    Examples:
      | user | substance | commaDelimitedDetails                 |
      | ipu1 | random    | banned=true,permissible=true,cas=true |
      | ipu1 | random    | banned=true,permissible=true,cas=false |


  @regression
  Scenario: Verify error message is displayed when value are more than 100 or less than 0 for quality assurance
    Given I am logged into appian as "ipumanager1" user
    And I update qa percentage to "101"
    Then I should see "value must be between" in the error message


  @regression
  Scenario: Verify quality assurance is updated successfully
    Given I am logged into appian as "ipumanager1" user
    And I update qa percentage to "random"
    Then I should see qa percentage updated to "random"


  @regression
  Scenario: Verify edit notification is only available to IPU super users
    Given I login to appian as "super1" user
    And I have notifications
    Then I should be able to edit notification


  @regression
  Scenario: Verify users can search for notification using the ecid and than submitter name
    Given I am logged into appian as "super1" user
    When I search for an existing notification by "ecid" for text "random"
    Then I should see only 1 notification
    When I search for the stored submitter name
    Then I should see 1 or more notification


  @regression
  Scenario: Verify users can create a new notification and update status to Exception
    Given I am logged into appian as "super1" user
    And I create new notification with following data
      | type | 1 |
    Then I should see the stored notification
    And I update status of stored notification to "Exception"
    Then I should see the notification displayed in exception page


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

