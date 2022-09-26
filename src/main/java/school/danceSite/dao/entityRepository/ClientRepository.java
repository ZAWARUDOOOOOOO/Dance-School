package school.danceSite.dao.entityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import school.danceSite.dao.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT c < m OR m < l FROM(SELECT COUNT(*) AS c, MAX(id) AS m, " +
            "(SELECT last_value FROM client_id_seq) AS l FROM client) AS t;", nativeQuery = true)
    Boolean missedIdExists();

    @Query(value = "SELECT MIN(id) FROM (SELECT GENERATE_SERIES(1, GREATEST((SELECT MAX(id)+1 FROM client), 1)) AS id) AS s\n" +
            "WHERE NOT EXISTS (SELECT id FROM client WHERE id = s.id);", nativeQuery = true)
    Long getFirstMissedId();

    @Query(value = "SELECT CASE WHEN :id <> 1 THEN SETVAL('client_id_seq', :id -1)" +
            "ELSE SETVAL('client_id_seq', 1, FALSE) END;", nativeQuery = true)
    Long setAutoIncrement(Long id);


    @Query(value = "SELECT SETVAL('client_id_seq', (SELECT MAX(id) FROM client));", nativeQuery = true)
    Long setMaxAutoIncrement();

    @Modifying//these 2 annotations are used to tell spring, that this query changes smth in DB
    @Transactional
    @Query(value = "UPDATE smth set",
            nativeQuery = true)
    void customUpdateMethod();

    Boolean existsByContactNumberOrEmail(String contactNumber, String email);

    @Query(value = "SELECT EXISTS(SELECT * FROM client WHERE id <> ?1 AND (contactNumber = ?2 OR email = ?3 ));", nativeQuery = true)
    Boolean areNewCredUnique(Long id, String contactNumber, String email);
}
