package org.openindustries.telecom.slice;

import java.util.UUID;

/**
 * Network slice aggregate — a logical network instance identified by its S-NSSAI
 * (SST + optional SD), per 3GPP TS 23.501 and slice management per TS 28.531.
 * Slice types cover the 5G service families (eMBB, URLLC, mMTC) and the IMT-2030
 * integrated sensing and communication scenario (ISAC) as a forward-looking value.
 */
public class NetworkSlice {
    public UUID id;
    public String name;
    public int sst;               // Slice/Service Type, 1..255
    public String sd;             // Slice Differentiator (24-bit hex) or null
    public String snssai;         // derived: "<sst>-<sd or 000000>"
    public String sliceType;      // EMBB | URLLC | MMTC | ISAC
    public int latencyTargetMs;
    public long maxDevices;
    public String status;         // PROVISIONED | ACTIVE | DECOMMISSIONED

    public static NetworkSlice create(String name, int sst, String sd, String sliceType, int latencyTargetMs, long maxDevices) {
        NetworkSlice s = new NetworkSlice();
        s.id = UUID.randomUUID();
        s.name = name;
        s.sst = sst;
        s.sd = sd;
        s.snssai = sst + "-" + (sd == null || sd.isBlank() ? "000000" : sd);
        s.sliceType = sliceType;
        s.latencyTargetMs = latencyTargetMs;
        s.maxDevices = maxDevices;
        s.status = "PROVISIONED";
        return s;
    }
}
