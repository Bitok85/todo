package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.User;
import ru.job4j.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam (name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest request) {
        Optional<User> userDb = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("/regUser")
    public String regUser(Model model, @RequestParam (name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        model.addAttribute("timeZones", userService.timeZones());
        return "regUser";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user,
                               @RequestParam ("timeZone.id") String timeZone,
                               HttpServletRequest request) {
        user.setTimeZone(timeZone);
        Optional<User> userDb = userService.addUser(user);
        if (userDb.isEmpty()) {
            return "redirect:/regUser?fail=true";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", userDb.get());
        return "index";
    }
}
