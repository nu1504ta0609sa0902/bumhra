Feature: As an Appian user I should be able to search and filter notifications
  So that I can select the correct notification quickly


  @mcm-32
  Scenario Outline: Search for notification using the ecid and than submitter name
    Given I am logged into appian as "<user>" user
    When I search for an existing notification by "ecid"
    Then I should see only 1 notification
    When I search for the stored submitter name
    Then I should see 1 or more notification
    Examples:
      | user   |
      | super1 |
      | rdt1   |


  @mcm-32
  Scenario Outline: Search for notification using partial ecid
    Given I am logged into appian as "<user>" user
    When I search for an existing notification by partial "ecid"
    Then I should see at least 1 notification
    Examples:
      | user   |
      | comm1  |
      | super1 |
      | rdt1   |


  @mcm-32
  Scenario Outline: Check users are able to filter by different type of status
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<status>"
    Then I should only see notifications where status is "<status>"
    When I go to the notifications page
    And I filter by status "<status2>"
    Then I should only see notifications where status is "<status2>"
    Examples:
      | user  | status            | status2    |
      | rdt1  | Uploaded          | Paid       |
      | fin1  | Paid              | Successful |
      | fin1  | Unpaid            | Paid       |
      | vrmm1 | Failed            | Published  |
      | comm1 | Successful        | Paid       |
      | ipu1  | Quality Assurance | Successful |
      | fin1  | Published         | Paid       |


  @mcm-32
  Scenario Outline: Filtering by specific status should only return notifications related to the selected status
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<filterBy>"
    Then I should only see notifications where status is "<filterBy>"
    When I clear the selected filter "<filterBy>"
    And I filter by status "<filterBy2>"
    Then I should only see notifications where status is "<filterBy2>"
    Examples:
      | user   | filterBy          | filterBy2  |
      | rdt1   | Uploaded          | Paid       |
      | ipu1   | Quality Assurance | Successful |
      | fin1   | Published         | Paid       |