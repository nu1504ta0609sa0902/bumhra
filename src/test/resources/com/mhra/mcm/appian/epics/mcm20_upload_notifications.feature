Feature:Appian system will provide the ability for an RDT user to upload the notification XML file and related attachments manually.


  @mcm-20
  Scenario Outline: Upload different types of notification
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type | <type> |
    Then I should see the stored notification with status set to "<initialStatus>"
    Examples:
      | user | type | initialStatus       |
      | ipu1 | 2    | Ready for Invoicing |
      | ipu1 | 1    | Ready for Invoicing |


  @mcm-20
  Scenario Outline: Upload different types of notification without TCA number
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type      | <type> |
      | tcaNumber |        |
    Then I should see the stored notification with status set to "<initialStatus>"
    Examples:
      | user | type | initialStatus |
      | ipu1 | 1    | Uploaded      |
      | ipu1 | 2    | Uploaded      |



  @mcm-20
  Scenario Outline: Upload different types of notification with zip file
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type | <type> |
    Then I should see the stored notification with status set to "<initialStatus>"
    Examples:
      | user | type | initialStatus       |
      | ipu1 | 2    | Ready for Invoicing |
      | ipu1 | 1    | Ready for Invoicing |


  @mcm-20
  Scenario Outline: Upload different types of notification with a zip file without TCA number
    Given I am logged into appian as "<user>" user
    And I create new notification with following data
      | type      | <type> |
      | tcaNumber |        |
    Then I should see the stored notification with status set to "<initialStatus>"
    Examples:
      | user | type | initialStatus |
      | ipu1 | 1    | Uploaded      |
      | ipu1 | 2    | Uploaded      |


  Scenario: Verify xml notification zip file generation with end 2 end processing
    Given I create new zip file with following data table
      #These are keys which will load data from excel file
      | saveXMLOutputAs         | submissionType | submitter         | product         | ingredientAndToxicologyReportPairs                                             | listOfEmissions | listOfManufacturers  | listOfPresentations  | design         |
      #| verifyXMLGeneration.xml | 1              | valid.submitter.1 | valid.product.1 | valid.ingredient.2, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 : valid.ingredient.2 |                 | valid.manufacturer.2 | valid.presentation.2 | valid.design.1 |
      | verifyXMLGeneration.xml | 1              | valid.submitter.6 | valid.product.1 | valid.ingredient.1, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
    And I am logged into appian as "rdt1" user
    And I upload the stored zip file to appian
    Then I should see the uploaded zip file notification with status set to "Uploaded"