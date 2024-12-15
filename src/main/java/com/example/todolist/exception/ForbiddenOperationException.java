package com.example.todolist.exception;

public class ForbiddenOperationException extends RuntimeException {
    public ForbiddenOperationException() {
        super("Forbidden");
    }

    public ForbiddenOperationException(String message) {
        super(message);
    }
}
