package ru.job4j.store;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserStore {

    private static final Logger LOG = Logger.getLogger(UserStore.class.getName());

    private final SessionFactory sf;

    public Optional<User> addUser(User user) {
        Optional<User> rsl = Optional.empty();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(user);
            rsl = Optional.of(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("User addition error", e);
        }
        return rsl;
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        Optional<User> rsl = Optional.empty();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = (Optional<User>) session.createQuery(
                    "FROM User WHERE login = :fLogin AND password = :fPassword")
                    .setParameter("fLogin", login)
                    .setParameter("fPassword", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("Find by login and password error", e);
        }
        return rsl;
    }
}
