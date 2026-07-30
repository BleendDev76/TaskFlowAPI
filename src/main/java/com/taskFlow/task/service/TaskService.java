package com.taskFlow.task.service;

import com.taskFlow.task.dto.CreateTaskRequest;
import com.taskFlow.task.dto.UpdateTaskRequest;
import com.taskFlow.task.model.TaskPriority;
import com.taskFlow.task.model.TaskModel;
import com.taskFlow.task.mapper.TaskMapper;
import com.taskFlow.task.repository.TaskRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TaskModel> getAll() {
        return repository.findAll();
    }

    public TaskModel getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TaskModel getByNombreTarea(String nombreTarea) {
        return repository.findByNombreTarea(nombreTarea).orElse(null);
    }

    public List<TaskModel> getByPrioridad(TaskPriority prioridad) {
        return repository.findByPrioridad(prioridad);
    }

    public TaskModel create(CreateTaskRequest request) {
        TaskModel task = mapper.toModel(request);
        return repository.save(task);
    }

    public TaskModel update(Long id, UpdateTaskRequest request) {
        if (!repository.existsById(id)) {
            return null;
        }
        TaskModel existingTask = repository.findById(id).orElseThrow(); // Ya verificamos con existsById
        mapper.updateModelFromRequest(existingTask, request);
        return repository.save(existingTask);
    }

    public String delete(Long id) {
        if (!repository.existsById(id)) {
            return "Tarea no encontrada";
        }
        repository.deleteById(id);
        return "Tarea eliminada";
    }
}
