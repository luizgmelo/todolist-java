package com.example.todolist.constants;

import com.example.todolist.dtos.TaskDTO;
import com.example.todolist.models.Task;

public class Constants {
    public final static Task TASK = new Task("title","description",false,1);
    public final static Task INVALID_TASK = new Task("","",false,0);
    public final static TaskDTO TASK_DTO = new TaskDTO("title","description",false,1);
}
