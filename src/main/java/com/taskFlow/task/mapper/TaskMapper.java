package com.taskFlow.task.mapper;

import com.taskFlow.task.dto.CreateTaskRequest;
import com.taskFlow.task.model.TaskModel;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskModel toModel(CreateTaskRequest request){
        return new TaskModel(
            null,
            request.nombreTarea(),
            request.descripcion(),
            request.estadoDeTarea(),
            request.prioridad(),
            request.responsable(),
            request.fechasAsociadas()
        );
    }

    public void updateModelFromRequest(TaskModel taskModel, com.taskFlow.task.dto.UpdateTaskRequest request) {
        taskModel.setNombreTarea(request.nombreTarea());
        taskModel.setDescripcion(request.descripcion());
        taskModel.setEstadoDeTarea(request.estadoDeTarea());
        taskModel.setPrioridad(request.prioridad());
        taskModel.setResponsable(request.responsable());
        taskModel.setFechasAsociadas(request.fechasAsociadas());
    }
}
