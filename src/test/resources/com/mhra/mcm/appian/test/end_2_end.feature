Feature: As a user I should be able to do an end to end processing of notification

  @wip @testenv
  Scenario: Perform an end to end processing of notification
    Given I am logged into appian as "super1" user
    And I create new notification
    Then I should see the stored notification
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email in last 2 min with correct price "150" for the stored notification
    When I send paid email response back to appian
    Then The status should update to "Paid"



  @ignore
  Scenario: Check price for an existing notification
    Given I am logged into appian as "fin1" user
    And I have a notification "79894-16-47650" generated
    When I generate a standard invoice
    Then I should receive an invoice email in last 100 min with correct price "150" for the stored notification
    When I send paid email response back to appian
    Then The status should update to "Paid"


