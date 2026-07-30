package com.taskFlow.task.repository;

import com.taskFlow.task.model.TaskModel;
import com.taskFlow.task.model.TaskPriority;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    Optional<TaskModel> findByNombreTarea(String nombreTarea);

    List<TaskModel> findByPrioridad(TaskPriority prioridad);

}
