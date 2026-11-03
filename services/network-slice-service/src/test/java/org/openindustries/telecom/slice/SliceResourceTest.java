package org.openindustries.telecom.slice;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class SliceResourceTest {

    @Test
    void provisionDerivesSnssaiAndActivates() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"embb-default\",\"sst\":1,\"sliceType\":\"EMBB\",\"latencyTargetMs\":50,\"maxDevices\":100000}")
                .when().post("/api/v1/slices")
                .then().statusCode(201)
                .body("snssai", is("1-000000"))
                .body("status", is("PROVISIONED"))
                .extract().path("id");

        given().when().post("/api/v1/slices/" + id + "/activate")
                .then().statusCode(200).body("status", is("ACTIVE"));
    }
}
