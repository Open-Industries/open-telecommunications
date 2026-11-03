package org.openindustries.telecom.notification;

import java.time.Instant;
import java.util.UUID;

/** A dispatched notification; channel delivery is an outbound adapter concern. */
public class Notification {
    public UUID id;
    public String channel;     // EMAIL | SMS | PUSH
    public String recipient;   // never logged (privacy, ISO/IEC 27002 A.5.34)
    public String template;    // e.g. USAGE_ALERT, SLICE_ACTIVATED, BALANCE_LOW
    public String status;      // QUEUED | SENT | FAILED
    public Instant createdAt;

    public static Notification create(String channel, String recipient, String template) {
        Notification n = new Notification();
        n.id = UUID.randomUUID();
        n.channel = channel;
        n.recipient = recipient;
        n.template = template;
        n.status = "QUEUED";
        n.createdAt = Instant.now();
        return n;
    }
}
