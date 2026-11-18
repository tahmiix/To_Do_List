package org.codewiththamsanqa.to_do_list.service;

import org.codewiththamsanqa.to_do_list.dto.*;
import org.codewiththamsanqa.to_do_list.entity.Task;
import org.codewiththamsanqa.to_do_list.exceptions.ResourceNotFoundException;
import org.codewiththamsanqa.to_do_list.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.codewiththamsanqa.to_do_list.dto.TaskResponse.taskResponse;

@Service
public class TaskService {

    final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskResponse createTask(TaskRequest request){

        Task task = new Task();
        task.setTaskName(request.taskName());
        task.setDueDate(request.dueDate());

        Task savedTask = taskRepository.save(task);

        return taskResponse(savedTask);

    }
    private Task findTask(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
    }

    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request){
        Task task = findTask(id);

        task.setDueDate(request.dueDate());

        return taskResponse(task);
    }

    @Transactional
    public List<TaskResponse> getAllTasks(){
        return taskRepository.findAll()
                             .stream()
                             .map(TaskResponse::taskResponse)
                             .toList();
    }

    @Transactional
    public void deleteTask(Long id){
        Task task = findTask(id);

        taskRepository.delete(task);
    }
}
