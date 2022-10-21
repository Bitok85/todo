package ru.job4j.store;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore {

    private static final Logger LOG = Logger.getLogger(UserStore.class.getName());

    private final CrudRepository crudRepository;

    public Optional<User> addUser(User user) {
        try {
            crudRepository.run(session -> session.save(user));
            return Optional.ofNullable(user);
        } catch (HibernateException e) {
            LOG.error("Add user error", e);
        }
        return Optional.empty();
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "FROM User WHERE login = :fLogin AND password = :fPassword",
                User.class,
                Map.of("fLogin", login, "fPassword", password)
        );
    }

}
