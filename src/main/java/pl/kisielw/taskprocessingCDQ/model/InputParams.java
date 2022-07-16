package pl.kisielw.taskprocessingCDQ.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class InputParams {

    @Id
    @GeneratedValue
    private Integer id;

    private Long base;

    private Long exponent;
}
