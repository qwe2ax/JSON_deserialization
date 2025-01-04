package org.example.services.interfaces;

public interface TaskService {

    void assignTask(int taskId, int fromUserId, int toUserId);
}
