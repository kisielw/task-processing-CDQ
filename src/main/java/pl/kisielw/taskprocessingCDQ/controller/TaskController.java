package pl.kisielw.taskprocessingCDQ.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kisielw.taskprocessingCDQ.service.TaskService;
import pl.kisielw.taskprocessingCDQ.model.InputParams;
import pl.kisielw.taskprocessingCDQ.model.Task;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/tasks")
    public Task createTask(@RequestBody InputParams inputParams) {
        return taskService.save(inputParams);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/tasks/{id}")
    public Task getTaskById(@PathVariable Integer id) {
        return taskService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/tasks")
    public List<Task> getTasks() {
        return taskService.getAll();
    }
}
