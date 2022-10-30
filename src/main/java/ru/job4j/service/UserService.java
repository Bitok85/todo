package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.repository.UserStore;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserStore store;

    public Optional<User> addUser(User user) {
        return store.addUser(user);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return store.findByLoginAndPassword(login, password);
    }

    public Map<String, String> timeZones() {
        return store.timeZones();
    }
}
