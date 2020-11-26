package ru.sin.springjdbc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sin.springjdbc.model.Task;
import ru.sin.springjdbc.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> getListTasks(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "100000000") Integer pageSize) {
        return taskService.getListTasks(page, pageSize);
    }

}
