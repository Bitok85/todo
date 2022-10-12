package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class AddTaskController {

    private final TaskService taskService;

    @GetMapping("/addTask")
    public String addTaskForm() {
        return "addTask";
    }

    @PostMapping("/createTask")
    public String create(@RequestParam("name") String name,
                         @RequestParam("descr") String descr) {
        Task task = new Task(name, descr);
        taskService.createTask(task);
        return "redirect:/index";
    }
}
