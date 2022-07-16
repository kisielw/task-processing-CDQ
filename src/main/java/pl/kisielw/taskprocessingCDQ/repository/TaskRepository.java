package pl.kisielw.taskprocessingCDQ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kisielw.taskprocessingCDQ.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
