package org.openindustries.telecom.session;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
class SessionResourceTest {

    @Test
    void establishAndRelease() {
        String id = given().contentType("application/json")
                .body("{\"subscriberId\":\"11111111-1111-1111-1111-111111111111\",\"snssai\":\"1-000001\",\"dataNetworkName\":\"internet\",\"qosClass\":\"EMBB\"}")
                .when().post("/api/v1/sessions")
                .then().statusCode(201)
                .body("status", is("ESTABLISHED"))
                .body("ipAddress", startsWith("10.45.0."))
                .body("id", notNullValue())
                .extract().path("id");

        given().when().post("/api/v1/sessions/" + id + "/release")
                .then().statusCode(200).body("status", is("RELEASED"));
    }
}
