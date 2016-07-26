Feature: As a user I should be able to do an end to end processing of notification

  @wip
  Scenario: Perform an end to end processing of notification
    Given I am in appian page
    When I login as user "super1"
    #And I create a new notification
    Then I should see the notification "32351-16-64113" generated
    When I login as "fin1" and generate a standard invoice
    #Given I should be able to edit notification

