package org.openindustries.telecom.session;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SessionService {

    @Inject
    SessionRepository repository;

    public PduSession establish(CreateSessionRequest request) {
        return repository.save(PduSession.establish(request.subscriberId(),
                request.snssai(), request.dataNetworkName(), request.qosClass()));
    }

    public PduSession get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "PDU session not found: " + id));
    }

    public List<PduSession> bySubscriber(UUID subscriberId) {
        return repository.findBySubscriber(subscriberId);
    }

    public PduSession release(UUID id) {
        PduSession session = get(id);
        if ("RELEASED".equals(session.status)) {
            throw new DomainException(409, "Session already released");
        }
        session.status = "RELEASED";
        return repository.save(session);
    }
}
