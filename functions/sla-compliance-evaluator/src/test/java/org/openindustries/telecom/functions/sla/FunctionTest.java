package org.openindustries.telecom.functions.sla;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void compliantSlice() {
        given().contentType("application/json")
                .body("{\"sliceId\":\"sl-1\",\"measuredLatencyMs\":8,\"targetLatencyMs\":10,\"measuredAvailabilityPct\":99.99,\"targetAvailabilityPct\":99.95}")
                .when().post("/slaComplianceEvaluator")
                .then().statusCode(200).body("compliant", is(true)).body("findings.size()", is(0));
    }

    @Test
    void latencyBreachIsReported() {
        given().contentType("application/json")
                .body("{\"sliceId\":\"sl-2\",\"measuredLatencyMs\":25,\"targetLatencyMs\":10,\"measuredAvailabilityPct\":99.99,\"targetAvailabilityPct\":99.95}")
                .when().post("/slaComplianceEvaluator")
                .then().statusCode(200).body("compliant", is(false)).body("findings.size()", is(1));
    }
}
