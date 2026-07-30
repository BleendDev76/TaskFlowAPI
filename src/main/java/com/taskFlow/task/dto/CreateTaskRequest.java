package com.taskFlow.task.dto;

import com.taskFlow.task.model.TaskPriority;
import com.taskFlow.task.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateTaskRequest(

    @NotBlank(message = "No puedes dejar el nombre libre")
    String nombreTarea,

    @NotBlank(message = "No puedes dejar la descripcion vacia")
    String descripcion,

    @NotNull(message = "El estado de la tarea es obligatorio")
    TaskStatus estadoDeTarea,

    @NotNull(message = "Tienes que asignar una prioridad a la tarea")
    TaskPriority prioridad,

    @NotBlank(message = "Tienes que dejar a alguien responsable de la tarea")
    String responsable,

    LocalDate fechasAsociadas
){}
