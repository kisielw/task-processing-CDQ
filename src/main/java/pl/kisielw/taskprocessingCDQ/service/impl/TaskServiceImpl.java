package pl.kisielw.taskprocessingCDQ.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kisielw.taskprocessingCDQ.service.TaskService;
import pl.kisielw.taskprocessingCDQ.model.InputParams;
import pl.kisielw.taskprocessingCDQ.model.Task;
import pl.kisielw.taskprocessingCDQ.repository.InputParamsRepository;
import pl.kisielw.taskprocessingCDQ.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private InputParamsRepository inputParamsRepository;

    @Override
    public Task save(InputParams inputParams) {

        inputParamsRepository.save(inputParams);
        Double result = Math.pow(inputParams.getBase(), inputParams.getExponent());
        Task task = new Task();
        task.setInputParams(inputParams);

        taskRepository.save(task);
        task.setId(taskRepository.findByInputParams(inputParams).getId());

        Thread thread = new Thread(() ->{
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                //TODO obsłużyć, jeśli jest możliwość
                throw new RuntimeException(e);
            }
            task.setResult(result);
            task.setProgress("100%");
            task.setStatus("finished");
            taskRepository.save(task);
        });
        thread.start();
        return task;
    }


}
