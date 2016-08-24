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


  Scenario: Verify xml notification generation
    #Given I am logged into appian as "super1" user
    Given I create new xml notification with following data
      #Submitter : random, usedata or none
      | submitter1                                    | random          |
      | submitterType                                 | MANUFACTURER    |
      | submissionType                                | 2               |
      | hasEnterer                                    | false           |
      | hasAffiliate                                  | false           |
      | hasParent                                     | true            |
      #Product
      | productId                                     | random          |
      | productType                                   | 5               |
      | weight                                        | 10              |
      | volume                                        | 10              |
      #Product Manufacturer
      | manufacturer1                                 | random          |
      #Product Presentation
      | presentation1                                 | random          |
      #Product Design
      | design1                                       | random          |
      #Product ingredient
      | ingredient1                                   | usedata          |
      | ingredient1ToxicologyStatus                   | 1               |
      #Product ingredient toxicology reports
      | ingredient1AddToxicologyCardioPulmonary       | false           |
      | ingredient1AddToxicologyCardioPulmonaryReport | default         |
      | ingredient1AddToxicologyCmr                   | false           |
      | ingredient1AddToxicologyCmrReport             | default         |
      | ingredient1AddToxicologyOther                 | false           |
      | ingredient1AddToxicologyOtherReport           | default         |
      #Producct Emission
      | emission1                                     | random          |
      | emission1Quantity                             | 10              |
      | emission1Unit                                 | ±1.1mg/100puffs |
      | emission1Name                                 | 2               |
      | emission1Attachement                          | true            |




  Scenario: Verify xml notification generation of minimal data
    #Given I am logged into appian as "super1" user
    Given I create new xml notification with following data
      #Submitter : random, usedata or none
      | submitter1                                    | random          |
      | submitterType                                 | MANUFACTURER    |
      | submissionType                                | 2               |
      #Product
      | productId                                     | random          |
      | productType                                   | 5               |
      | weight                                        | 10              |
      | volume                                        | 10              |
      #Product ingredient
      | ingredient1                                   | random          |
      | ingredient2                                   | random          |
      | ingredient3                                   | random          |
      | ingredient1ToxicologyStatus                   | 1               |
      #Producct Emission
      | emission1                                     | random          |
      | emission2                                     | random          |
      | emission3                                     | random          |
      #Product Manufacturer
      #| manufacturer1                                 | random          |
      #Product Presentation
      #| presentation1                                 | random          |
      #Product Design
      #| design1                                       | random          |


