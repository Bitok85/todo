package ru.job4j.repository;


import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Priority;
import ru.job4j.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriorityRepository {

    private static final Logger LOG = Logger.getLogger(PriorityRepository.class.getName());
    private final CrudRepository crudRepository;

    public List<Priority> findAll() {
        return crudRepository.query("FROM Priority", Priority.class);
    }

    public Optional<Priority> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Priority WHERE id = :fId", Priority.class, Map.of("fId", id)
            );
        } catch (HibernateException e) {
            LOG.error("Find priority by id error");
        }
        return Optional.empty();
    }
}
