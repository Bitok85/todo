package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.service.TaskService;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("user", defineUser(session));
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @GetMapping("/actualTasks")
    public String actual(Model model, HttpSession session) {
        model.addAttribute("user", defineUser(session));
        model.addAttribute("actualTasks", taskService.findActual());
        return "actualTasks";
    }

    @GetMapping("/doneTasks")
    public String done(Model model, HttpSession session) {
        model.addAttribute("user", defineUser(session));
        model.addAttribute("doneTasks", taskService.findDone());
        return "doneTasks";
    }

    @GetMapping("/addTask")
    public String addTaskForm() {
        return "addTask";
    }

    @PostMapping("/createTask")
    public String create(@ModelAttribute Task task, HttpSession session) {
        task.setUser(defineUser(session));
        taskService.createTask(task);
        return "redirect:/index";
    }

    @GetMapping("/formTaskDetails/{taskId}")
    public String formTaskDetails(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Задание не существует")));
        return "taskDetails";
    }

    @PostMapping("/addTask")
    public String addTaskButton() {
     return "redirect:/addTask";
 }

    @GetMapping("/taskToDone/{taskId}")
    public String taskToDone(@PathVariable("taskId") int id) {
        taskService.updateToDone(taskService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Задание не существует")));
        return "redirect:/index";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String taskUpdate(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Задание не существует")));
        return "taskUpdate";
    }

    @PostMapping("/taskUpdate")
    public String updateTask(@ModelAttribute Task task, HttpSession session) {
        task.setUser(defineUser(session));
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable ("taskId") int id) {
        taskService.delete(id);
        return "redirect:/index";
    }

    private User defineUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User("Гость");
        }
        return user;
    }
}
