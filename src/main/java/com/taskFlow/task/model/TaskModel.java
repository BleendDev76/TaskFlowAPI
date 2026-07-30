package com.taskFlow.task.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tareas")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre de la tarea es obligatorio")
    @Column(name = "nombre_tarea", nullable = false)
    private String nombreTarea;

    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "El estado de la tarea es obligatorio")
    @Column(name = "estado_de_tarea", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus estadoDeTarea;

    @NotNull(message = "La prioridad es obligatoria")
    @Column(name = "prioridad", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskPriority prioridad;

    @NotBlank(message = "El responsable es obligatorio")
    @Column(name = "responsable", nullable = false)
    private String responsable;

    @Column(name = "fechas_asociadas")
    private LocalDate fechasAsociadas;

}
