Feature: Currency rate retrive and filter

  Scenario: Currency Rate
    When I fetch the currency exchange rates from NBP page
    Then I display the rate for the currency with the code "USD"
    And I display the rate for the currency with the name "dolar amerykański"
    And I display currencies with rates above 5
    And I display currencies with rates below 3