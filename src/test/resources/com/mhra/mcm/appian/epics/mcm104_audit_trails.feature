@ignore
Feature: The Appian system shall support a full end to end audit trail for each notification viewable by users
  so that users are aware of the stages a notification has gone through.

  @mcm-103 @mcm-94 @mcm-38 @mcm-97 @mcm-42
  Scenario Outline: Verify audit log after a new notification invoice is paid
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type       | <type>       |
      | tcaNumber  |              |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    And I should see new task generated for the stored notification
    When I set a new TCA number for the notification
    Then I should see the stored notification with status set to "<statusWithTCANumber>"
    When I login as "fin1" and generate a standard invoice
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "<price>" notifications
    When I send paid email response back to appian
    Then The notification status should update to "<status>"
    And The notification status should update to "<status2>"
    And Audit log displays correct status "<status2>" user name "<user_name>" and comment
    And Verify audit log details "Uploaded" are correct
    Examples:
      | user | type | price | status | ingredient | user_name          | status2    | statusWithTCANumber |
      | rdt1 | 1    | 150   | Paid   | SUPPA1     | mhra.uat@gmail.com | Successful | Ready for Invoicing |


    # Depends on having new notifications which are to be invoiced
  @ignore @mcm-103 @mcm-94 @mcm-38
  Scenario Outline: Verify audit log after an existing notification invoice is paid
    Given I am logged into appian as "fin1" user
    And I generate a standard invoice
    Then I receive an invoice email with heading "Uniinvoiced Notifications" from appian in next 2 min for "" notifications
    When I select a random invoice and send paid email response back to appian
    Then The notification status should update to "<status>"
    And Audit log displays correct status "<status>" user name "<user_name>" and comment
    Examples:
      | user | type | price | status | ingredient | user_name          |
      | rdt1 | 1    | 150   | Paid   | SUPPA1     | mhra.uat@gmail.com |


  @mcm-103
  Scenario Outline: Verify audit log for existing notifications
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<status>"
    And I view an random notification with status "<status>"
    Then Audit log displays correct status "<status>" user name "<user_name>" and comment
    Examples:
      | user   | status     | user_name          |
      | rdt1   | Paid       | mhra.uat@gmail.com |
      | ipu1   | Unpaid     |                    |
      | super1 | Uploaded   |                    |
      | fin1   | Successful |                    |
      | fin1   | Published  |                    |


  @mcm-107
  Scenario Outline: Check audit log shows correct details after manual updates of notifications
    Given I am logged into appian as "<user>" user
    When I go to the notifications page
    And I filter by status "<statusFrom>"
    When I update status of an existing notification to "<status>"
    Then The notification status should update to "<status>"
    And Verify audit log details "<status>,<details>"
    Examples:
      | user   | statusFrom | status   |  details                                 |
      | super1 | Uploaded   | Unpaid   |  action=Update,user=Super 1,comment= ,timestamp=GMT+ |
      | super1 | Unpaid     | Uploaded |   action=Update,user=Super 1,comment=Manage Notification,timestamp=GMT+   |
      | super1 | Uploaded   | Failed    | action=Update,user=Super 1,comment=Manage Notification,timestamp=GMT+   |
      | super1 | Failed     | Uploaded   | action=Update,user=Super 1,comment=Manage Notification,timestamp=GMT+   |