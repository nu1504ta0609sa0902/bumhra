Feature: As a ipumanager user I should be able to direct certain percentage of notifications for manual quality assurance
  So that we can make sure the system is working as expected


  @mcm-67
  Scenario Outline: Verify error message is displayed when value are more than 100 or less than 0 for quality assurance
    Given I am logged into appian as "<user>" user
    And I update qa percentage to "<qa_percentage>"
    Then I should see "<error_msg>" in the error message
    Examples:
      | user        | qa_percentage | error_msg             |
      | ipumanager1 |            | value must be between |
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