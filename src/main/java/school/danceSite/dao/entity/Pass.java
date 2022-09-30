package school.danceSite.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(name = "pass")
public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duration")
    @NotEmpty(message = "duration can't be empty")
    @Min(1)
    private Long duration;

    @Column(name = "classnum")
    @NotEmpty(message = "Number of classes can't be empty")
    @Min(4)
    private Long classNum;

    @Column(name = "passcost")
    @NotEmpty(message = "cost can't be empty")
    @Min(value = 2500, message = "too low cost has chosen")
    private Long passCost;
}
