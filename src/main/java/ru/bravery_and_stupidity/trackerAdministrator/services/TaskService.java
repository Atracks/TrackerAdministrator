package ru.bravery_and_stupidity.trackerAdministrator.services;

import ru.bravery_and_stupidity.trackerAdministrator.dto.TaskDto;
import ru.bravery_and_stupidity.trackerAdministrator.model.Task;

import java.util.List;
import java.util.Set;

public interface TaskService {
  List<TaskDto> getTasksByProject(int projectId);
  List<TaskDto> getTasksByOrder(int orderId);
  void saveTasks(Set<Task> tasks);
}
