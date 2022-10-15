package ru.job4j.store;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {
    private static final Logger LOG = Logger.getLogger(TaskStore.class.getName());
    private final SessionFactory sf;

    public Task createTask(Task task) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("Task creation error", e);
        }
        return task;
    }

    public boolean update(Task task) {
        boolean rsl = false;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery(
                    "UPDATE Task SET name = :fName, descr = :fDescr WHERE id = :fId")
                    .setParameter("fName", task.getName())
                    .setParameter("fDescr", task.getDescr())
                    .setParameter("fId", task.getId())
                    .executeUpdate() > 0;
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("Task update error", e);
        }
        return rsl;
    }

    public Optional<Task> findById(int id) {
        Optional<Task> rsl = Optional.empty();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Task task = (Task) session.createQuery("FROM Task WHERE id = :fId")
                    .setParameter("fId", id)
                    .getSingleResult();
            rsl = Optional.of(task);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("Find task by id error", e);
        }
        return rsl;
    }

    public List<Task> findAll() {
        List<Task> rsl;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("FROM Task").list();
            session.getTransaction().commit();
            session.close();
            return rsl;
        } catch (HibernateException e) {
            LOG.error("FindAll tasks error", e);
        }
        return new ArrayList<>();
    }

    public List<Task> findDone() {
        List<Task> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery(
                    "FROM Task WHERE done = :fDone")
                    .setParameter("fDone", true)
                    .list();
            session.getTransaction();
            session.close();
        } catch (HibernateException e) {
            LOG.error("FindDone tasks error", e);
        }
        return rsl;
    }

    public List<Task> findActual() {
        List<Task> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery(
                            "FROM Task WHERE done = :fDone")
                    .setParameter("fDone", false)
                    .list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("HibernateException", e);
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = false;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery(
                    "DELETE Task WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("Delete task error", e);
        }
        return rsl;
    }

    public boolean updateToDone(Task task) {
        boolean rsl = false;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("UPDATE Task SET done = :fDone WHERE id = :fId")
                    .setParameter("fDone", true)
                    .setParameter("fId", task.getId())
                    .executeUpdate() > 0;
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("Update task to 'done' error", e);
        }
        return rsl;
    }
}

