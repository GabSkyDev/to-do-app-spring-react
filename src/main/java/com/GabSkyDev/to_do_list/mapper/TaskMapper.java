package com.GabSkyDev.to_do_list.mapper;

import com.GabSkyDev.to_do_list.dto.TaskRequestDTO;
import com.GabSkyDev.to_do_list.dto.TaskResponseDTO;
import com.GabSkyDev.to_do_list.model.Task;

public class TaskMapper {
    public TaskResponseDTO toResponse(Task task){
        return new TaskResponseDTO(
                task.getName(),
                task.getDescription(),
                task.getState(),
                task.getPriority()
        );
    }

    public Task toEntity(TaskRequestDTO requestDTO){
        return new Task(
                requestDTO.id(),
                requestDTO.name(),
                requestDTO.description(),
                requestDTO.state(),
                requestDTO.priority()
        );
    }
}
