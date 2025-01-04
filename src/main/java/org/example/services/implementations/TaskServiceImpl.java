package org.example.services.implementations;

import lombok.RequiredArgsConstructor;
import org.example.dao.TaskRepository;
import org.example.dao.UserRepository;
import org.example.entities.Task;
import org.example.entities.User;
import org.example.services.interfaces.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final String ROLE_MANAGER = "MANAGER";
    private static final String ROLE_ADMIN = "ADMIN";
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void assignTask(int taskId, int fromUserId, int toUserId) {
        User fromUser = userRepository.findById(fromUserId).
                orElseThrow(() -> new RuntimeException("User not found with id " + fromUserId));
        User toUser = userRepository.findById(toUserId).
                orElseThrow(() -> new RuntimeException("User not found with id " + toUserId));

        boolean hasPermission = fromUser.getRoles().stream()
                .anyMatch(role -> ROLE_MANAGER.equals(role.getName()) || ROLE_ADMIN.equals(role.getName()));

        if (!hasPermission) {
            throw new SecurityException("You do not have permission to assign task");
        }

       Task task = taskRepository.findById(taskId)
               .orElseThrow(() -> new RuntimeException("Task not found with id " + taskId));

    }

    // Task task = taskRepository.findById(taskId)
    //                .orElseThrow(() -> new RuntimeException("Task not found with id " + taskId));
    //
    //        task.setAssignedUser(toUser);
    //        taskRepository.save(task);

}
