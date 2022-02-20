@db
  Feature: Add new team API and DB validation
    Scenario:  Post new team and verify in DB
      
      Given User logged in to BookIt api as a teacher role
      When User sends post request to "/api/teams/team" with following info:
      |campus-location|VA|
      |batch-number|20|
      |team-name|Kent WA|
      Then status code should be 201
      And Database should have same team information
      And User deletes previous created student team
