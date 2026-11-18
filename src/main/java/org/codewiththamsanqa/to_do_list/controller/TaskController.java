package org.codewiththamsanqa.to_do_list.controller;

import org.codewiththamsanqa.to_do_list.dto.TaskRequest;
import org.codewiththamsanqa.to_do_list.dto.TaskResponse;
import org.codewiththamsanqa.to_do_list.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {

    final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request){
        TaskResponse response = taskService.createTask(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody TaskRequest request, @PathVariable Long id){
        TaskResponse response = taskService.updateTask(id,request);

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @RequestBody TaskRequest request){
         taskService.deleteTask(id);

         return ResponseEntity.noContent().build();
    }

    @GetMapping("/getTasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        List<TaskResponse> tasks = taskService.getAllTasks();

        return ResponseEntity.ok().body(tasks);

    }

}
