package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.service.TaskService;

@Controller
@AllArgsConstructor
public class ActualController {

    private final TaskService taskService;

    @GetMapping("/todosActual")
    public String actual(Model model) {
        model.addAttribute("actualTasks", taskService.findActual());
        return "todosActual";
    }

}
