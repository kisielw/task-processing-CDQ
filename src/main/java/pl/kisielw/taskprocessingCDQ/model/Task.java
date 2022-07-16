package pl.kisielw.taskprocessingCDQ.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Builder
    public Task(String status, String progress, Double result) {
        this.status = status;
        this.progress = progress;
        this.result = result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    private String progress;

    private Double result;

    @OneToOne
    private InputParams inputParams;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", progress='" + progress + '\'' +
                ", result=" + result +
                '}';
    }
}
