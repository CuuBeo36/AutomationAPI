Feature: Full flow

  Scenario: Get email from API then login on App AndroidStudio
    Given Set up the API URL
    When Get email from API
    And Login on App via AndroidStudio
    Then Verify Login Fail

  Scenario: Get email from API then login on App BrowserStack
    Given Set up the API URL
    When Get email from API
    And Login on App via BrowserStack
    Then Verify Login Fail