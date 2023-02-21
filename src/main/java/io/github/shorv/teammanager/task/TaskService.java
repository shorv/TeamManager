package io.github.shorv.teammanager.task;

import io.github.shorv.teammanager.PageableAndSortableService;
import io.github.shorv.teammanager.task.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends PageableAndSortableService<Task, TaskRepository> {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository repository, TaskRepository taskRepository) {
        super(repository);
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAll(Integer page, Integer size, String sortDirection, String sortField) {
        return super.getAll(page, size, sortDirection, sortField);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
    }

    public void addNewTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
