package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {
    private static final String FIND_ALL
            = "SELECT distinct t FROM Task t JOIN FETCH t.priority JOIN FETCH t.categories";
    private static final String FIND_BY_ID
            = "SELECT DISTINCT t FROM Task t JOIN FETCH t.priority JOIN FETCH t.categories WHERE t.id = :fId";
    private static final String SELECT_DONE_COLUMN
            = "SELECT DISTINCT t FROM Task t JOIN FETCH t.priority JOIN FETCH t.categories WHERE t.done = :fDone";
    private static final String UPDATE_TO_DONE = "UPDATE Task SET done = :fDone WHERE id = :fId";
    private static final String DELETE = "DELETE Task WHERE id = :fId";
    private static final Logger LOG = Logger.getLogger(TaskStore.class.getName());

    private final CrudRepository crudRepository;
    public Task createTask(Task task) {
        crudRepository.run(session -> session.save(task));
        return task;
    }

    public boolean update(Task task) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.update(task));
            rsl = true;
        } catch (HibernateException e) {
            LOG.error("Task update error");
        }
        return rsl;
    }

    public Optional<Task> findById(int id) {
        try {
            return crudRepository.optional(FIND_BY_ID, Task.class, Map.of("fId", id));
        } catch (HibernateException e) {
            LOG.error("Find task by id error");
        }
        return Optional.empty();
    }

    public List<Task> findAll() {
        return crudRepository.query(FIND_ALL, Task.class);
    }

    public List<Task> findDone() {
        return crudRepository.query(SELECT_DONE_COLUMN, Task.class, Map.of("fDone", true));
    }

    public List<Task> findActual() {
        return crudRepository.query(SELECT_DONE_COLUMN, Task.class, Map.of("fDone", false));
    }

    public boolean delete(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(DELETE, Map.of("fId", id));
            rsl = true;
        } catch (HibernateException e) {
            LOG.error("Task delete error");
        }
        return rsl;
    }

    public boolean updateToDone(Task task) {
        boolean rsl = false;
        try {
            crudRepository.run(UPDATE_TO_DONE, Map.of("fDone", true, "fId", task.getId()));
            rsl = true;
        } catch (HibernateException e) {
            LOG.error("Update task to done error");
        }
        return rsl;
    }
}

