package io.github.shorv.teammanager.task;

import io.github.shorv.teammanager.task.exception.TaskNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "size", required = false) Integer size,
                                               @RequestParam(value = "sort", required = false) String sortDir,
                                               @RequestParam(value = "orderBy", required = false) String orderBy) {
        return ResponseEntity.ok(taskService.getTasks(page, size, sortDir, orderBy));
    }

    @GetMapping("{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId) throws TaskNotFoundException{
        return taskService.getTaskById(taskId)
                .map(ResponseEntity::ok)
                .orElseThrow(TaskNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        taskService.addNewTask(task);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(task.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(task);
    }

    @DeleteMapping("{taskId}")
    public void deleteTaskById(@PathVariable("taskId") Long taskId) {
        taskService.deleteTaskById(taskId);
    }
}
