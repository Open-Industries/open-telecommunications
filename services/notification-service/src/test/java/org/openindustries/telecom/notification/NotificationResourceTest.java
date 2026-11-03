package org.openindustries.telecom.notification;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class NotificationResourceTest {

    @Test
    void dispatchNotification() {
        given().contentType("application/json")
                .body("{\"channel\":\"SMS\",\"recipient\":\"15551234567\",\"template\":\"BALANCE_LOW\"}")
                .when().post("/api/v1/notifications")
                .then().statusCode(202).body("status", is("SENT"));
    }
}
