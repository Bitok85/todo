package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
@AllArgsConstructor
public class TaskDetailsController {

    private final TaskService taskService;

    @GetMapping("/taskToDone/{taskId}")
    public String taskToDone(@PathVariable("taskId") int id) {
        taskService.updateToDone(taskService.findById(id).get());
        return "redirect:/index";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String taskUpdate(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findById(id).get());
        return "taskUpdate";
    }

    @PostMapping("/taskUpdate")
    public String updateTask(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable ("taskId") int id) {
        taskService.delete(id);
        return "redirect:/index";
    }
}
