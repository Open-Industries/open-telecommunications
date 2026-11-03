package org.openindustries.telecom.session;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PDU session aggregate — aligned with the 5G SMF session concept (TS 23.501/23.502)
 * and the PDN connection of 4G/EPC. The user-plane function (UPF) itself is a network
 * element outside this IT platform; this service owns session state and lifecycle.
 */
public class PduSession {
    private static final AtomicInteger ADDRESS_POOL = new AtomicInteger(10);

    public UUID id;
    public UUID subscriberId;
    public String snssai;            // network slice identifier, e.g. 1-000001 (SST-SD)
    public String dataNetworkName;   // internet | ims | iot
    public String qosClass;          // EMBB | URLLC | MMTC
    public String ipAddress;         // synthetic address from the session pool
    public String status;            // ESTABLISHED | RELEASED
    public Instant startedAt;

    public static PduSession establish(UUID subscriberId, String snssai, String dnn, String qosClass) {
        PduSession s = new PduSession();
        s.id = UUID.randomUUID();
        s.subscriberId = subscriberId;
        s.snssai = snssai;
        s.dataNetworkName = dnn;
        s.qosClass = qosClass;
        s.ipAddress = "10.45.0." + ADDRESS_POOL.incrementAndGet();
        s.status = "ESTABLISHED";
        s.startedAt = Instant.now();
        return s;
    }
}
