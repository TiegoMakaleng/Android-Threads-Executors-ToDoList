package com.tiego.makaleng.dao;

import com.tiego.makaleng.model.TaskEntry;

import java.util.List;

public interface TaskDao {

    List<TaskEntry> loadAllTasks();

    long insertTask(TaskEntry taskEntry);

    int updateTask(TaskEntry taskEntry);

    int deleteTask(TaskEntry taskEntry);
}
