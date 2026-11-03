package org.openindustries.telecom.policy;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class PolicyResourceTest {

    @Test
    void activateThenEvaluate() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"urllc-premium\",\"sliceType\":\"URLLC\",\"maxBitrateMbps\":100,\"latencyBudgetMs\":10,\"priority\":1}")
                .when().post("/api/v1/policies")
                .then().statusCode(201).body("status", is("DRAFT"))
                .extract().path("id");

        given().when().post("/api/v1/policies/" + id + "/activate")
                .then().statusCode(200).body("status", is("ACTIVE"));

        given().contentType("application/json")
                .body("{\"sliceType\":\"URLLC\",\"requiredLatencyMs\":20,\"requiredBitrateMbps\":50}")
                .when().post("/api/v1/policies/evaluate")
                .then().statusCode(200).body("decision", is("ALLOWED"));
    }

    @Test
    void evaluateWithoutRuleIsDenied() {
        given().contentType("application/json")
                .body("{\"sliceType\":\"ISAC\",\"requiredLatencyMs\":20,\"requiredBitrateMbps\":50}")
                .when().post("/api/v1/policies/evaluate")
                .then().statusCode(200).body("decision", is("DENIED"));
    }
}
