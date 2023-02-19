package io.github.shorv.teammanager.task;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    private List<Task> getPaginatedTasks(int page, int size) {
        return taskRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    private List<Task> getSortedTasks(String sortDirection, String field) {
        return taskRepository.findAll(Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field));
    }

    private List<Task> getSortedAndPaginatedTasks(int page, int size, String sortDirection, String field) {
        return taskRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field))).getContent();
    }

    public List<Task> getTasks(Integer page, Integer size, String sortDirection, String sortField) {
        boolean pageable = !(page == null || size == null);
        boolean sortable = !(sortDirection == null || sortField == null);

        if (!pageable) {
            if (!sortable) {
                return getAllTasks();
            }

            return getSortedTasks(sortDirection, sortField);
        }

        if (!sortable) {
            return getPaginatedTasks(page, size);
        }


        return getSortedAndPaginatedTasks(page, size, sortDirection, sortField);
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public void addNewTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
