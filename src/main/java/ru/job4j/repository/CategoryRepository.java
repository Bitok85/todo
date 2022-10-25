package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepository {

    private static final Logger LOG = Logger.getLogger(CategoryRepository.class.getName());
    private final CrudRepository crudRepository;

    public Category createCategory(Category category) {
        crudRepository.run(session -> session.save(category));
        return category;
    }

    public List<Category> findAll() {
        return crudRepository.query("FROM Category", Category.class);
    }

    public Optional<Category> findById(int id) {
        try {
            return crudRepository.optional("FROM Category WHERE id = :fId", Category.class, Map.of("fId", id));
        } catch (HibernateException e) {
            LOG.error("Find category by id error");
        }
        return Optional.empty();
    }
}
