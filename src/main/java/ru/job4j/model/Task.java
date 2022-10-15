package ru.job4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table()
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    private String descr;

    @EqualsAndHashCode.Exclude
    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

    @EqualsAndHashCode.Exclude
    private boolean done = false;

    public Task() {
    }

    public Task(int id, String name, String descr) {
        this.id = id;
        this.name = name;
        this.descr = descr;
    }

    public Task(String name, String descr) {
        this.name = name;
        this.descr = descr;
    }
}
