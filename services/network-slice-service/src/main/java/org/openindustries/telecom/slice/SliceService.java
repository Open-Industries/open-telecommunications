package org.openindustries.telecom.slice;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SliceService {

    @Inject
    SliceRepository repository;

    public NetworkSlice create(CreateSliceRequest request) {
        return repository.save(NetworkSlice.create(request.name(), request.sst(), request.sd(),
                request.sliceType(), request.latencyTargetMs(), request.maxDevices()));
    }

    public NetworkSlice get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Network slice not found: " + id));
    }

    public List<NetworkSlice> list() {
        return repository.findAll();
    }

    public NetworkSlice activate(UUID id) {
        NetworkSlice slice = get(id);
        if (!"PROVISIONED".equals(slice.status)) {
            throw new DomainException(409, "Only a PROVISIONED slice can be activated");
        }
        slice.status = "ACTIVE";
        return repository.save(slice);
    }

    public NetworkSlice decommission(UUID id) {
        NetworkSlice slice = get(id);
        if ("DECOMMISSIONED".equals(slice.status)) {
            throw new DomainException(409, "Slice already decommissioned");
        }
        slice.status = "DECOMMISSIONED";
        return repository.save(slice);
    }
}
