package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Priority;
import ru.job4j.repository.PriorityRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    public Optional<Priority> findById(int id) {
        return priorityRepository.findById(id);
    }
}
