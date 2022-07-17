package pl.kisielw.taskprocessingCDQ.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kisielw.taskprocessingCDQ.service.TaskService;
import pl.kisielw.taskprocessingCDQ.model.InputParams;
import pl.kisielw.taskprocessingCDQ.model.Task;
import pl.kisielw.taskprocessingCDQ.repository.InputParamsRepository;
import pl.kisielw.taskprocessingCDQ.repository.TaskRepository;
import pl.kisielw.taskprocessingCDQ.thread.ThreadById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private InputParamsRepository inputParamsRepository;

    final private Map<Integer, ThreadById> threads = new HashMap<>();

    @Override
    public Task save(InputParams inputParams) {
        inputParamsRepository.save(inputParams);

        Task task = getTask();

        Double result = Math.pow(inputParams.getBase(), inputParams.getExponent());

        ThreadById threadById = new ThreadById(task, result, taskRepository);
        threads.put(threadById.getId(), threadById);
        threadById.start();

        return task;
    }

    @Override
    public Task getById(Integer id) {
        if (!(taskRepository.existsById(id))) {
            return null;
        }

        ThreadById threadById = threads.get(id);

        if (threadById.getState() == Thread.State.TERMINATED) {
            return taskRepository.findById(id).orElseThrow();
        }

        if (threadById.getState() == Thread.State.TIMED_WAITING) {
            Task task = taskRepository.findById(id).orElseThrow();
            task.setStatus("running");
            task.setProgress(threadById.getProgress());
            return task;
        } else {
            return taskRepository.findById(id).orElseThrow();
        }
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();

        for (Task task : tasks) {
            if (!(task.getStatus().equals("finished"))) {
                task = getById(task.getId());
            }
        }

        return tasks;
    }

    private Task getTask() {
        Task task = new Task();
        Task savedTask = taskRepository.save(task);
        task.setId(savedTask.getId());
        return task;
    }


}
