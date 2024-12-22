package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyRatesStepDefinitions {

        private static final String BASE_URL = "http://api.nbp.pl/api/exchangerates/tables/A";
        private Response response;
        private JsonArray rates;

        @When("I fetch the currency exchange rates from NBP page")
        public void fetchCurrencyExchangeRatesFromNBPPage() {
            response = RestAssured.get(BASE_URL);
            int statusCode = response.statusCode();
            System.out.println("HTTP Status Code: " + statusCode);

            String json = response.body().asString();
            JsonArray tables = JsonParser.parseString(json).getAsJsonArray();
            rates = tables.get(0).getAsJsonObject().get("rates").getAsJsonArray();
        }

        @Then("I display the rate for the currency with the code {string}")
        public void displayRateByCode(String code) {
            rates.forEach(rate -> {
                JsonObject rateObj = rate.getAsJsonObject();
                if (rateObj.get("code").getAsString().equalsIgnoreCase(code)) {
                    System.out.println("Currency Code: " + code + " | Rate: " + rateObj.get("mid").getAsDouble());
                }
            });
        }

        @And("I display the rate for the currency with the name {string}")
        public void displayRateByName(String name) {
            rates.forEach(rate -> {
                JsonObject rateObj = rate.getAsJsonObject();
                if (rateObj.get("currency").getAsString().equalsIgnoreCase(name)) {
                    System.out.println("Currency Name: " + name + " | Rate: " + rateObj.get("mid").getAsDouble());
                }
            });
        }

        @And("I display currencies with rates above {double}")
        public void displayCurrenciesAbove(double threshold) {
            System.out.println("Currencies with rates above " + threshold + ":");
            rates.forEach(rate -> {
                JsonObject rateObj = rate.getAsJsonObject();
                double rateValue = rateObj.get("mid").getAsDouble();
                if (rateValue > threshold) {
                    System.out.println("Currency: " + rateObj.get("currency").getAsString() + " | Rate: " + rateValue);
                }
            });
        }

        @And("I display currencies with rates below {double}")
        public void displayCurrenciesBelow(double threshold) {
            System.out.println("Currencies with rates below " + threshold + ":");
            rates.forEach(rate -> {
                JsonObject rateObj = rate.getAsJsonObject();
                double rateValue = rateObj.get("mid").getAsDouble();
                if (rateValue < threshold) {
                    System.out.println("Currency: " + rateObj.get("currency").getAsString() + " | Rate: " + rateValue);
                }
            });
        }
}

