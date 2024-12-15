package com.example.todolist.constants;

import com.example.todolist.dtos.TaskDTO;
import com.example.todolist.models.Task;
import com.example.todolist.models.User;
import com.example.todolist.models.UserRole;

public class Constants {
    public final static Task TASK = new Task("title","description",false,1, new User("nome", "email", "password", UserRole.USER));
    public final static Task INVALID_TASK = new Task("","",false,0, new User());
    public final static TaskDTO TASK_DTO = new TaskDTO("title","description",false,1);
}
