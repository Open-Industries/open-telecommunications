package org.openindustries.telecom.exposure;

import java.time.Instant;
import java.util.UUID;

/**
 * Quality-on-Demand session — modeled on the GSMA Open Gateway / CAMARA QoD API:
 * an application requests a temporary QoS profile for a device. In production this is
 * realized through the NEF (TS 23.501) towards the PCF/SMF; here the session record is
 * the exposure-layer contract.
 */
public class QodSession {
    public UUID id;
    public String deviceMsisdn;     // synthetic
    public String qosProfile;       // LOW_LATENCY | HIGH_THROUGHPUT | DEFAULT
    public int durationMinutes;
    public String status;           // ACTIVE | EXPIRED
    public Instant createdAt;

    public static QodSession create(String deviceMsisdn, String qosProfile, int durationMinutes) {
        QodSession s = new QodSession();
        s.id = UUID.randomUUID();
        s.deviceMsisdn = deviceMsisdn;
        s.qosProfile = qosProfile;
        s.durationMinutes = durationMinutes;
        s.status = "ACTIVE";
        s.createdAt = Instant.now();
        return s;
    }
}
