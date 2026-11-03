package org.openindustries.telecom.subscriber;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class SubscriberResourceTest {

    @Test
    void onboardMasksPermanentIdentifier() {
        String id = given().contentType("application/json")
                .body("{\"msisdn\":\"15551234567\",\"imsi\":\"310260123456789\",\"fullName\":\"Grace Hopper\",\"plan\":\"POSTPAID\",\"homeNetwork\":\"310260\"}")
                .when().post("/api/v1/subscribers")
                .then().statusCode(201)
                .body("imsiMasked", endsWith("6789"))
                .body("status", is("ACTIVE"))
                .extract().path("id");

        given().when().get("/api/v1/subscribers/" + id)
                .then().statusCode(200).body("msisdn", is("15551234567"));
    }

    @Test
    void suspendWithoutBody() {
        // Lifecycle transitions take no body and must not require a Content-Type (no 415).
        String id = given().contentType("application/json")
                .body("{\"msisdn\":\"15557654321\",\"imsi\":\"310260987654321\",\"fullName\":\"Alan Turing\",\"plan\":\"PREPAID\",\"homeNetwork\":\"310260\"}")
                .when().post("/api/v1/subscribers")
                .then().statusCode(201)
                .extract().path("id");

        given().when().post("/api/v1/subscribers/" + id + "/suspend")
                .then().statusCode(200).body("status", is("SUSPENDED"));
    }
}
