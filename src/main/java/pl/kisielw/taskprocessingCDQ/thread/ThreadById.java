package pl.kisielw.taskprocessingCDQ.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pl.kisielw.taskprocessingCDQ.model.Task;
import pl.kisielw.taskprocessingCDQ.repository.TaskRepository;

@Data
@Slf4j
public class ThreadById {

    private Task task;

    private Double result;

    private TaskRepository taskRepository;

    private Long startTaskTime;

    final private static Long executeTaskThreadSleep = 20000L;

    public ThreadById(Task task, Double result, TaskRepository taskRepository) {
        this.task = task;
        this.result = result;
        this.taskRepository = taskRepository;
    }

    private Thread thread = new Thread(() -> {
        startTimeRunningTask();

        try {
            Thread.sleep(executeTaskThreadSleep);
        } catch (InterruptedException e) {
            log.warn("New task with id: " + task.getId() + " didn't execute");
        }

        fillFieldsInTask();
    });

    private void startTimeRunningTask() {
        startTaskTime = System.nanoTime();
    }

    private Long getRunningTaskTime() {
        return System.nanoTime() - startTaskTime;
    }

    private void fillFieldsInTask() {
        task.setResult(result);
        task.setProgress("100%");
        task.setStatus("finished");
        taskRepository.save(task);
    }

    public String getProgress() {
        Long progress = (100 * getRunningTaskTime()) / (1000000 * executeTaskThreadSleep);
        return progress + "%";
    }

    public Integer getId() {
        return task.getId();
    }

    public Thread.State getState() {
        return thread.getState();
    }

    public void start() {
        thread.start();
    }

}
