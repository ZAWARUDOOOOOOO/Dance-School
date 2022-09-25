package school.danceSite.service;

import org.springframework.stereotype.Service;
import school.danceSite.dao.entity.PassStatus;
import school.danceSite.dao.entityservice.PassStatusService;

@Service
public class PaymentService {

    private final PassStatusService passStatusService;

    public PaymentService(PassStatusService passStatusService) {
        this.passStatusService = passStatusService;
    }

    public void pay(PassStatus passStatus) {
        // payment logic
        passStatusService.createStatus(passStatus);
    }
}
