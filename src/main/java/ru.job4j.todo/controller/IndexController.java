package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
@AllArgsConstructor
public class IndexController {

    private final TaskService taskService;

 @GetMapping("/index")
 public String index(Model model) {
     model.addAttribute("tasks", taskService.findAll());
     return "index";
 }

 @PostMapping("/addTask")
 public String addTaskButton() {
     return "redirect:/addTask";
 }
}
