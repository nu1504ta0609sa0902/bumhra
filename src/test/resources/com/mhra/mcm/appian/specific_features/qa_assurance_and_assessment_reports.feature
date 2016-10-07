Feature: As a ipumanager user I should be able to direct certain percentage of notifications for manual quality assurance
  So that we can make sure the system is working as expected


  @mcm-67
  Scenario Outline: Verify error message is displayed when value are more than 100 or less than 0 for quality assurance
    Given I am logged into appian as "<user>" user
    And I update qa percentage to "<qa_percentage>"
    Then I should see "<error_msg>" in the error message
    Examples:
      | user        | qa_percentage | error_msg             |
      | ipumanager1 |               | A value is required   |
      | ipumanager1 | 101           | value must be between |
      | ipumanager1 | -1            | value must be between |
      | ipumanager1 | 1010          | value must be between |
      | ipumanager1 | -1000         | value must be between |


  @mcm-67
  Scenario Outline: Verify quality assurance is updated successfully
    Given I am logged into appian as "<user>" user
    And I update qa percentage to "<qa_percentage>"
    Then I should see qa percentage updated to "<qa_percentage>"
    Examples:
      | user        | qa_percentage |
      | ipumanager1 | 1             |
      | ipumanager1 | 20            |
      | ipumanager1 | 50            |
      | ipumanager1 | 100           |
      | ipumanager1 | 0             |
      | ipumanager1 | 10            |


  @mcm-51
  Scenario Outline: The system will display visually in a read only format, which rules the notification passed and which rules it failed.
    Given I am logged into appian as "<user>" user
    When I go to notifications page and filter by status "<status>" and view a random notification
    And I view the assessment report
    Then The assessment report should show the values which were checked
    Examples:
      | user   | status            |
      | super1 | Successful        |
      | super1 | Failed            |
      | super1 | Quality Assurance |


  @mcm-51
  Scenario Outline: Users should be able to add comments to notifications
    Given I am logged into appian as "<user>" user
    When I go to notifications page and filter by status "<status>" and view a random notification
    When I add comment "<comment>" to selected notification
    Then I should see comment "<comment>" displayed in notification for user "<userName>"
    Examples:
      | user | status            | userName | comment |
      | ipu1 | Successful        | IPU 1    | random  |
      | ipu1 | Failed            | IPU 1    | random  |
      | ipu1 | Quality Assurance | IPU 1    | random  |


  @mcm-51
  Scenario Outline: Users should be able to accept or reject notifications in qa status
    Given I am logged into appian as "<user>" user
    When I view a task with heading containing "<heading>"
    And I should see option to accept or reject qa tasks
    Examples:
      | user | status            | heading                    | comment |
      | ipu1 | Quality Assurance | Review Notification for QA | random  |