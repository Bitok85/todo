package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.service.TaskService;

@Controller
@AllArgsConstructor
public class DoneController {

    private final TaskService taskService;

    @GetMapping("/todosDone")
    public String done(Model model) {
        model.addAttribute("doneTasks", taskService.findDone());
        return "todosDone";
    }
}
