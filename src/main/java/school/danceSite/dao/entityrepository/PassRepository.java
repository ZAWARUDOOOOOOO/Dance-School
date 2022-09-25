package school.danceSite.dao.entityrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.danceSite.dao.entity.Pass;

@Repository
public interface PassRepository extends JpaRepository<Pass, Long> {

    @Query(value = "update pass p set " +
            "p.duration = :passUpd.getDuration, " +
            "p.classnum = :passUpd.getClassNum, " +
            "p.passcost = :passUpd.getPassCost " +
            "where p.id = :id", nativeQuery = true)
    void setPassInfoById(Long id, Pass passUpd);
}
