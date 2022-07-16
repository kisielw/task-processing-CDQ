package pl.kisielw.taskprocessingCDQ.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kisielw.taskprocessingCDQ.model.Task;

import java.util.List;

@RestController
@Slf4j
public class TaskController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/api/tasks")
    public List<Task> getTasks() {
        throw new IllegalArgumentException("Not implemented yet");
    }
}
