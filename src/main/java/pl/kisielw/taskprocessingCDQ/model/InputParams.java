package pl.kisielw.taskprocessingCDQ.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class InputParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long base;

    private Long exponent;

    @OneToOne
    private Task task;
}
