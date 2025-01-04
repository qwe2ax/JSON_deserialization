package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.services.interfaces.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PutMapping("/{taskId}/assign")
    public ResponseEntity<String> assignTask(
            @PathVariable int taskId,
            @RequestParam int fromUserId,
            @RequestParam int toUserId) {
        try {
            taskService.assignTask(taskId, fromUserId, toUserId);
            return ResponseEntity.ok("Task successfully assigned.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
