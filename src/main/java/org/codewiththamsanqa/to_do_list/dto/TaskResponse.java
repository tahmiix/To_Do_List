package org.codewiththamsanqa.to_do_list.dto;

import org.codewiththamsanqa.to_do_list.entity.Task;

import java.time.LocalDate;

public record TaskResponse(String taskName, LocalDate dueDate) {


    public static TaskResponse taskResponse(Task task){
        return new TaskResponse(
              task.getTaskName(),
                task.getDueDate()
        );
    }

}
