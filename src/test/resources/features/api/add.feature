Feature: Adding new student to api and deleting
  Scenario: adding a new student with certain credentials and verify status code is 201
    Given User logged in to BookIt api as a teacher role
    When User sends post request to "/api/students/student" with following info:
      |first-name|Nurlan|
      |last-name|Nolan|
      |email|nurlan@gmail.com|
      |password|abc123|
      |role|student-team-leader|
      |campus-location|VA|
      |batch-number|7|
      |team-name|Nukes|
    Then status code should be 201
    And User deletes previous created student "/api/students/"