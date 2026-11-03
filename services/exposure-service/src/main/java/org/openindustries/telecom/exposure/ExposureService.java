package org.openindustries.telecom.exposure;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class ExposureService {

    @Inject
    ExposureRepository repository;

    public QodSession createQod(CreateQodRequest request) {
        return repository.save(QodSession.create(
                request.deviceMsisdn(), request.qosProfile(), request.durationMinutes()));
    }

    public QodSession getQod(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "QoD session not found: " + id));
    }

    public SimSwapResult checkSimSwap(SimSwapCheckRequest request) {
        int days = request.lastSwapDaysAgo();
        String risk = days <= 3 ? "HIGH" : days <= 30 ? "MEDIUM" : "LOW";
        return new SimSwapResult(request.msisdn(), days <= 30, risk);
    }
}
