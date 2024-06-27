Feature: Validate REST API with GET request

  Scenario: Validate status code of the GET request
    Given The REST API is up and running
    When I send a GET request to the API
    Then The status code of the response should be 200

  Scenario: Validate status code of the POST request
    Given The REST API is up and running
    When I send a POST request to the API
    Then The status code of the response should be 201

  Scenario Outline: Validate added data of the POST request
    Given The REST API is up and running
    When I send a POST request to the API "<title>" "<body>" "<userId>"
    Then The status code of the response should be 201
#    Then I send a GET request to the API for user id "<userId>"
#    And I compare GET response with POST request "<userId>" "<title>" "<body>"
    Examples:
      | title | body | userId |
      | a     | test | 10     |

  Scenario Outline: Validate updated data of the PUT request
    Given The REST API is up and running
    When I send a PUT request to the API for user id "<userId>" with "<title>" "<body>"
    Then The status code of the response should be 200
#    Then I send a GET request to the API for user id "<userId>"
#    And I compare GET response with POST request "<userId>" "<title>" "<body>"
    Examples:
      | title           | body            | userId |
      | API Update Test | API Update Test | 10     |

  Scenario Outline: Validate updated data of the DELETE request
    Given The REST API is up and running
    When I send a DELETE request to the API for user id "<userId>"
    And The status code of the response should be 200
    Examples:
      | userId |
      | 10     |