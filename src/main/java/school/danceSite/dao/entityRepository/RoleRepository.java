package school.danceSite.dao.entityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.danceSite.dao.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);
}
