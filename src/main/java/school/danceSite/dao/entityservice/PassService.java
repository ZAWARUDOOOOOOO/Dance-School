package school.danceSite.dao.entityservice;

import org.springframework.stereotype.Service;
import school.danceSite.dao.entity.Pass;
import school.danceSite.dao.entityrepository.PassRepository;

import java.util.List;

@Service
public class PassService {

    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public List<Pass> getPasses(){
        return passRepository.findAll();
    }

    public void update(Pass pass){
        passRepository.save(pass);
    }
}
