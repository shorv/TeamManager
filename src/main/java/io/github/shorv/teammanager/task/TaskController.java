package io.github.shorv.teammanager.task;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

}
