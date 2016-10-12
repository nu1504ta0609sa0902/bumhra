Feature: As a ipumanager user I should be able to direct certain percentage of notifications for manual quality assurance
  So that we can make sure the system is working as expected


  @mcm-67
  Scenario Outline: Verify error message is displayed when value are more than 100 or less than 0 for quality assurance
    Given I am logged into appian as "<user>" user
    And I update qa percentage to "<qa_percentage>"
    Then I should see "<error_msg>" in the error message
    Examples:
      | user        | qa_percentage | error_msg             |
      | ipumanager1 |               | A value is required   |
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
      | ipumanager1 | 10            |


  @mcm-51
  Scenario Outline: The system will display visually in a read only format, which rules the notification passed and which rules it failed.
    Given I am logged into appian as "<user>" user
    When I go to notifications page and filter by status "<status>" and view a random notification
    And I view the assessment report
    Then The assessment report should show the values which were checked
    Examples:
      | user   | status            |
      | super1 | Successful        |
      | super1 | Failed            |
      | super1 | Quality Assurance |


  @mcm-51
  Scenario Outline: Users should be able to add comments to notifications
    Given I am logged into appian as "<user>" user
    When I go to notifications page and filter by status "<status>" and view a random notification
    When I add comment "<comment>" to selected notification
    Then I should see comment "<comment>" displayed in notification for user "<userName>"
    Examples:
      | user | status            | userName | comment |
      | ipu1 | Successful        | IPU 1    | random  |
      | ipu1 | Failed            | IPU 1    | random  |
      | ipu1 | Quality Assurance | IPU 1    | random  |


  # This depends on us having notifications in Quality Assurance status
  @mcm-51 @mcm-93
  Scenario Outline: Users should be able to accept or reject notifications in qa status
    Given I am logged into appian as "<user>" user
    When I view a task with heading containing "<heading>"
    And I should see option to accept or reject qa tasks
    Examples:
      | user | status            | heading                    | comment |
      | ipu1 | Quality Assurance | Review Notification for QA | random  |


  # This depends on us having notifications in Quality Assurance status, Heading = Review Notification for QA
  @mcm-93 @bug @wip
  Scenario Outline: QA Manager should be able to accept or reject existing tasks
    Given I am logged into appian as "<user>" user
    When I view a task with heading containing "<heading>"
    And I should see option to accept or reject qa tasks
    And I "<ipuAcceptOrReject>" the qa decision and add comment "<comment>"
    When I re login as user "ipumanager1"
    And I view a task for stored notification with heading containing "<qaManagerReviewLinks>"
    And I "<magagerAcceptOrReject>" the qa decision and add comment "<managerComment>"
    Then I should see the stored notification
    And The notification status should update to "<status>"
    Examples:
      | status     | user | heading                    | ipuAcceptOrReject | comment | qaManagerReviewLinks | magagerAcceptOrReject | managerComment                        |
      | Successful | ipu1 | Review Notification for QA | accept            |         | QA Manager Review    | accept                | QA manager accepted the rejected task |
#      | Failed           | ipu1 | Review Notification for QA | accept         |  | QA Manager Review    | reject                | QA manager rejected the rejected task                                      |
#      | Failed     | ipu1 | Review Notification for QA | reject         | Testing rejecting of qa decision | QA Manager Review    | accept                | QA manager accepted rejection, status should update to failed notification |
#      | Failed     | ipu1 | Review Notification for QA | reject         | Testing rejecting of qa decision | QA Manager Review    | reject                | QA manager rejected rejection, status should update to failed notification |


#  set QA PERCENTAGE to 100% so that a task is generated for IPU user
  @mcm-93 @bug @long @wip
  Scenario Outline: QA Manager should be able to accept or reject new tasks
#Create a new notification upto the point of paying the invoice
    Given I create new zip file with following data table
      #These are keys which will load data from excel file
      | saveXMLOutputAs         | submissionType | submitter         | product         | ingredientAndToxicologyReportPairs     | listOfEmissions | listOfManufacturers  | listOfPresentations  | design         |
      | verifyXMLGeneration.xml | 1              | valid.submitter.1 | valid.product.1 | valid.ingredient.1, valid.toxicology.1 |                 | valid.manufacturer.1 | valid.presentation.1 | valid.design.1 |
    And I am logged into appian as "rdt1" user
    And I upload the stored zip file to appian
    Then I should see the uploaded zip file notification with status set to "Uploaded"
    And I find the new task generated for the stored notification
    And I set TCA details for the notification
    When I re login as user "super1"
    And I enter a submitter address to stored notification
    When I login as "fin1" and generate a standard invoice
    Then I receive an invoice email with heading "Uninvoiced Notifications" from appian in next 2 min for "" notifications
    When I send paid email response back to appian
    Then I expect the notification status should be "Successful"
#Login as IPU user and accept reject a task
    Given I am logged into appian as "<user>" user
#    When I view a task with heading containing "<heading>"
    When I view a task for stored notification with heading containing "<qaManagerReviewLinks>"
    And I "<ipuAcceptOrReject>" the qa decision and add comment "<comment>"
#Login as QA Manager and accept or reject a task
    When I re login as user "ipumanager1"
    And I view a task for stored notification with heading containing "<qaManagerReviewLinks>"
    And I "<magagerAcceptOrReject>" the qa decision and add comment "<managerComment>"
    Then I should see the stored notification
    And The notification status should update to "<status>"
    Examples:
      | status     | user | heading                    | ipuAcceptOrReject | comment | qaManagerReviewLinks | magagerAcceptOrReject | managerComment                        |
      | Successful | ipu1 | Review Notification for QA | accept            |         | QA Manager Review    | accept                | QA manager accepted the rejected task |
#      | Failed           | ipu1 | Review Notification for QA | accept         |  | QA Manager Review    | reject                | QA manager rejected the rejected task                                      |
#      | Failed     | ipu1 | Review Notification for QA | reject         | Testing rejecting of qa decision | QA Manager Review    | accept                | QA manager accepted rejection, status should update to failed notification |
#      | Failed     | ipu1 | Review Notification for QA | reject         | Testing rejecting of qa decision | QA Manager Review    | reject                | QA manager rejected rejection, status should update to failed notification |
