package org.openindustries.telecom.subscriber;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SubscriberService {

    @Inject
    SubscriberRepository repository;

    public Subscriber onboard(CreateSubscriberRequest request) {
        return repository.save(Subscriber.create(request.msisdn(), request.imsi(),
                request.fullName(), request.plan(), request.homeNetwork()));
    }

    public Subscriber get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Subscriber not found: " + id));
    }

    public List<Subscriber> list() {
        return repository.findAll();
    }

    public Subscriber suspend(UUID id) {
        return transition(id, "SUSPENDED");
    }

    public Subscriber reactivate(UUID id) {
        return transition(id, "ACTIVE");
    }

    private Subscriber transition(UUID id, String to) {
        Subscriber subscriber = get(id);
        if ("CLOSED".equals(subscriber.status)) {
            throw new DomainException(409, "A closed subscription is terminal");
        }
        subscriber.status = to;
        return repository.save(subscriber);
    }
}
