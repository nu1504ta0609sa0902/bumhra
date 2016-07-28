Feature: As a user I should be able to do an end to end processing of notification

  @wip
  Scenario: Perform an end to end processing of notification
    Given I am logged into appian as "super1" user
    And I create a new notification
    Then I should see the stored notification
    When I login as "fin1" and generate a standard invoice
    Then I should receive an invoice email with correct price "150" for the stored notification


  @wip
  Scenario: Check price for an existing notification notification
    Given I am logged into appian as "fin1" user
    And I have a notification "14428-16-69418" generated
    When I generate a standard invoice
    Then I should receive an invoice email with correct price "150" for the stored notification


