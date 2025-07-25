package com.GabSkyDev.to_do_list.controller;

import com.GabSkyDev.to_do_list.dto.TaskRequestDTO;
import com.GabSkyDev.to_do_list.dto.TaskResponseDTO;
import com.GabSkyDev.to_do_list.model.Priority;
import com.GabSkyDev.to_do_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findTaskById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @GetMapping("/priority")
    public ResponseEntity<List<TaskResponseDTO>> findTasksByPriority(@RequestParam Priority priority){
        return new ResponseEntity<>(taskService.getTasksByPriority(priority), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO requestDTO){
        return new ResponseEntity<>(taskService.createTask(requestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO requestDTO){
        return new ResponseEntity<>(taskService.updateTask(id, requestDTO), HttpStatus.OK);
    }
}
