package pl.kisielw.taskprocessingCDQ.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class InputParams {

    @Id
    @GeneratedValue
    private Integer id;

    private Long base;

    private Long exponent;

    @OneToOne
    private Task task;
}
