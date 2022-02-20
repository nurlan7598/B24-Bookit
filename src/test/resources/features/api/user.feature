@ui @rooms @db

  Feature: Verify room reservation functionality

    Scenario: Team load should be able to see the available room
      Given User logged in to BookIt app  as team lead role
      When User goes to room hunt page
      And User sets date for using room as:
      |date|February 21,2022|
      |from|9:00am          |
      |to  |9:30am          |
    Then User should see available rooms:
    And User logged in to BookIt api  as team lead role
    And User sends GET request to "/api/rooms/available" with:
      |year|2022|
      |month|2|
      |day  |21|
      |conference-type|SOLID|
      |cluster-name   |light-side|
      |timeline-id    |11237     |
    Then status code should be 200
    And available rooms in response should match UI results
    And  available rooms in DB should match UI and API results
