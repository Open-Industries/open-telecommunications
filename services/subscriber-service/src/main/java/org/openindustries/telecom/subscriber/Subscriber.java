package org.openindustries.telecom.subscriber;

import java.time.Instant;
import java.util.UUID;

/**
 * Subscriber aggregate — the platform's subscription profile record, aligned with the
 * 3GPP UDM/UDR subscription data model (TS 23.501) and the 4G HSS before it.
 *
 * Privacy by design (ISO/IEC 27002 A.5.34): the permanent identifier (IMSI/SUPI) is
 * validated at onboarding and NEVER persisted or returned in clear — only a masked
 * form is kept. All identifiers in this reference implementation are synthetic.
 */
public class Subscriber {
    public UUID id;
    public String msisdn;        // synthetic phone number (public identifier)
    public String imsiMasked;    // masked permanent identifier, e.g. ***********1234
    public String fullName;
    public String plan;          // PREPAID | POSTPAID | ENTERPRISE
    public String homeNetwork;   // PLMN id (MCC+MNC), synthetic, e.g. 310260
    public String status;        // ACTIVE | SUSPENDED | CLOSED
    public Instant createdAt;

    public static Subscriber create(String msisdn, String imsi, String fullName, String plan, String homeNetwork) {
        Subscriber s = new Subscriber();
        s.id = UUID.randomUUID();
        s.msisdn = msisdn;
        s.imsiMasked = mask(imsi);
        s.fullName = fullName;
        s.plan = plan;
        s.homeNetwork = homeNetwork;
        s.status = "ACTIVE";
        s.createdAt = Instant.now();
        return s;
    }

    static String mask(String imsi) {
        if (imsi == null || imsi.length() < 4) {
            return "****";
        }
        return "*".repeat(imsi.length() - 4) + imsi.substring(imsi.length() - 4);
    }
}
