package com.GabSkyDev.to_do_list.service;

import com.GabSkyDev.to_do_list.dto.TaskRequestDTO;
import com.GabSkyDev.to_do_list.dto.TaskResponseDTO;
import com.GabSkyDev.to_do_list.mapper.TaskMapper;
import com.GabSkyDev.to_do_list.model.Priority;
import com.GabSkyDev.to_do_list.model.Task;
import com.GabSkyDev.to_do_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    // GET
    public List<TaskResponseDTO> getAllTasks(){
        List<Task> taskList = taskRepository.findAll();
        return taskList.
                stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    // GET
    public List<TaskResponseDTO> getTasksByPriority(Priority priority){
        List<Task> listTaskPriority = taskRepository.findByPriority(priority);
        return listTaskPriority
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    // GET
    public TaskResponseDTO getTaskById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        return taskMapper.toResponse(task);
    }

    // POST
    public TaskResponseDTO createTask(TaskRequestDTO requestDTO){
        Task task = taskMapper.toEntity(requestDTO);
        taskRepository.save(task);
        return taskMapper.toResponse(task);
    }

    // DELETE
    public void deleteTaskById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        taskRepository.delete(task);
    }

    // PUT
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        task.setName(requestDTO.name());
        task.setDescription(requestDTO.description());
        task.setState(requestDTO.state());
        task.setPriority(requestDTO.priority());

        taskRepository.save(task);

        return taskMapper.toResponse(task);
    }
}
