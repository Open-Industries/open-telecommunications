package org.openindustries.telecom.functions.rating;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void ratesDataUsage() {
        given().contentType("application/json")
                .body("{\"subscriberId\":\"s-1\",\"serviceType\":\"DATA\",\"units\":250}")
                .when().post("/usageRating")
                .then().statusCode(200)
                .body("chargeMinor", is(500))
                .body("ratePerUnitMinor", is(2));
    }

    @Test
    void ratesSmsUsage() {
        given().contentType("application/json")
                .body("{\"subscriberId\":\"s-1\",\"serviceType\":\"SMS\",\"units\":10}")
                .when().post("/usageRating")
                .then().statusCode(200)
                .body("chargeMinor", is(50));
    }
}
