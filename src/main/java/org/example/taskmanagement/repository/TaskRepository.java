package org.example.taskmanagement.repository;

import org.example.taskmanagement.dto.TaskDTO;
import org.example.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Override
    Optional<Task> findById(Long id);
    Optional<Task> findByHeading(String heading);

}
