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

    private Long runningTime;

    final private static Long counterTimeSleep = 1000L;

    final private static Long executeTaskThreadSleep = 20000L;

    final private static Long ratioOfSleepTimes = executeTaskThreadSleep / counterTimeSleep;

    public ThreadById(Task task, Double result, TaskRepository taskRepository) {
        this.task = task;
        this.result = result;
        this.taskRepository = taskRepository;
    }

    //TODO poszukać dokładniejszego sposobu na zliczanie czasu
    private Thread counterTimeThread = new Thread(() -> {
        runningTime = 0L;

        while (true) {
            try {
                Thread.sleep(counterTimeSleep);
            } catch (InterruptedException exc) {
                log.info("Task: " + task + " is executed");
                return;
            }
            runningTime++;
        }
    });

    private Thread thread = new Thread(() -> {
        counterTimeThread.start();

        try {
            Thread.sleep(executeTaskThreadSleep);
        } catch (InterruptedException e) {
            //TODO obsłużyć, jeśli jest możliwość
            throw new RuntimeException(e);
        }

        fillFieldsInTask();
        counterTimeThread.interrupt();
    });

    private void fillFieldsInTask() {
        task.setResult(result);
        task.setProgress("100%");
        task.setStatus("finished");
        taskRepository.save(task);
    }

    public String getProgress() {
        Long progress = 100 * this.runningTime / (ratioOfSleepTimes);
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
