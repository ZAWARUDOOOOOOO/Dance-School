package school.danceSite.dao.entityservice;

import org.springframework.stereotype.Service;
import school.danceSite.dao.entity.PassStatus;
import school.danceSite.dao.entityrepository.PassStatusRepository;

@Service
public class PassStatusService {

    private final PassStatusRepository passStatusRepository;

    public PassStatusService(PassStatusRepository passStatusRepository) {
        this.passStatusRepository = passStatusRepository;
    }

    public PassStatus createStatus(PassStatus passStatus){
        return passStatusRepository.save(passStatus);
    }
}
