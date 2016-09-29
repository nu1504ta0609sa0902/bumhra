Feature: As a reporter, I want to view a report of notification data
  So that I can quickly view an overview of the current workload and status of notifications in the system.

  @mcm-29
  Scenario: Check report displays total number of notifications for a day
    Given I am logged into appian as "super1" user
    When I go to notifications reports page
    Then I should see the "high chart"


  @mcm-29
  Scenario: Users should be able to show and hide notifications
    Given I am logged into appian as "super1" user
    When I go to notifications reports page
    And I click on "show" notifications
    Then I page should "show" notifications
    #And I should see "hide" notifications link
    When I click on "hide" notifications
    Then I should see "show" notifications link
