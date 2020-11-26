package ru.sin.springjdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sin.springjdbc.model.Task;
import ru.sin.springjdbc.repository.TaskRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getListTasks() {
        return taskRepository.getListTasks();
    }

    public List<Task> getListTasks(int page, int pageSize) {
        return taskRepository.getPaginationListTasks(page, pageSize);
    }

}
