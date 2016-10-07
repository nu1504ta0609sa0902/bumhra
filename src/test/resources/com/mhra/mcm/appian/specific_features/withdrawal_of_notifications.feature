Feature: As a Finance user, I want to be emailed a consolidated spreadsheet when notifications are withdrawn,
  so that I can end the finance processing for the notification.


  @mcm-92 @wip
  Scenario Outline: Withdrawal of Invoice email should be generated
    Given I am logged into appian as "<user>" user
    When I create new notification with following data
      | type       | <type>       |
      | ingredient | <ingredient> |
    And I attach a toxicology report for "<ingredient>"
    Then I should see the stored notification with status set to "<status>"
    And I update the stored notification to type "4" withdrawal
    When I login as "fin1" and generate a withdrawal invoice
    Then I receive an withdrawal email with heading "Withdrawn Notifications" from appian in next 2 min for "" notifications
    And The invoices should contains the stored ecid
    Examples:
      | user   | type | price | ingredient  | glcode | status              |
      | super1 | 1    | 150   | SUPPLEMENT1 | 1770   | Ready for Invoicing |
      | super1 | 2    | 80    | SUPPLEMENT2 | 1771   | Ready for Invoicing |
      | super1 | 3    | 150   | SUPPLEMENT3 | 1770   | Ready for Invoicing |


  @mcm-92 @wip
  Scenario: Manually withdraw notification and verify Withdrawal of Invoice email should be generated
    Given I am logged into appian as "super1" user
    And I goto notifications page and update status of an existing notification to "Withdrawn"
#    And Set a new ecid for stored notification
    When I login as "fin1" and generate a withdrawal invoice
    Then I receive an withdrawal email with heading "Withdrawn Notifications" from appian in next 2 min for "" notifications
    And The invoices should contains the stored ecid

  @mcm-53
  Scenario: Verify end to end withdrawal of notifications process is working
    Given I create new zip file with following data table
      #These are keys which will load data from excel file : is the separator
      | saveXMLOutputAs            | submissionType | submitter         | product         | ingredientAndToxicologyReportPairs                                             | listOfEmissions | listOfManufacturers  | listOfPresentations  | design         |
      | withdrawalNotification.xml | 1              | valid.submitter.6 | valid.product.1 | valid.ingredient.1, valid.toxicology.1 : valid.ingredient.2,valid.toxicology.1 |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
    And I am logged into appian as "rdt1" user
    And I upload the stored zip file to appian
    Then I should see the uploaded zip file notification with status set to "Uploaded"
    And I find the new task generated for the stored notification
    And I set TCA details for the notification
    Then I should see the uploaded zip file notification with status set to "Ready for Invoicing"
    When I re login as user "super1"
    And I enter a submitter address to stored notification
    And I update the stored notification to type "4" withdrawal
    And I login as "fin1" and generate a withdrawal invoice
    Then I receive an withdrawal email with heading "Withdrawn Notifications" from appian in next 2 min for "" notifications
    And The invoices should contains the stored ecid