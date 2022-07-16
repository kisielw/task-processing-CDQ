package pl.kisielw.taskprocessingCDQ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kisielw.taskprocessingCDQ.model.InputParams;

import javax.persistence.criteria.CriteriaBuilder;

public interface InputParamsRepository extends JpaRepository<InputParams, Integer> {
}
