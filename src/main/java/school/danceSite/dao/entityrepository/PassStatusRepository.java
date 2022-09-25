package school.danceSite.dao.entityrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.danceSite.dao.entity.PassStatus;

@Repository
public interface PassStatusRepository extends JpaRepository<PassStatus, Long> {


}
