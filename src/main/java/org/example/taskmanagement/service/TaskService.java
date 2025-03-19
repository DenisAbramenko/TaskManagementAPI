package org.example.taskmanagement.service;

import org.example.taskmanagement.dto.TaskDTO;
import org.example.taskmanagement.entity.Task;
import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Поиск задачи по id
     *
     * @return задача
     */
    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    /**
     * Удаление задачи из базы
     */
    public void deleteTask(Long id) {
        Task task = repository.findById(id).orElse(null);
        repository.delete(task);
    }

    /**
     * Редактирование задачи
     *
     * @return отредактированная задача
     */
    public Task updateTask(Long id, TaskDTO taskDto) {
        Task task = repository.findById(id).get();
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        task.setComments(taskDto.getComments());
        task.setAuthor(taskDto.getAuthor());
        task.setExecutor(taskDto.getExecutor());
        return repository.save(task);
    }

    /**
     * Создание задачи
     *
     * @return созданная задача
     */
    public Task createTask(TaskDTO taskDTO) {
        Task.TaskBuilder builder = Task.builder();
        builder.heading(taskDTO.getHeading());
        builder.description(taskDTO.getDescription());
        builder.priority(taskDTO.getPriority());
        builder.status(taskDTO.getStatus());
        builder.author(taskDTO.getAuthor());
        builder.executor(taskDTO.getExecutor());
        Task task = builder.build();
        return repository.save(task);
    }

    /**
     * Просмотр задачи
     *
     * @return список задач
     */
    public List<Task> getAllTasks() {
        return repository.findAll();
    }
}
