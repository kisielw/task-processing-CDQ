package pl.kisielw.taskprocessingCDQ.service;

import pl.kisielw.taskprocessingCDQ.model.InputParams;
import pl.kisielw.taskprocessingCDQ.model.Task;

import java.util.List;

public interface TaskService {
    Task save(InputParams inputParams);

    Task getById(Integer id);

    List<Task> getAll();
}
