package school.danceSite.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PassStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clientid")
    private Long clientId;

    @OneToOne
    @JoinColumn(name = "passid")
    private Pass passid;

    @Column(name = "activesince")
    private LocalDate activeSince;

    @Column(name = "activetill")
    private LocalDate activeTill;

    @Column(name = "classesremaining")
    private Long classesRemaining;
}
