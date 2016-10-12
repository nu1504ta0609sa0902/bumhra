@ignore
Feature: As a user I should be able to do an end to end invoice processing of notification

  @wip @testenv @e2e
  Scenario: Perform an end to end invoice processing of notification
    Given I am logged into appian as "super1" user
    And I create new notification
    Then I should see the stored notification
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "150" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "Paid"


  @wip @testenv @e2e
  Scenario Outline: Perform an end to end invoice processing of different types of notification
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type | <type> |
    Then I should see the stored notification
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min with correct price "<price>" for the stored notification
    When I send paid email response back to appian
    Then The notification status should update to "<status>"
    Examples:
      | user | type | price | status |
      | ipu1 | 1    | 150   | Paid   |


  @zipupload
  Scenario Outline: Check when ALLOWABLE substances are added the system will Pass the notification
    Given I am logged into appian as "<user>" user
    When I go to manage substance page
    And I add a substance "<substance>" which "<banned>" banned
    Then I should see the new substance in the manage substance page
    And I create new notification with following data
      | type       | <type> |
      | ingredient | stored |
    And I attach a toxicology report for "stored"
    When I login as "fin1" and generate a standard invoice
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "" notifications
    When I send paid email response back to appian
    Then I expect the notification status should be "Unpaid"
    Then I expect the notification status should be "<status>"
    Examples:
      | user | type | status     | substance | banned |
      | ipu1 | 1    | Successful | random    | is not |


  Scenario: Verify xml notification generation of minimal data from excel sheet
    #Given I am logged into appian as "super1" user
    Given I create new xml notification with following data set
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


  Scenario: Verify xml notification zip file generation of minimal data from excel sheet
    Given I create zip file with following data set
      #Field values are : excelkey or none
      | saveXMLOutputAs                 | random                                |
      #Submission Type
      | submissionType                  | 1                                     |
      #Submitter
      | submitter1                      | valid.submitter.2                     |
      #Product
      | product1                        | valid.product.3                       |
      #Product ingredient
      | ingredientAndToxicologyReports1 | valid.ingredient.4,valid.toxicology.2 |
      | ingredientAndToxicologyReports2 | valid.ingredient.1,valid.toxicology.2 |
      #| ingredientAndToxicologyReports3 | valid.ingredient.1                    |
      #Producct Emission
      | emission1                       | valid.emission.1                      |
      #| emission2                       | valid.emission.3                      |
      #Product Manufacturer
      | manufacturer1                   | valid.manufacturer.1                  |
      #| manufacturer2                   | valid.manufacturer.2                  |
      #Product Presentation
      | presentation1                   | valid.presentation.1                  |
      #| presentation2                   | valid.presentation.2                  |
      #Product Design
      | design                          | valid.design.1                        |
    And I am logged into appian as "rdt1" user
    And I upload the stored zip file to appian
    Then I should see the uploaded zip file notification with status set to "Uploaded"


  Scenario: Verify xml notification zip file generation of minimal data from excelsheet 2
    Given I create new zip file with following data table
      #These are keys which will load data from excel file
      | saveXMLOutputAs         | submissionType | submitter         | product         | ingredientAndToxicologyReportPairs                                             | listOfEmissions | listOfManufacturers  | listOfPresentations  | design         |
      #| verifyXMLGeneration.xml | 1              | valid.submitter.1 | valid.product.1 | valid.ingredient.2, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 : valid.ingredient.2 |                 | valid.manufacturer.2 | valid.presentation.2 | valid.design.1 |
      | verifyXMLGeneration.xml | 1              | valid.submitter.1 | valid.product.1 | valid.ingredient.1, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
    And I am logged into appian as "rdt1" user
    And I upload the stored zip file to appian
    Then I should see the uploaded zip file notification with status set to "Uploaded"


  Scenario Outline: Verify xml notification zip file generation of minimal data from excelsheet 3
    Given I create new zip file with following data table
      #These are keys which will load data from excel file
      | saveXMLOutputAs         | submissionType | submitter   | product   | ingredientAndToxicologyReportPairs                                             | listOfEmissions | listOfManufacturers   | listOfPresentations   | design   |
      #| verifyXMLGeneration.xml | 1              | valid.submitter.1 | valid.product.1 | valid.ingredient.2, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 : valid.ingredient.2 |                 | valid.manufacturer.2 | valid.presentation.2 | valid.design.1 |
      | verifyXMLGeneration.xml | <type>         | <submitter> | <product> | valid.ingredient.1, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 |                 | <listOfManufacturers> | <listOfPresentations> | <design> |
    And I am logged into appian as "rdt1" user
    And I upload the stored zip file to appian
#    Then I should see the uploaded zip file notification with status set to "Uploaded"
#    And I should see new task generated for the stored notification
    Examples:
      | type | submitter         | product         | ingredientAndToxicologyReportPairs | listOfEmissions | listOfManufacturers  | listOfPresentations  | design         |
      | 1    | valid.submitter.1 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.1 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.1 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.2 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.2 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.2 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.3 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.3 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.3 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.4 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.4 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.4 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.5 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.5 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.5 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |



  Scenario Outline: Verify xml notification zip file generation with end 2 end processing
    Given I create new zip file with following data table
      #These are keys which will load data from excel file
#      | saveXMLOutputAs         | submissionType | submitter         | product         | ingredientAndToxicologyReportPairs                                             | listOfEmissions | listOfManufacturers  | listOfPresentations  | design         |
#      | verifyXMLGeneration.xml | 1              | valid.submitter.1 | valid.product.1 | valid.ingredient.2, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 : valid.ingredient.2 |                 | valid.manufacturer.2 | valid.presentation.2 | valid.design.1 |
#      | verifyXMLGeneration.xml | 1              | valid.submitter.6 | valid.product.1 | valid.ingredient.1, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | saveXMLOutputAs         | submissionType | submitter   | product   | ingredientAndToxicologyReportPairs                                             | listOfEmissions | listOfManufacturers   | listOfPresentations   | design   |
      | verifyXMLGeneration.xml | <type>         | <submitter> | <product> | valid.ingredient.1, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 |                 | <listOfManufacturers> | <listOfPresentations> | <design> |
    And I am logged into appian as "rdt1" user
    And I upload the stored zip file to appian
    Then I should see the uploaded zip file notification with status set to "Uploaded"
    And I find the new task generated for the stored notification
    And I set TCA details for the notification
    #Then I should see the uploaded zip file notification with status set to "Ready for Invoicing"
    When I re login as user "super1"
    And I enter a submitter address to stored notification
    Then I should see the uploaded zip file notification with status set to "Ready for Invoicing"
    When I login as "fin1" and generate a standard invoice
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2.5 min for "" notifications
    When I send paid email response back to appian
    Then I expect the notification status should be "Unpaid"
    Then I expect the notification status should be "Successful"
  Examples:
      | type | submitter         | product         | ingredientAndToxicologyReportPairs | listOfEmissions | listOfManufacturers  | listOfPresentations  | design         |
      | 1    | valid.submitter.1 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.1 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.1 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.2 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.2 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.2 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.3 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.3 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.3 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.4 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.4 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.4 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.5 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 1    | valid.submitter.5 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
      | 3    | valid.submitter.5 | valid.product.1 |                                    |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
