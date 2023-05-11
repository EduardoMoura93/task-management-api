package com.management.task.api.domain.person.repository;

import com.management.task.api.domain.person.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {
    Optional<PersonModel> findByName(String name);
    @Query("SELECT DISTINCT p.department FROM PersonModel p")
    List<String> findAllDepartments();
}
