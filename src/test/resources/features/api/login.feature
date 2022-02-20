
  Feature: BookIt API login verification

    Scenario: verify login with valid credentials
      Given User logged in to BookIt api as a teacher role
      And User send get request to "/api/users/me"
      Then status code should be 200
      And content type "application/json"
      Then role is "teacher"
#


 Scenario: verify user details with ui and api
   Given User logged in to BookIt api as a teacher role
   And User send get request to "/api/users/me"
   And User logged in BookIt api as a teacher role
   And User is on self page
   Then User should see same info on UI and API


