Feature: As a user I should be able to do an end to end invoice processing of notification

  @wip @testenv @e2e
  Scenario: Perform an end to end invoice processing of notification
    Given I am logged into appian as "super1" user
    And I create new notification
    Then I should see the stored notification
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email from appian in next 2 min with correct price "150" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "Paid"


  @wip @testenv @e2e
  Scenario Outline: Perform an end to end invoice processing of different types of notification
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type | <type> |
    Then I should see the stored notification
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email from appian in next 2 min with correct price "<price>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<status>"
    Examples:
      | user | type | price | status |
      | ipu1 | 1    | 150   | Paid   |


  Scenario: Verify xml notification generation of minimal data from excel sheet
    #Given I am logged into appian as "super1" user
    Given I create new xml notification with following data
      #Field values are : excelkey or none
      | saveXMLOutputAs                 | random                                |
      #Submission Type
      | submissionType                  | 1                                     |
      #Submitter
      | submitter1                      | valid.submitter.1                     |
      #Product
      | product1                        | valid.product.1                       |
      #Product ingredient
      | ingredientAndToxicologyReports1 | valid.ingredient.2,valid.toxicology.2 |
      | ingredientAndToxicologyReports2 | valid.ingredient.1,valid.toxicology.2 |
      | ingredientAndToxicologyReports3 | valid.ingredient.1                    |
      #Producct Emission
      | emission1                       | valid.emission.1                      |
      | emission2                       | valid.emission.1                      |
      #Product Manufacturer
      | manufacturer1                   | valid.manufacturer.1                  |
      | manufacturer2                   | valid.manufacturer.1                  |
      #Product Presentation
      | presentation1                   | valid.presentation.1                  |
      | presentation2                   | valid.presentation.1                  |
      #Product Design
      | design                          | valid.design.1                        |


  Scenario: Verify xml notification generation of minimal data from excelsheet 2
    #Given I am logged into appian as "super1" user
    Given I create new xml notification with following data table
      #These are keys which will load data from excel file
      | saveXMLOutputAs         | submissionType | submitter         | product         | ingredientAndToxicologyReportPairs                                                                 | listOfEmissions | listOfManufacturers  | listOfPresentations  | design         |
      | verifyXMLGeneration.xml | 1              | valid.submitter.1 | valid.product.1 | valid.ingredient.1,valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 : valid.ingredient.2 |                 | valid.manufacturer.2 | valid.presentation.2 | valid.design.1 |


