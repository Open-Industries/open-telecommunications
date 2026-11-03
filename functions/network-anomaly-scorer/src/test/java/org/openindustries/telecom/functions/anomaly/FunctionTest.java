package org.openindustries.telecom.functions.anomaly;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void healthyCellIsNormal() {
        given().contentType("application/json")
                .body("{\"cellId\":\"cell-1\",\"packetLossPct\":0.5,\"latencyMs\":20,\"throughputDropPct\":5}")
                .when().post("/networkAnomalyScorer")
                .then().statusCode(200).body("decision", is("NORMAL")).body("score", is(0));
    }

    @Test
    void stackedSignalsAreCritical() {
        given().contentType("application/json")
                .body("{\"cellId\":\"cell-2\",\"packetLossPct\":12.0,\"latencyMs\":200,\"throughputDropPct\":60}")
                .when().post("/networkAnomalyScorer")
                .then().statusCode(200).body("decision", is("CRITICAL")).body("score", is(100));
    }
}
