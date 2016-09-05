Feature: As a Finance user I should receive invoice email with correct data
  So that I am able to bill the right client


  @mcm-87 @mcm-91 @mcm-90
  Scenario Outline: Invoice spreadsheet should contain GL Code and other necessary invoice details
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email from appian in next 2 min with correct price "<price>" for the stored notification
    And The invoice should contain correct code "<glcode>" and other details
    Examples:
      | user | type | price | ingredient  | glcode |
      | rdt1 | 1    | 150   | SUPPLEMENT1 | 1770   |
#      | rdt1 | 2    | 80    | SUPPLEMENT2 | 1771   |
#      | rdt1 | 3    | 150   | SUPPLEMENT3 | 1770   |


  @mcm-90
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
    Then I should receive an invoice email from appian in next 2 min with correct price "" for the stored notification
    And The invoices should be unique by invoice id
    Examples:
      | user | type1 | ingredient1 | type2 | ingredient2 |
      | rdt1 | 1     | SUPPLEMENT1 | 2     | SUPPLEMENT2 |