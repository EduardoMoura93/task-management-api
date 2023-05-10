package com.management.task.api.domain.task.model;

import com.management.task.api.domain.person.model.PersonModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
@Table(name = "task")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "department")
    private String department;

    @Column(name = "duration")
    private String duration;

    @ManyToOne
    @JoinColumn(name = "assigned_person_id")
    private PersonModel assignedPerson;

    @Column(name = "completed")
    private Boolean completed;
}
