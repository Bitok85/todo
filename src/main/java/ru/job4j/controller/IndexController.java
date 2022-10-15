package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.service.TaskService;

@Controller
@AllArgsConstructor
public class IndexController {

    private final TaskService taskService;

 @GetMapping("/index")
 public String index(Model model) {
     model.addAttribute("tasks", taskService.findAll());
     return "index";
 }

 @GetMapping("/formTaskDetails/{taskId}")
 public String formTaskDetails(Model model, @PathVariable("taskId") int id) {
    model.addAttribute("task", taskService.findById(id).get());
    return "taskDetails";
 }


 @PostMapping("/addTask")
 public String addTaskButton() {
     return "redirect:/addTask";
 }
}
