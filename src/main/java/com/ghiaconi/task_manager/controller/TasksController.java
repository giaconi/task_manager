package com.ghiaconi.task_manager.controller;
import java.util.Map;
import java.util.HashMap;

import com.ghiaconi.task_manager.exceptions.ResourceNotFoundException;
import com.ghiaconi.task_manager.model.Task;
import com.ghiaconi.task_manager.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TasksController {
    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping("tasks")
    public List<Task> getAllTasks(){
        return this.tasksRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        Task task = tasksRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        return this.tasksRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long taskId,
                                                   @Valid @RequestBody Task taskDetails) throws ResourceNotFoundException {
        Task task = tasksRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        task.setTitle(taskDetails.getTitle());
        task.setDuration(taskDetails.getDuration());
        final Task updatedTask = tasksRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public Map<String, Boolean> deleteTask(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        Task task = tasksRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        tasksRepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
