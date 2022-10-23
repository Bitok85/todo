package ru.job4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;
    private String descr;

    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);

    private boolean done = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

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

    public Task(int id, String name, String descr, User user) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.user = user;
    }
}
