Feature: As a Finance user I should receive invoice email with correct data
  So that I am able to bill the right client


  @mcm-87 @mcm-91 @mcm-90 @mcm-109 @mcm-97 @mcm-68 @mcm-101
  Scenario Outline: Invoice spreadsheet should contain GL Code and other necessary invoice details
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<price>" for the stored notification
    And The invoice should contain correct glcode "<glcode>" and other details
    Examples:
      | user | type | price | ingredient  | glcode |
      | rdt1 | 1    | 150   | SUPPLEMENT1 | 1770   |
      | rdt1 | 2    | 80    | SUPPLEMENT2 | 1771   |
      | rdt1 | 3    | 150   | SUPPLEMENT3 | 1770   |


  @mcm-90 @mcm-97 @mcm-101
  Scenario Outline: Invoice spreadsheet should only have unique invoice ids
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type       | <type1>       |
      | ingredient | <ingredient1> |
    And I attach a toxicology report for "<ingredient1>"
    And I create new notification with following data
      | type       | <type2>       |
      | ingredient | <ingredient2> |
    And I attach a toxicology report for "<ingredient2>"
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "" for the stored notification
    And The invoices should be unique by invoice id
    Examples:
      | user | type1 | ingredient1 | type2 | ingredient2 |
      | rdt1 | 1     | SUPPLEMENT1 | 2     | SUPPLEMENT2 |


  @mcm-87 @mcm-37 @mcm-109 @mcm-101 @bug
  Scenario Outline: Test GL code for 1772 TPD Annual Periodic Fee
    Given I am logged into appian as "<user>" user
    #And I select notification with status "<statusFrom>" and update status to "<statusTo>"
    And I goto notifications page and update status of an existing notification to "<statusTo>"
    Then I expect the notification status should be "<statusTo>"
    When I login as "fin1" and generate an annual invoice
    Then I should receive an invoice email with heading "Annual Notification Invoices" from appian in next 2 min with correct price "" for the stored notification
    And The invoice should contain correct glcode "<glcode>" and other details
    And The invoices should be unique by invoice id
    And The invoices should all have unit price as "<price>"
    Examples:
      | user   | glcode | price | statusFrom | statusTo   |
      | super1 | 1772   | 60    | Unpaid     | Published  |
      | super2 | 1772   | 60    | Paid       | Successful |


#  @mcm-87 @mcm-37 @mcm-109 @ignore
#  Scenario: Verify number of TPD Annual Invoices should matched number of published notifications
#    Given I am logged into appian as "super1" user
#    When I go to the notifications page
#    When I store count of notifications by status "Successful"
#    When I login as "fin1" and generate an annual invoice
#    Then I receive an invoice email with heading "Annual Notification Invoices" from appian in next 2 min for "" notifications
#    And The number of invoices should match with count of "Successful" notifications


  @mcm-87 @mcm-37 @mcm-109
  Scenario: Verify number of TPD Annual Invoices should matched number of published or successful notifications
    Given I am logged into appian as "super1" user
    When I go to the notifications page
    When I count the number of notifications in "Published,Successful" status
    When I login as "fin1" and generate an annual invoice
    Then I receive an invoice email with heading "Annual Notification Invoices" from appian in next 2 min for "" notifications
    And The number of invoices should match with count of "Published,Successful" notifications


  @mcm-87
  Scenario: Verify TPD Annual Invoice are generated each time we click annual invoice
    Given I am logged into appian as "super1" user
    When I login as "fin1" and generate an annual invoice
    Then I receive an invoice email with heading "Annual Notification Invoices" from appian in next 2 min for "" notifications