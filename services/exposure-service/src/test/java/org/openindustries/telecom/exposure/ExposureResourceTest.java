package org.openindustries.telecom.exposure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExposureResourceTest {

    @Test
    void createQodSession() {
        given().contentType("application/json")
                .body("{\"deviceMsisdn\":\"15551234567\",\"qosProfile\":\"LOW_LATENCY\",\"durationMinutes\":60}")
                .when().post("/api/v1/exposure/qod/sessions")
                .then().statusCode(201).body("status", is("ACTIVE"));
    }

    @Test
    void simSwapRiskLevels() {
        given().contentType("application/json")
                .body("{\"msisdn\":\"15551234567\",\"lastSwapDaysAgo\":2}")
                .when().post("/api/v1/exposure/sim-swap/check")
                .then().statusCode(200)
                .body("swappedRecently", is(true))
                .body("riskLevel", is("HIGH"));

        given().contentType("application/json")
                .body("{\"msisdn\":\"15551234567\",\"lastSwapDaysAgo\":90}")
                .when().post("/api/v1/exposure/sim-swap/check")
                .then().statusCode(200)
                .body("swappedRecently", is(false))
                .body("riskLevel", is("LOW"));
    }
}
