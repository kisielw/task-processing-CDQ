package pl.kisielw.taskprocessingCDQ.service;

import pl.kisielw.taskprocessingCDQ.model.InputParams;
import pl.kisielw.taskprocessingCDQ.model.Task;

public interface TaskService {
    Task save(InputParams inputParams);
}
