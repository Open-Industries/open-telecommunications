package org.openindustries.telecom.charging;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ChargingResourceTest {

    @Test
    void usageDeductsFromBalance() {
        String id = given().contentType("application/json")
                .body("{\"subscriberId\":\"11111111-1111-1111-1111-111111111111\",\"currency\":\"USD\",\"initialBalanceMinor\":1000}")
                .when().post("/api/v1/charging/accounts")
                .then().statusCode(201).body("balanceMinor", is(1000))
                .extract().path("id");

        // 100 MB of DATA at 2 minor units/MB = 200 -> balance 800.
        given().contentType("application/json")
                .body("{\"serviceType\":\"DATA\",\"units\":100}")
                .when().post("/api/v1/charging/accounts/" + id + "/usage")
                .then().statusCode(200)
                .body("chargeMinor", is(200))
                .body("balanceAfterMinor", is(800));
    }

    @Test
    void usageBeyondBalanceIsRejected() {
        String id = given().contentType("application/json")
                .body("{\"subscriberId\":\"22222222-2222-2222-2222-222222222222\",\"currency\":\"USD\",\"initialBalanceMinor\":100}")
                .when().post("/api/v1/charging/accounts")
                .then().statusCode(201)
                .extract().path("id");

        // 100 minutes of VOICE at 15 minor units/min = 1500 > 100 -> 422, nothing charged.
        given().contentType("application/json")
                .body("{\"serviceType\":\"VOICE\",\"units\":100}")
                .when().post("/api/v1/charging/accounts/" + id + "/usage")
                .then().statusCode(422).body("status", is(422));

        given().when().get("/api/v1/charging/accounts/" + id)
                .then().statusCode(200).body("balanceMinor", is(100));
    }
}
