package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskStore store;

    public Task createTask(Task task) {
        return store.createTask(task);
    }

    public boolean update(Task task) {
        return store.update(task);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public List<Task> findAll() {
        return store.findAll();
    }

    public List<Task> findDone() {
        return store.findDone();
    }

    public List<Task> findActual() {
        return store.findActual();
    }

    public boolean updateToDone(Task task) {
        return store.updateToDone(task);
    }
}
