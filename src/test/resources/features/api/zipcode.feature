
 @ui @zip

   Feature: USPS Zipcode look up UI and API validation
     Scenario: User should see same city for API and UI zipcode lookup

       Given User sends GET request to "http://api.zippopotam.us/us/{postal-code}" with "98133" zipcode
       Then city name should be "Seattle" in response
       And User searches for "98133" on USPS web
       Then city name should be "SEATTLE WA" in result