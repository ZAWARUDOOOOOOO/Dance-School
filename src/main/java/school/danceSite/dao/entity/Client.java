package school.danceSite.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clientname")
    @Size(min = 2, max = 30)
    @NotEmpty(message = "client name must be not empty")
    private String clientname;

    @Column(name = "contactnumber")//добавить валидацию по шаблонам номеров
    @Size(min = 10, max = 16, message = "too short or long telephone number")
    @NotEmpty(message = "client name must be not empty")
    private String contactNumber;

    @Column(name = "email")
    @NotEmpty(message = "email must be not empty")
    @Email(message = "email must be valid")
    private String email;

    @OneToOne
    @JoinColumn(name = "passStatus")
    private PassStatus passStatus;

    @Column(name = "lastappealdate")
    private LocalDate lastAppealDate;

    @Version
    private Integer version;
}
