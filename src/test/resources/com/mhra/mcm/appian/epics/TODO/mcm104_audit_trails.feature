@ignore
Feature: The Appian system shall support a full end to end audit trail for each notification viewable by users
  so that users are aware of the stages a notification has gone through.

  @mcm-103 @mcm-94 @mcm-38
  Scenario Outline: Verify audit log after a new notification invoice is paid
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<price>" for the stored notification
    #Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "" notifications
    When I send paid email response back to appian
    Then The notification status should update to "<status>"
    And Audit log displays correct user name "<user_name>" and comment
    Examples:
      | user | type | price | status | ingredient | user_name          |
      | rdt1 | 1    | 150   | Paid   | SUPPA1     | mhra.uat@gmail.com |


  @mcm-103 @mcm-94 @mcm-38
  Scenario Outline: Verify audit log after an existing notification invoice is paid
    Given I am logged into appian as "fin1" user
    And I generate a standard invoice
    #Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<price>" for the stored notification
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "" notifications
    #When I send paid email response back to appian
    When I select a random invoice and send paid email response back to appian
    Then The notification status should update to "<status>"
    And Audit log displays correct user name "<user_name>" and comment
    Examples:
      | user | type | price | status | ingredient | user_name          |
      | rdt1 | 1    | 150   | Paid   | SUPPA1     | mhra.uat@gmail.com |
