package ru.job4j.utils;

import org.springframework.ui.Model;
import ru.job4j.model.User;

import javax.servlet.http.HttpSession;

public class UserCheck {

    public static User defineUser(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }
}
