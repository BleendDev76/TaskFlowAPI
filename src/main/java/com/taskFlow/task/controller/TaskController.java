package com.taskFlow.task.controller;

import com.taskFlow.task.dto.CreateTaskRequest;
import com.taskFlow.task.dto.UpdateTaskRequest;
import com.taskFlow.task.model.TaskPriority;
import com.taskFlow.task.model.TaskModel;
import com.taskFlow.task.service.TaskService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/tareas")
@Tag(name = "API de Gestión de Tareas", description = "Endpoints para crear, leer, actualizar y eliminar tareas.")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }
    
    @PostMapping
    @Operation(summary = "Crear una nueva tarea", description = "Crea una nueva tarea y la guarda en la base de datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarea creada con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos.", content = @Content)
    })
    public ResponseEntity<TaskModel> create(@Valid @RequestBody CreateTaskRequest request) {
        TaskModel tareaCreada = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaCreada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tarea por su ID", description = "Busca y devuelve una tarea específica según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarea encontrada con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class))),
        @ApiResponse(responseCode = "404", description = "Tarea no encontrada.", content = @Content)
    })
    public ResponseEntity<TaskModel> getById(@Parameter(description = "ID de la tarea a buscar.") @PathVariable Long id) {
        TaskModel tarea = service.getById(id);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarea);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las tareas", description = "Devuelve una lista de todas las tareas existentes.")
    @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida con éxito.")
    public ResponseEntity<List<TaskModel>> getAll() {
        List<TaskModel> tareas = service.getAll();
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/tarea-especifica/{nombreTarea}")
    @Operation(summary = "Obtener una tarea por su nombre", description = "Busca y devuelve una tarea específica según su nombre.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarea encontrada con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class))),
        @ApiResponse(responseCode = "404", description = "Tarea no encontrada.", content = @Content)
    })
    public ResponseEntity<TaskModel> getByNombreTarea(@Parameter(description = "Nombre de la tarea a buscar.") @PathVariable String nombreTarea) {
        TaskModel tarea = service.getByNombreTarea(nombreTarea);
        if (tarea == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarea);
    }

    @GetMapping("/prioridad/{prioridad}")
    @Operation(summary = "Obtener tareas por prioridad", description = "Devuelve una lista de tareas que coinciden con la prioridad especificada.")
    @ApiResponse(responseCode = "200", description = "Tareas encontradas con éxito.")
    public ResponseEntity<List<TaskModel>> getByPrioridad(@Parameter(description = "Prioridad de las tareas a buscar (ej. ALTA, MEDIA, BAJA).") @PathVariable TaskPriority prioridad) {
        List<TaskModel> tareas = service.getByPrioridad(prioridad);
        return ResponseEntity.ok(tareas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una tarea existente", description = "Actualiza los datos de una tarea existente a partir de su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarea actualizada con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskModel.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos.", content = @Content),
        @ApiResponse(responseCode = "404", description = "Tarea no encontrada.", content = @Content)
    })
    public ResponseEntity<TaskModel> update(@Parameter(description = "ID de la tarea a actualizar.") @PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request) {
        TaskModel tareaActualizada = service.update(id, request);
        if (tareaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tareaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una tarea", description = "Elimina una tarea de la base de datos a partir de su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarea eliminada con éxito."),
        @ApiResponse(responseCode = "404", description = "Tarea no encontrada.")
    })
    public ResponseEntity<String> delete(@Parameter(description = "ID de la tarea a eliminar.") @PathVariable Long id) {
        String mensaje = service.delete(id);
        if (mensaje.equals("Tarea no encontrada")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        return ResponseEntity.ok(mensaje);
    }

}
