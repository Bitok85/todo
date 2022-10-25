package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.service.CategoryService;
import ru.job4j.service.PriorityService;
import ru.job4j.service.TaskService;
import ru.job4j.utils.UserCheck;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("user", UserCheck.defineUser(session));
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @GetMapping("/actualTasks")
    public String actual(Model model, HttpSession session) {
        model.addAttribute("user", UserCheck.defineUser(session));
        model.addAttribute("actualTasks", taskService.findActual());
        return "actualTasks";
    }

    @GetMapping("/doneTasks")
    public String done(Model model, HttpSession session) {
        model.addAttribute("user", UserCheck.defineUser(session));
        model.addAttribute("doneTasks", taskService.findDone());
        return "doneTasks";
    }

    @GetMapping("/addTask")
    public String addTaskForm(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("priorities", priorityService.findAll());
        return "addTask";
    }

    @PostMapping("/createTask")
    public String create(@ModelAttribute Task task,
                         @RequestParam("priority.id") int prId,
                         @RequestParam(value = "taskCategories", required = false) List<Integer> taskCategories,
                         HttpSession session) {
        task.setUser(UserCheck.defineUser(session));
        task.setPriority(priorityService.findById(prId)
                .orElseThrow(() -> new NoSuchElementException("Приоритет не существует")));
        task.setCategories(
                taskCategories.stream()
                        .map(catId -> categoryService.findById(catId)
                                .orElseThrow(() -> new NoSuchElementException("Категории не существует")))
                        .collect(Collectors.toSet()));
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
        model.addAttribute("priorities", priorityService.findAll());
        return "taskUpdate";
    }

    @PostMapping("/taskUpdate")
    public String updateTask(@ModelAttribute Task task, @RequestParam("priority.id") int prId, HttpSession session) {
        task.setUser(UserCheck.defineUser(session));
        task.setPriority(priorityService.findById(prId)
                .orElseThrow(() -> new NoSuchElementException("Приоритет не существует")));
        taskService.update(task);
        return "redirect:/index";
    }

    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable ("taskId") int id) {
        taskService.delete(id);
        return "redirect:/index";
    }
}
